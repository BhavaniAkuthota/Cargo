package com.delta.pageobjects.advancebooking;

import com.delta.util.CommonMethod;
import com.delta.util.ExcelRead;
import com.delta.util.Login;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvanceBooking {
    Map<String, String> xpathIDMap = new HashMap<String, String>();
    ExcelRead excelRead = new ExcelRead();
    CommonMethod commonMethod;
    WebDriver driver;


    public AdvanceBooking(WebDriver driver) {
        this.driver = driver;
        Login login = new Login(driver);
        commonMethod = new CommonMethod(driver);
        this.xpathIDMap = login.getXpathIDMap();
    }

    public Map<String, Object> excelReadTestData(String workSheetName, String testCaseName) {

        Map<String, Object> eachRowMap = new HashMap<String, Object>();
        String inputTestFile = "src/test/resources/TestCasesMapping.xlsx";
        String xpathDataFile = "src/test/resources/XpathTestData.xlsx";
        Map<String, Map<String, Object>> getConfigurationSheet = excelRead.getConfigurationSheet(inputTestFile);
        List<String> getTestCaseMasterSheet = excelRead.getTestCaseMasterSheet(inputTestFile);
        Map<String, List<Map<String, Object>>> readInputTestCaseFile = excelRead.readInputTestCaseFile(inputTestFile, getTestCaseMasterSheet);
        //Map<String,String>  xpathIDMap=  excelRead.readXPathData(xpathDataFile);

        for (Map.Entry<String, List<Map<String, Object>>> entry : readInputTestCaseFile.entrySet()) {

            String keyHeading = entry.getKey();
            // String KeyValue = entry.getValue().toString();
            List<Map<String, Object>> TestCasesValue = entry.getValue();
            //System.out.println("TestCasesValue- - - "+TestCasesValue);
            if (keyHeading.equalsIgnoreCase(workSheetName)) {// passing the worksheetname example ABGeneralCargo

                for (int i = 0; TestCasesValue.size() > i; i++) {
                    eachRowMap = TestCasesValue.get(i);
                    String xlTestCaseName = eachRowMap.get("TestCases").toString();
                    System.out.println("xlTestCaseName- - " + xlTestCaseName);
                    if (xlTestCaseName.equalsIgnoreCase(testCaseName)) {
                        eachRowMap = TestCasesValue.get(i);
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

    public void userClicksTheAdvancedOption() {

        commonMethod.waitUntilCssSelectorElementIsClickable(xpathIDMap.get("abbookAShipment").toString());

        commonMethod.waitUntilXpathElementIsClickable(xpathIDMap.get("abbookAdvance").toString());


    }

    public void userClickAssignNewAirWaybillNumber() {

        commonMethod.waitUntilCssSelectorElementIsClickable(xpathIDMap.get("abassignNewAirWaybillNumber").toString());
    }

    public void userEnterMyShipmentContains(String xlShipmentContains) throws InterruptedException {
        /*List<WebElement> webShipmentContains = driver.findElements(By.cssSelector(xpathIDMap.get("abmyShipmentContainsString")));
        for (int i = 0; webShipmentContains.size() > i; i++) {
            String webShipmentValue = webShipmentContains.get(i).getText().toString();
            if (webShipmentValue.equalsIgnoreCase(xlShipmentContains)) {
                webShipmentContains.get(i).click();
                // Thread.sleep(5000);
            }

        }*/
        commonMethod.selectDropdownUsingCssSelector(xpathIDMap.get("abmyShipmentContainsString"), xlShipmentContains);
    }


    public void userEnterShipmentDescription(String xlShipmentDescription) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("abdescriptionTextArea").toString(), xlShipmentDescription);
    }


    public void userChooseTheShipmentMethod(String xlShipmentMethod) {
        if (xlShipmentMethod.equalsIgnoreCase("Loose")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.waitUntilCssSelectorElementIsClickable(xpathIDMap.get("abshippingMethodLoose").toString());
        } else if (xlShipmentMethod.equalsIgnoreCase("Containerized")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.waitUntilCssSelectorElementIsClickable(xpathIDMap.get("abshippingMethodContainer").toString());
        } else if (xlShipmentMethod.equalsIgnoreCase("Both")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.waitUntilCssSelectorElementIsClickable(xpathIDMap.get("abshippingMethodBoth").toString());
        }
    }


    public void userEnterShipmentWeight(String xlShipmentWeight) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("abshipmentWeight").toString(), xlShipmentWeight);
    }

    public void userChooseShipmentUnit(String xlShipmentWeightUnits) {
        commonMethod.selectDropdownUsingCssSelector(xpathIDMap.get("abshipmentWeightUnit"), xlShipmentWeightUnits);

    }

    public void userEnterShipmentQuantity(String xlShipmentQuantity) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("abshipmentQuantity").toString(), xlShipmentQuantity);
    }

    public void userEnterShipmentLength(String xlShipmentLength) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("abshipmentLength").toString(), xlShipmentLength);
    }

    public void userEnterShipmentWidth(String xlShipmentWidth) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("abshipmentWidth").toString(), xlShipmentWidth);
    }

    public void userEnterShipmentHeight(String xlShipmentHeight) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("abshipmentHeight").toString(), xlShipmentHeight);
    }


    public void userChooseQuantityUnit(String xlShipmentUnit) {
        commonMethod.selectDropdownUsingCssSelector(xpathIDMap.get("abshipmentWeightUnit"), xlShipmentUnit);

    }
}



