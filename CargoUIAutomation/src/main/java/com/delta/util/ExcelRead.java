package com.delta.util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelRead {

    private int configurationSheetIndex = 0;
    private int testCaseMenuSheetIndex = 1;
    List<String> testCaseNameList = new ArrayList<String>();
    /*
     * public static void main(String[] args) {
     *
     * // this is a list having testcases for which 'execute' is 'yes'
     * List<String> testCaseNameList = getMasterSheet("FileName.xlsx");
     * System.out.println("testCaseNameList..." + testCaseNameList);
     *
     * //Map<String,List<List<Map<String, Object>>>>
     * testCaseSheetMap=readTestCaseFile("NewTestCasesGR.xlsx",
     * testCaseNameList); Map<String,List<Map<String, Object>>>
     * testCaseSheetMap=readTestCaseFile("NewTestCasesGR.xlsx",
     * testCaseNameList); System.out.println(
     * "------------------testCaseSheetMap---------------------" +
     * testCaseSheetMap); }
     */

    /**
     *
     * Reads Configuration(first)sheet data of testCase XLFile to a map
     *
     */
    public Map<String, Map<String, Object>> getConfigurationSheet(String inputTestCaseFile) {

        // Map<String, String> configurationMap = new LinkedHashMap<String, String>();
        Map<String, Map<String, Object>> configurationMap = new LinkedHashMap<String, Map<String, Object>>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(inputTestCaseFile));
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(configurationSheetIndex);
            System.out.println("\n ********......getConfigurationSheet(FirstSheet)...*************");
            String sheetName = sheet.getSheetName();
            System.out.println("SheetName=" + sheetName);

            int firstRowIndex = 0;
            XSSFRow firstRow = sheet.getRow(firstRowIndex);
            int firstRowColumnCount = firstRow.getLastCellNum();
            System.out.println("firstRowColumnCount=" + firstRowColumnCount);

            // for each row
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("Number of rows..." + rowCount);
            for (int i = 1; i < rowCount; i++) {

                String configNameKey = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                Map<String, Object> configValuesMap = new LinkedHashMap<String, Object>();
                XSSFRow row = sheet.getRow(i);

                // from second column onwards-for each column
                for (int j = 1; j < firstRowColumnCount; j++) {

                    Object columnValue = "";
                    String columnHeadingKey = sheet.getRow(0).getCell(j).getStringCellValue().trim();

                    if (row.getCell(i) != null) {
                        switch (row.getCell(j).getCellType()) {
                            case STRING:
                                columnValue = row.getCell(j).getStringCellValue().trim();
                                break;
                            case NUMERIC:
                                columnValue = row.getCell(j).getNumericCellValue();
                                break;
                            case BOOLEAN:
                                columnValue = row.getCell(j).getBooleanCellValue();
                                break;
                            default:
                                columnValue = "";
                                break;
                        }
                    }
                    columnValue.toString().trim();
                    configValuesMap.put(columnHeadingKey, columnValue);
                } // end for each column
                configurationMap.put(configNameKey, configValuesMap);
            }
            //System.out.println("configurationMap..." + configurationMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Corrupted  Excel Sheet/Missing Data");
            e.printStackTrace();
        }
        return configurationMap;
    }

    /**
     *
     * Reads TestCaseMaster(second)sheet data of testCase XLFile to a list.
     * Returned list has sheet names which has to be executed(Yes)
     *
     */
    public List<String> getTestCaseMasterSheet(String inputTestCaseFile) {

       // List<String> testCaseNameList = new ArrayList<String>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(inputTestCaseFile));
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(testCaseMenuSheetIndex);
            System.out.println("********......getSecondSheet(Master)...*************");
            String sheetName = sheet.getSheetName();
            System.out.println("Sheet=" + sheetName);

            // for each row
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("Number of rows..." + rowCount);
            for (int i = 1; i < rowCount; i++) {

                // column2 - Execute
                String execute = sheet.getRow(i).getCell(1).getStringCellValue().trim();
                if (execute.equalsIgnoreCase("Yes")) {
					/*// column1 - TestCaseName
					String key = sheet.getRow(i).getCell(0).getStringCellValue();*/
                    // column3 - SheetName
                    String key = sheet.getRow(i).getCell(2).getStringCellValue().trim();
                    testCaseNameList.add(key);
                }
            }
           // System.out.println("testCaseList..." + testCaseList);
           // System.out.println("testCaseList..." + testCaseNameList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Corrupted  Excel Sheet...");
            e.printStackTrace();
        }
        return testCaseNameList;
    }

    /**
     * read 4-1 todas all row
     *
     */
    public Map<String, List<Map<String, Object>>> readInputTestCaseFile(String inputTestCaseFile,
                                                                        List<String> executableSheetList) {

        System.out.println("\n ********......readTestCaseFileForEachSheetHavingYes...*************");
        String excelFilePath = inputTestCaseFile;
        Map<String, List<Map<String, Object>>> testCaseSheetsDataMap = new LinkedHashMap<String, List<Map<String, Object>>>();

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(inputStream);
            int sheetCount = workbook.getNumberOfSheets();
            System.out.println("sheetCount..." + sheetCount);

            for (String testCaseName : executableSheetList) {// for each testCase sheet to be executed

                Boolean sheetFound=false;

                System.out.println("---------------------------");
                System.out.println("Sheet testCaseName ...." + testCaseName);
                List<Map<String, Object>> allrowLists = new ArrayList<Map<String, Object>>();

                for (int i = 2; i < sheetCount; i++) { // from sheet3 ,for each
                    // sheet
                    XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(i);

                    String sheetName = sheet.getSheetName();

                    if (sheetName.equalsIgnoreCase(testCaseName)) {
                        sheetFound=true;
                        System.out.println("sheetName-----"+sheetName);
                        // first row - all column count
                        int firstRowIndex = 0;
                        XSSFRow firstRow = sheet.getRow(firstRowIndex);
                        int firstRowColumnCount = firstRow.getLastCellNum();

                        int rowCount = sheet.getPhysicalNumberOfRows();
                        System.out.println("rowCount..." + rowCount);
                        for (int j = 1; j < rowCount; j++) { // for each row, from second row
                            Map<String, Object> columnMap = null;
                            XSSFRow row = sheet.getRow(j);

                            String rowExecute = row.getCell(3).getStringCellValue().trim();
                            if (rowExecute.equalsIgnoreCase("yes")||rowExecute.equalsIgnoreCase("No")) {
                                columnMap = new LinkedHashMap<String, Object>();
                                for (int k = 0; k < firstRowColumnCount; k++) { // for
                                    // each
                                    // column
                                    Object columnValue = "";
                                    String columnHeadingKey = sheet.getRow(0).getCell(k).getStringCellValue().trim();

                                    XSSFCell cell=sheet.getRow(j).getCell(k);
                                    if (cell != null) {
                                        switch (cell.getCellType()) {
                                            case STRING:
                                                columnValue = cell.getStringCellValue();
                                                break;
                                            case NUMERIC:
                                                //columnValue = cell.getNumericCellValue();

											/*- double doubleVal =  cell.getNumericCellValue();
											//to get rid of '.0' which gets appended to integers while reading from excel
											columnValue=String.valueOf(doubleVal).replaceFirst("\\.0+$", "");
											*/
                                                // columnValue = NumberToTextConverter.toText(cell.getNumericCellValue());

                                                DataFormatter df = new DataFormatter();
                                                columnValue = df.formatCellValue(cell);

                                                break;
                                            case BOOLEAN:
                                                columnValue = cell.getBooleanCellValue();
                                                break;
                                            case FORMULA:
                                                switch (cell.getCachedFormulaResultType()) {
                                                    case NUMERIC:
                                                        //columnValue = cell.getNumericCellValue();
                                                        double dVal =  cell.getNumericCellValue();
                                                        //to get rid of '.0' which gets appended to integers while reading from excel
                                                        columnValue=String.valueOf(dVal).replaceFirst("\\.0+$", "");
                                                        break;
                                                    case STRING:
                                                        columnValue = cell.getStringCellValue();
                                                        break;
                                                    default:
                                                        columnValue = "defaultFormulaSwitchCase";
                                                        break;
                                                }
                                                break;
                                            case BLANK:
                                                columnValue = "";
                                                break;
                                            default:
                                                //columnValue = "";
                                                columnValue = "defaultSwitchCase";
                                                break;
                                        }
                                    }


                                    columnValue = columnValue.toString().trim();
                                    columnMap.put(columnHeadingKey, columnValue);
                                } // for each column
                                allrowLists.add(columnMap);
                            } // endif
                        } // for each row
                       // System.out.println("allRowLists..." + allrowLists);
                        break;
                    } // end if (sheetName.equals(testCaseName))
                } // for each sheet

                if(sheetFound==false)
                    System.out.println("XL Error: "+testCaseName+" Sheet not found.");
                testCaseSheetsDataMap.put(testCaseName, allrowLists);
            } // for testcaseNameList
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Corrupted  Excel Sheet...");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testCaseSheetsDataMap;
    }

    /**
     * Reads xpath and id data from XpathData excel sheet
     *
     */
    public Map<String, String> readXPathData(String xpathDataFile) {

        System.out.println("\n ********......readXPathData...*************");
        Map<String, String> xPathIdMap = new LinkedHashMap<String, String>();

        try {
            FileInputStream inputStream = new FileInputStream(new File(xpathDataFile));
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //int LastRowNum = sheet.getLastRowNum();
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("readXPathData rowCount..." + rowCount);
            String key, value;
            for (int i = 0; i < rowCount; i++) {
                key = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                value = sheet.getRow(i).getCell(1).getStringCellValue().trim();
                xPathIdMap.put(key, value);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Corrupted MasterData Excel Sheet...");
            e.printStackTrace();
        }
        System.out.println(xPathIdMap);
        return xPathIdMap;
    }


    /**
     *
     * Reads TestCaseMaster(second)sheet data of testCase XLFile to a map.
     * Returned map has executable(Sheet name, testCaseName)
     *
     */
   /* public Map<String,String> getMasterMap(String inputTestCaseFile) {

        Map<String,String> masterMap = new  LinkedHashMap<String,String>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(inputTestCaseFile));
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(testCaseMenuSheetIndex);
            System.out.println("\n ********......getSecondSheet(Master)...*************");
            String sheetName = sheet.getSheetName();
            System.out.println("Sheet=" + sheetName);

            // for each row
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("Number of rows..." + rowCount);
            for (int i = 1; i < rowCount; i++) {

                // column2 - Execute
                String execute = sheet.getRow(i).getCell(1).getStringCellValue();
                if (execute.equalsIgnoreCase("Yes")) {
                    // column1 - TestCaseName is the value
                    String testCaseName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                    // column3 - SheetName is the key
                    String keySheetName = sheet.getRow(i).getCell(2).getStringCellValue().trim();
                    masterMap.put( keySheetName,testCaseName);
                }
            }
            // System.out.println("testCaseList..." + testCaseList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Corrupted  Excel Sheet...");
            e.printStackTrace();
        }
        return masterMap;
    }
*/


    /**
     *
     * Reads TestCaseMaster(third)sheet data of testCase XLFile to a map.
     * Returned map has executable(Sheet name, testCaseName)
     *
     */
   /* public Map<String,String> getNewMasterMap(String inputTestCaseFile) {

        Map<String,String> masterMap = new  LinkedHashMap<String,String>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(inputTestCaseFile));
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(2);
            System.out.println("\n ********......getSecondSheet(Master)...*************");
            String sheetName = sheet.getSheetName();
            System.out.println("Sheet=" + sheetName);

            // for each row
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("Number of rows..." + rowCount);
            for (int i = 1; i < rowCount; i++) {

                // column2 - Execute
                String execute = sheet.getRow(i).getCell(1).getStringCellValue();
                if (execute.equalsIgnoreCase("Yes")) {
                    // column1 - TestCaseName is the value
                    String testCaseName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
                    // column3 - SheetName is the key
                    String keySheetName = sheet.getRow(i).getCell(2).getStringCellValue().trim();
                    masterMap.put( keySheetName,testCaseName);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Corrupted  Excel Sheet...");
            e.printStackTrace();
        }
        return masterMap;
    }*/






}

