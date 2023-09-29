package com.delta.pageobjects.advancebooking;

import com.delta.util.CommonMethod;
import com.delta.util.ExcelRead;
import com.delta.util.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvanceBooking {
    Map<String, String> xpathIDMap = new HashMap<String, String>();
    ExcelRead excelRead = new ExcelRead();
    CommonMethod commmonMethod = new CommonMethod();
    Login login = new Login();
    WebDriver driver;

    public AdvanceBooking() {
        Login login = new Login();
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

    public void userClicksTheAdvancedOption(WebDriver driver) {

        commmonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("abbookAShipment").toString());

        commmonMethod.waitUntilXpathElementIsClickable(driver, xpathIDMap.get("abbookAdvance").toString());


    }

    public void userClickAssignNewAirWaybillNumber(WebDriver driver) {

        commmonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("abassignNewAirWaybillNumber").toString());
    }

    public void userEnterMyShipmentContains(WebDriver driver, String xlShipmentContains) throws InterruptedException {
        /*List<WebElement> webShipmentContains = driver.findElements(By.cssSelector(xpathIDMap.get("abmyShipmentContainsString")));
        for (int i = 0; webShipmentContains.size() > i; i++) {
            String webShipmentValue = webShipmentContains.get(i).getText().toString();
            if (webShipmentValue.equalsIgnoreCase(xlShipmentContains)) {
                webShipmentContains.get(i).click();
                // Thread.sleep(5000);
            }

        }*/
        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("abmyShipmentContainsString"),xlShipmentContains);
    }


    public void userEnterShipmentDescription(WebDriver driver, String xlShipmentDescription) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("abdescriptionTextArea").toString(), xlShipmentDescription);
    }


    public void userChooseTheShipmentMethod(WebDriver driver, String xlShipmentMethod) {
        if (xlShipmentMethod.equalsIgnoreCase("Loose")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("abshippingMethodLoose").toString());
        } else if (xlShipmentMethod.equalsIgnoreCase("Containerized")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("abshippingMethodContainer").toString());
        } else if (xlShipmentMethod.equalsIgnoreCase("Both")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("abshippingMethodBoth").toString());
        }
    }


    public void userEnterShipmentWeight(WebDriver driver, String xlShipmentWeight) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("abshipmentWeight").toString(), xlShipmentWeight);
    }

    public void userChooseShipmentUnit(WebDriver driver, String xlShipmentWeightUnits) {
        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("abshipmentWeightUnit"),xlShipmentWeightUnits);

    }

    public void userEnterShipmentQuantity(WebDriver driver, String xlShipmentQuantity) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("abshipmentQuantity").toString(), xlShipmentQuantity);
    }
    public void userEnterShipmentLength(WebDriver driver, String xlShipmentLength) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("abshipmentLength").toString(), xlShipmentLength);
    }
    public void userEnterShipmentWidth(WebDriver driver, String xlShipmentWidth) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("abshipmentWidth").toString(), xlShipmentWidth);
    }
    public void userEnterShipmentHeight(WebDriver driver, String xlShipmentHeight) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("abshipmentHeight").toString(), xlShipmentHeight);
    }


    public void userChooseQuantityUnit(WebDriver driver, String xlShipmentUnit) {
        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("abshipmentWeightUnit"),xlShipmentUnit);

    }
}



