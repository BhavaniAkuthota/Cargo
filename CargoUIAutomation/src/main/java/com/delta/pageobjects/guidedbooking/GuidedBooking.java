package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import com.delta.util.CommonMethod;
import com.delta.util.ExcelRead;
import com.delta.util.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GuidedBooking {
    Map<String, String> xpathIDMap = new HashMap<String, String>();
    ExcelRead excelRead = new ExcelRead();
    CommonMethod commmonMethod = new CommonMethod();
    Login login = new Login();
    WebDriver driver;
    public GuidedBooking() {
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


        for (Map.Entry<String, List<Map<String, Object>>> entry : readInputTestCaseFile.entrySet()) {

            String keyHeading = entry.getKey();
            // String KeyValue = entry.getValue().toString();
            List<Map<String, Object>> TestCasesValue = entry.getValue();
            //System.out.println("TestCasesValue- - - "+TestCasesValue);
            if (keyHeading.equalsIgnoreCase(workSheetName)) {// passing the worksheetname example ABGeneralCargo

                for (Map<String, Object> stringObjectMap : TestCasesValue) {
                    eachRowMap = stringObjectMap;
                    String xlTestCaseName = eachRowMap.get("TestCases").toString();
                    System.out.println("xlTestCaseName- - " + xlTestCaseName);
                    if (xlTestCaseName.equalsIgnoreCase(testCaseName)) {
                        eachRowMap = stringObjectMap;
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
    public void userClicksOnTheGuidedBookingOption(WebDriver driver){
        commmonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("abbookAShipment").toString());

        commmonMethod.waitUntilXpathElementIsClickable(driver, xpathIDMap.get("gbGuided").toString());
    }
    public void userSelectsGbShipmentTypes(WebDriver driver){
        commmonMethod.waitUntilXpathElementIsClickable(driver, xpathIDMap.get("gbParcels").toString());
    }
    public void userEnterGbParcelShipmentOrigin(WebDriver driver, String xlShipmentOrigin){
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsOrigin").toString(),xlShipmentOrigin);
    }
    public void userEnterGbParcelShipmentDestination(WebDriver driver, String xlShipmentDestination) throws InterruptedException {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsDestination").toString(),xlShipmentDestination);
    }

    public void userClickOnGbParcelShipmentDatePicker(WebDriver driver) {
        DatePicker datePicker = new DatePicker(driver, xpathIDMap);
        datePicker.openCalendar();
        datePicker.selectDate();
        commmonMethod.waitForAction(2000);
    }

//    public void userSelectGbParcelShipmentDate(WebDriver driver, String xlShipmentCalendarDate) {
//        commmonMethod.waitUntilCssSelectorElementIsClickable(driver,xpathIDMap.get("gbParcelsShipmentDate").toString());
//        commmonMethod.shippingDate(driver,xlShipmentCalendarDate);
//
//    }

    public void userSelectGbParcelDepartureTime(WebDriver driver) {
        final String[] departureTimes = {"Early (12am-8am)", "Morning (8am-12pm)", "Afternoon (12pm-4pm)", "Evening (4pm-8pm)", "Late (8pm-12pm"};
        Select departureTime = new Select(driver.findElement(By.cssSelector(xpathIDMap.get("gbParcelsDepartureTimeSelector"))));
        int index = new Random().nextInt(departureTimes.length);
        System.out.println("\n\n##################### Departure Time: " + departureTimes[index] + " #####################\n\n");
        departureTime.selectByVisibleText(departureTimes[index]);
        commmonMethod.waitForAction(1000);
    }

    public void userSelectDeliveryType(WebDriver driver) {
        final String[] shipmentTypes = {"generalSelectedPickupDeliveryOptionCode0", "generalSelectedPickupDeliveryOptionCode1", "generalSelectedPickupDeliveryOptionCode2", "generalSelectedPickupDeliveryOptionCode3"};
        int index = new Random().nextInt(shipmentTypes.length);
        String shipmentSelector = String.format("input[id*='generalSelectedPickupDeliveryOptionCode'] + label[for='%s']", shipmentTypes[index]);
        driver.findElement(By.cssSelector(shipmentSelector)).click();
    }


//    public void userSelectGbParcelShippingOption(WebDriver driver){
//        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("gbParcelsDepartureTime").toString(),xl);
//
//    }



    public void userEnterGbParcelShipmentQuantity(WebDriver driver, String xlShipmentQuantity) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsQuantity").toString(), xlShipmentQuantity);
    }
    public void userEnterGbParcelShipmentLength(WebDriver driver, String xlShipmentLength) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsLength").toString(), xlShipmentLength);
    }
    public void userEnterGbParcelShipmentWidth(WebDriver driver, String xlShipmentWidth) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsWidth").toString(), xlShipmentWidth);
    }
    public void userEnterGbParcelShipmentHeight(WebDriver driver, String xlShipmentHeight) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsHeight").toString(), xlShipmentHeight);
    }


    public void userChooseGbParcelQuantityUnit(WebDriver driver, String xlShipmentUnit) {
        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("gbParcelsQuantityUnits"),xlShipmentUnit);

    }
    public void userEnterGbParcelShipmentWeight(WebDriver driver, String xlShipmentWeight) {
        commmonMethod.sendkeysUsingXpath(driver, xpathIDMap.get("gbParcelsTotalWeight").toString(), xlShipmentWeight);
    }
    public void userChooseGbParcelShipmentUnit(WebDriver driver, String xlShipmentWeightUnits) {
        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("gbParcelsWeightUnits"),xlShipmentWeightUnits);

    }

    public void userChooseGbParcelShipmentPiecesBeRotated(WebDriver driver, String xlShipmentDoPiecesBeRotated) {
        if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("Yes")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelCanbeRotatedYes").toString());
        } else if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("No")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelCanbeRotatedNo").toString());
        }
    }
    public void userChooseGbParcelShipmentContainsDangerousGood(WebDriver driver, String xlShipmentDoPiecesContainDangerousGoods) {
        if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("Yes")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelContainsDangerousGoodsYes").toString());
        } else if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("No")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelContainsDangerousGoodsNo").toString());
        }
    }
    public void userChooseGbParcelShipmentBePreScreened(WebDriver driver, String xlShipmentBePreScreened) {
        if (xlShipmentBePreScreened.equalsIgnoreCase("Yes")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelPrescreenedYes").toString());
        } else if (xlShipmentBePreScreened.equalsIgnoreCase("No")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelPrescreenedNo").toString());
        }
    }
}
