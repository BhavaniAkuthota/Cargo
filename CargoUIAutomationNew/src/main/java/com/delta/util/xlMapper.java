package com.delta.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xlMapper {

    static ExcelRead excelRead = new ExcelRead();
    static String xpathDataFile = "src/test/resources/XpathTestData.xlsx";
    static Map<String, String> xpathIDMap = excelRead.readXPathData(xpathDataFile);

    public static Map<String, String> getXpathIDMap() {

        return xpathIDMap;
    }

    public static Map<String, Object> excelReadTestData(String workSheetName, String testCaseName) {
        Map<String, Object> eachRowMap = new HashMap<>();
        String inputTestFile = "src/test/resources/TestCasesMapping.xlsx";

        List<String> getTestCaseMasterSheet = excelRead.getTestCaseMasterSheet(inputTestFile);
        Map<String, List<Map<String, Object>>> readInputTestCaseFile = excelRead.readInputTestCaseFile(inputTestFile, getTestCaseMasterSheet);

        for (Map.Entry<String, List<Map<String, Object>>> entry : readInputTestCaseFile.entrySet()) {
            String keyHeading = entry.getKey();
            // String KeyValue = entry.getValue().toString();
            List<Map<String, Object>> TestCasesValue = entry.getValue();
            System.out.println("TestCasesValue- - - "+TestCasesValue);

            if (keyHeading.equalsIgnoreCase(workSheetName)) {// passing the work sheet name example ABGeneralCargo
                for (Map<String, Object> stringObjectMap : TestCasesValue) {
                    eachRowMap = stringObjectMap;
                    String xlTestCaseName = eachRowMap.get("TestCases").toString();
                    System.out.println("xlTestCaseName- - " + xlTestCaseName);
                    if (xlTestCaseName.equalsIgnoreCase(testCaseName)) {
//                        eachRowMap = stringObjectMap;
                        System.out.println(eachRowMap);
                        System.out.println("Testcase- - -" + eachRowMap.get("TestCases"));
                        System.out.println("ShipmentContains- - --" + eachRowMap.get("ShipmentContains"));
                        break;
                    }
                }
                // System.out.println("keyHeading - - -"+keyHeading+" /n"+ "KeyValue- - - "+ KeyValue);
                // if(TestCasesValue.get(1)) {
            }
        }
        return eachRowMap;
    }


}
