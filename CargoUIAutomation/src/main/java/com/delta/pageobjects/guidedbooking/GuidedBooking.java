package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import com.delta.util.CommonMethod;
import com.delta.util.ExcelRead;
import com.delta.util.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GuidedBooking {
    Map<String, String> xpathIDMap;
    ExcelRead excelRead = new ExcelRead();
    CommonMethod commonMethod = new CommonMethod();

    public GuidedBooking() {
        Login login = new Login();
        this.xpathIDMap = login.getXpathIDMap();
    }
    public Map<String, Object> excelReadTestData(String workSheetName, String testCaseName) {
        Map<String, Object> eachRowMap = new HashMap<>();
        String inputTestFile = "src/test/resources/TestCasesMapping.xlsx";
//        String xpathDataFile = "src/test/resources/XpathTestData.xlsx";
//        Map<String, Map<String, Object>> getConfigurationSheet = excelRead.getConfigurationSheet(inputTestFile);
        List<String> getTestCaseMasterSheet = excelRead.getTestCaseMasterSheet(inputTestFile);
        Map<String, List<Map<String, Object>>> readInputTestCaseFile = excelRead.readInputTestCaseFile(inputTestFile, getTestCaseMasterSheet);

        for (Map.Entry<String, List<Map<String, Object>>> entry : readInputTestCaseFile.entrySet()) {
            String keyHeading = entry.getKey();
            // String KeyValue = entry.getValue().toString();
            List<Map<String, Object>> TestCasesValue = entry.getValue();
            //System.out.println("TestCasesValue- - - "+TestCasesValue);

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

    public void userClicksOnTheGuidedBookingOption(WebDriver driver){
        commonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("abbookAShipment"));
        commonMethod.waitUntilXpathElementIsClickable(driver, xpathIDMap.get("gbGuided"));
    }

    public void userSelectsGbShipmentTypes(WebDriver driver){
        commonMethod.waitUntilXpathElementIsClickable(driver, xpathIDMap.get("gbParcels"));
    }

    public void userEnterGbParcelShipmentOrigin(WebDriver driver, String xlShipmentOrigin){
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsOrigin"), xlShipmentOrigin);
    }

    public void userEnterGbParcelShipmentDestination(WebDriver driver, String xlShipmentDestination) {
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsDestination"), xlShipmentDestination);
        commonMethod.waitUntilCssSelectorElementIsClickable(driver,xpathIDMap.get("gbParcelFlightInformation"));
        commonMethod.waitForAction(2000);
    }

    public void userClickOnGbParcelShipmentDatePicker(WebDriver driver) {
        DatePicker datePicker = new DatePicker(driver, xpathIDMap);
        datePicker.openCalendar();
        datePicker.selectDate();
        commonMethod.waitForAction(2000);
    }

    public void userSelectGbParcelDepartureTime(WebDriver driver) {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        Select departureTime = new Select(driver.findElement(By.cssSelector(xpathIDMap.get("gbParcelsDepartureTimeSelector"))));
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        commonMethod.waitForAction(1000);
    }

    public void userSelectDeliveryType(WebDriver driver) {
        final String[] shipmentTypes = {"generalSelectedPickupDeliveryOptionCode0", "generalSelectedPickupDeliveryOptionCode1", "generalSelectedPickupDeliveryOptionCode2", "generalSelectedPickupDeliveryOptionCode3"};
        // Always defaulting to option 'None'. If more dynamic behavior is needed then remove hard coded value and use
        // the commented random index generator.
        int index = 3;
        // int index = new Random().nextInt(shipmentTypes.length);
        String shipmentSelector = String.format(xpathIDMap.get("gbParcelsShipmentServiceDeliveryType"), shipmentTypes[index]);
        driver.findElement(By.cssSelector(shipmentSelector)).click();
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentQuantity(WebDriver driver, String xlShipmentQuantity) {
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsQuantity"), xlShipmentQuantity, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentLength(WebDriver driver, String xlShipmentLength) {
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsLength"), xlShipmentLength, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentWidth(WebDriver driver, String xlShipmentWidth) {
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsWidth"), xlShipmentWidth, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentHeight(WebDriver driver, String xlShipmentHeight) {
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsHeight"), xlShipmentHeight, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelQuantityUnit(WebDriver driver, String xlShipmentUnit) {
        commonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("gbParcelsQuantityUnits"), xlShipmentUnit);
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentWeight(WebDriver driver, String xlShipmentWeight) {
        commonMethod.sendkeysUsingXpath(driver, xpathIDMap.get("gbParcelsTotalWeight"), xlShipmentWeight, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentUnit(WebDriver driver, String xlShipmentWeightUnits) {
        commonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("gbParcelsWeightUnits"), xlShipmentWeightUnits);
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentPiecesBeRotated(WebDriver driver, String xlShipmentDoPiecesBeRotated) {
        if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("Yes")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelCanbeRotatedYes"));
        } else if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("No")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelCanbeRotatedNo"));
        }
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentContainsDangerousGood(WebDriver driver, String xlShipmentDoPiecesContainDangerousGoods) {
        if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("Yes")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelContainsDangerousGoodsYes"));
        } else if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("No")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelContainsDangerousGoodsNo"));
        }
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentBePreScreened(WebDriver driver, String xlShipmentBePreScreened) {
        if (xlShipmentBePreScreened.equalsIgnoreCase("Yes")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelPrescreenedYes"));
        } else if (xlShipmentBePreScreened.equalsIgnoreCase("No")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelPrescreenedNo"));
        }
        commonMethod.waitForAction(1000);
    }

    public void userClicksOnGbFindFlights(WebDriver driver) {
        commonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("gbParcelViewShippingOptions"));
    }

    public void userSelectOneFlight(WebDriver driver) {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(xpathIDMap.get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathIDMap.get("gbShipmentFlightSearchResults")))));
        commonMethod.waitForAction(2000);
        driver.findElement(By.cssSelector(xpathIDMap.get("gbShipmentFlightStandardSelection"))).click();
    }

    public void userWaitsToFillCargoShipmentRequest(WebDriver driver) {
        WebElement chargesConfirmationPage = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentChargeConfirmation")));
        if (chargesConfirmationPage == null) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
            waitForLoaderProgressBar(driver, wait);
        }
    }

    private void waitForLoaderProgressBar(WebDriver driver, WebDriverWait wait) {
        By loader = By.cssSelector(xpathIDMap.get("gbLoadingWait"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loader)));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(loader)));
    }

    public void userEnterRecipientDetails(WebDriver driver, String[] recipientInfo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        waitForLoaderProgressBar(driver, wait);
        // Enter recipient account number and the rest of the form will be autofill by system
        commonMethod.waitForAction(2000);
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("ShipmentDescription"), recipientInfo[0]);
        commonMethod.waitForAction(200);
        commonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("RecipientAccountNumber"), recipientInfo[1]);
        commonMethod.waitForAction(200);

        // Wait for loader to go away and display charge confirmation and then click 'Accept'
        waitForLoaderProgressBar(driver, wait);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(xpathIDMap.get("gbDisplayChargeConfirmation")))));
        commonMethod.waitForAction(200);
        driver.findElement(By.cssSelector(xpathIDMap.get("gbChargeConfirmationAccept"))).click();
        waitForLoaderProgressBar(driver, wait);

        // Select Payment method
        driver.findElement(By.cssSelector(xpathIDMap.get("gbShipmentPaymentMethod"))).click();
        commonMethod.waitForAction(200);
        // Select one available credit card (first one)
        driver.findElement(By.cssSelector(xpathIDMap.get("gbShipmentPaymentWithFirstCreditCard"))).click();
        commonMethod.waitForAction(200);
        // Select agreement check box
        driver.findElement(By.xpath(xpathIDMap.get("gbShipmentOptInFlag"))).click();
        commonMethod.waitForAction(200);

        // Submit shipment confirmation
        driver.findElement(By.cssSelector(xpathIDMap.get("gbShipmentFinalConfirmation"))).click();

        waitForLoaderProgressBar(driver, wait);

        commonMethod.waitForAction(200);
        driver.findElement(By.cssSelector(xpathIDMap.get("gbShipmentFinalConfirmationSubmit"))).click();

        waitForLoaderProgressBar(driver, wait);

        // Confirmed Booking Page is displayed
        WebElement confirmedBookingPage = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentBookingConfirmation")));
        Assert.assertNotNull (confirmedBookingPage);
        WebElement airwayBill = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentAirwayBillNumber")));
        Assert.assertNotNull(airwayBill);
        System.out.println("%%%%%%%%%%%%%% Airway Bill: " + airwayBill.getText() + " %%%%%%%%%%%%%%");
        Assert.assertFalse(airwayBill.getText().isBlank());
    }
}
