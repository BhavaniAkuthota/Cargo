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

import java.time.Duration;
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
        commmonMethod.waitUntilCssSelectorElementIsClickable(driver,xpathIDMap.get("gbParcelFlightInformation").toString());
        commmonMethod.waitForAction(2000);
    }
    public void userClickOnGbParcelShipmentDatePicker(WebDriver driver) {
        DatePicker datePicker = new DatePicker(driver, xpathIDMap);
        datePicker.openCalendar();
        datePicker.selectDate();
        commmonMethod.waitForAction(2000);
    }

    public void userSelectGbParcelDepartureTime(WebDriver driver) {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        Select departureTime = new Select(driver.findElement(By.cssSelector(xpathIDMap.get("gbParcelsDepartureTimeSelector"))));
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        commmonMethod.waitForAction(1000);
    }

    public void userSelectDeliveryType(WebDriver driver) {
        final String[] shipmentTypes = {"generalSelectedPickupDeliveryOptionCode0", "generalSelectedPickupDeliveryOptionCode1", "generalSelectedPickupDeliveryOptionCode2", "generalSelectedPickupDeliveryOptionCode3"};
        // Always defaulting to option 'None'. If more dynamic behavior is needed then remove hard coded value and use
        // the commented random index generator.
        int index = 3;
        // int index = new Random().nextInt(shipmentTypes.length);
        String shipmentSelector = String.format(xpathIDMap.get("gbParcelsShipmentServiceDeliveryType"), shipmentTypes[index]);
        driver.findElement(By.cssSelector(shipmentSelector)).click();
        commmonMethod.waitForAction(1000);
    }


    public void userEnterGbParcelShipmentQuantity(WebDriver driver, String xlShipmentQuantity) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsQuantity"), xlShipmentQuantity);
        commmonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentLength(WebDriver driver, String xlShipmentLength) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsLength"), xlShipmentLength);
        commmonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentWidth(WebDriver driver, String xlShipmentWidth) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsWidth"), xlShipmentWidth);
        commmonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentHeight(WebDriver driver, String xlShipmentHeight) {
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("gbParcelsHeight"), xlShipmentHeight);
        commmonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelQuantityUnit(WebDriver driver, String xlShipmentUnit) {
        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("gbParcelsQuantityUnits"), xlShipmentUnit);
        commmonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentWeight(WebDriver driver, String xlShipmentWeight) {
        commmonMethod.sendkeysUsingXpath(driver, xpathIDMap.get("gbParcelsTotalWeight"), xlShipmentWeight);
        commmonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentUnit(WebDriver driver, String xlShipmentWeightUnits) {
        commmonMethod.selectDropdownUsingCssSelector(driver,xpathIDMap.get("gbParcelsWeightUnits"), xlShipmentWeightUnits);
        commmonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentPiecesBeRotated(WebDriver driver, String xlShipmentDoPiecesBeRotated) {
        if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("Yes")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelCanbeRotatedYes"));
        } else if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("No")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelCanbeRotatedNo"));
        }
        commmonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentContainsDangerousGood(WebDriver driver, String xlShipmentDoPiecesContainDangerousGoods) {
        if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("Yes")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelContainsDangerousGoodsYes"));
        } else if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("No")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelContainsDangerousGoodsNo"));
        }
        commmonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentBePreScreened(WebDriver driver, String xlShipmentBePreScreened) {
        if (xlShipmentBePreScreened.equalsIgnoreCase("Yes")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelPrescreenedYes"));
        } else if (xlShipmentBePreScreened.equalsIgnoreCase("No")) {
            commmonMethod.waitForPageLoad(driver);
            commmonMethod.ClickOnRadiobutton(driver, xpathIDMap.get("gbParcelPrescreenedNo"));
        }
        commmonMethod.waitForAction(1000);
    }

    public void userClicksOnGbFindFlights(WebDriver driver) {
        commmonMethod.waitUntilCssSelectorElementIsClickable(driver, xpathIDMap.get("gbParcelViewShippingOptions"));
    }

    public void userSelectOneFlight(WebDriver driver) {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='modal-dialog ']")));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//dc-flight-search-results"))));
        commmonMethod.waitForAction(2000);
        driver.findElement(By.cssSelector("div[class='column flightInformation'] + div[class='column'] a")).click();
    }

    public void userWaitsToFillCargoShipmentRequest(WebDriver driver) {
        WebElement chargesConfirmationPage = driver.findElement(By.xpath("//main[@id='mainContent']"));
        if (chargesConfirmationPage == null) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
            waitForLoaderProgressBar(driver, wait);
        }
    }

    private void waitForLoaderProgressBar(WebDriver driver, WebDriverWait wait) {
        By loader = By.cssSelector("span img[alt='loading']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loader)));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(loader)));
    }

    public void userEnterRecipientDetails(WebDriver driver, String[] recipientInfo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        waitForLoaderProgressBar(driver, wait);
        commmonMethod.waitForAction(2000);
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("ShipmentDescription"), recipientInfo[0]);
        commmonMethod.waitForAction(200);
        commmonMethod.sendkeysUsingCssSelector(driver, xpathIDMap.get("RecipientAccountNumber"), recipientInfo[1]);
        commmonMethod.waitForAction(200);

        waitForLoaderProgressBar(driver, wait);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("body[class='modal-open']"))));
        driver.findElement(By.cssSelector("button[class='btn blue-btn ng-binding']")).click();
        waitForLoaderProgressBar(driver, wait);

        // Select Payment method
        driver.findElement(By.cssSelector("input[id='bopaymentTypeCreditCard'] + label[for='bopaymentTypeCreditCard']")).click();
        commmonMethod.waitForAction(200);
        // Select one credit available credit card
        driver.findElement(By.cssSelector("input[id='bosavedCardData-1'] + label[for='bosavedCardData-1']")).click();
        commmonMethod.waitForAction(200);
        // Select agreement check box
        driver.findElement(By.xpath("//label[@for='bosubscriptionOptFlag']")).click();
        commmonMethod.waitForAction(200);

        // Submit shipment confirmation
        driver.findElement(By.cssSelector("button[type='button'] + button[type='submit']")).click();

        waitForLoaderProgressBar(driver, wait);

        driver.findElement(By.cssSelector("button[class='btn blue-btn ng-binding']")).click();

        waitForLoaderProgressBar(driver, wait);

        // Confirmed Booking Page is displayed
        WebElement confirmedBookingPage = driver.findElement(By.xpath("//div[@id='dc-print']"));
        assert (confirmedBookingPage != null);
    }
}
