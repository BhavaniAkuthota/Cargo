package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import com.delta.util.CommonMethod;
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
import java.util.Map;
import java.util.Random;

public class GuidedBooking {
    Map<String, String> xpathIDMap;

    WebDriver driver;
    CommonMethod commonMethod;


    public GuidedBooking(WebDriver driver) {
        this.driver = driver;

        Login login = new Login(this.driver);
        commonMethod = new CommonMethod(this.driver);
        this.xpathIDMap = login.getXpathIDMap();
    }


    public void userClicksOnTheGuidedBookingOption() {
        commonMethod.closeBanner();
        commonMethod.waitUntilElementIsClickable(xpathIDMap.get("abbookAShipment"));
        commonMethod.waitUntilXpathElementIsClickable(xpathIDMap.get("gbGuided"));
    }

    public void userSelectsGbShipmentTypes() {
        commonMethod.waitUntilXpathElementIsClickable(xpathIDMap.get("gbParcels"));
    }

    public void userEnterGbParcelShipmentOrigin(String xlShipmentOrigin) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("gbParcelsOrigin"), xlShipmentOrigin);
    }

    public void userEnterGbParcelShipmentDestination(String xlShipmentDestination) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("gbParcelsDestination"), xlShipmentDestination);
        commonMethod.waitUntilCssSelectorElementIsClickable(xpathIDMap.get("gbParcelFlightInformation"));
        commonMethod.waitForAction(2000);
    }

    public void userClickOnGbParcelShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(driver, xpathIDMap);
        datePicker.openCalendar();
        datePicker.selectDate();
        commonMethod.waitForAction(2000);
    }

    public void userSelectGbParcelDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        Select departureTime = new Select(driver.findElement(By.cssSelector(xpathIDMap.get("gbParcelsDepartureTimeSelector"))));
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        commonMethod.waitForAction(1000);
    }

    public void userSelectDeliveryType() {
        final String[] shipmentTypes = {"generalSelectedPickupDeliveryOptionCode0", "generalSelectedPickupDeliveryOptionCode1", "generalSelectedPickupDeliveryOptionCode2", "generalSelectedPickupDeliveryOptionCode3"};
        // Always defaulting to option 'None'. If more dynamic behavior is needed then remove hard coded value and use
        // the commented random index generator.
        int index = 3;
        // int index = new Random().nextInt(shipmentTypes.length);
        String shipmentSelector = String.format(xpathIDMap.get("gbParcelsShipmentServiceDeliveryType"), shipmentTypes[index]);
        driver.findElement(By.cssSelector(shipmentSelector)).click();
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentQuantity(String xlShipmentQuantity) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("gbParcelsQuantity"), xlShipmentQuantity, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentLength(String xlShipmentLength) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("gbParcelsLength"), xlShipmentLength, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentWidth(String xlShipmentWidth) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("gbParcelsWidth"), xlShipmentWidth, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userEnterGbParcelShipmentHeight(String xlShipmentHeight) {
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("gbParcelsHeight"), xlShipmentHeight, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelQuantityUnit(String xlShipmentUnit) {
        commonMethod.selectDropdownUsingCssSelector(xpathIDMap.get("gbParcelsQuantityUnits"), xlShipmentUnit);
        commonMethod.waitForAction(1000);
        // unit change popup confirmation
        commonMethod.acceptPopUpButton();
    }


    public void userEnterGbParcelShipmentWeight(String xlShipmentWeight) {
        commonMethod.sendkeysUsingXpath(xpathIDMap.get("gbParcelsTotalWeight"), xlShipmentWeight, Keys.TAB);
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentUnit(String xlShipmentWeightUnits) {
        commonMethod.selectDropdownUsingCssSelector(xpathIDMap.get("gbParcelsWeightUnits"), xlShipmentWeightUnits);
        commonMethod.waitForAction(1000);
        // unit change popup confirmation
        commonMethod.acceptPopUpButton();
    }

    public void userChooseGbParcelShipmentPiecesBeRotated(String xlShipmentDoPiecesBeRotated) {
        if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("Yes")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(xpathIDMap.get("gbParcelCanbeRotatedYes"));
        } else if (xlShipmentDoPiecesBeRotated.equalsIgnoreCase("No")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(xpathIDMap.get("gbParcelCanbeRotatedNo"));
        }
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentContainsDangerousGood(String xlShipmentDoPiecesContainDangerousGoods) {
        if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("Yes")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(xpathIDMap.get("gbParcelContainsDangerousGoodsYes"));
        } else if (xlShipmentDoPiecesContainDangerousGoods.equalsIgnoreCase("No")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(xpathIDMap.get("gbParcelContainsDangerousGoodsNo"));
        }
        commonMethod.waitForAction(1000);
    }

    public void userChooseGbParcelShipmentBePreScreened(String xlShipmentBePreScreened) {
        if (xlShipmentBePreScreened.equalsIgnoreCase("Yes")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(xpathIDMap.get("gbParcelPrescreenedYes"));
        } else if (xlShipmentBePreScreened.equalsIgnoreCase("No")) {
            commonMethod.waitForPageLoad(driver);
            commonMethod.ClickOnRadiobutton(xpathIDMap.get("gbParcelPrescreenedNo"));
        }
        commonMethod.waitForAction(1000);
    }

    public void userClicksOnGbFindFlights() {
        driver.switchTo().activeElement();
        commonMethod.waitUntilCssSelectorElementIsClickable(xpathIDMap.get("gbParcelViewShippingOptions"));
    }

    public void userSelectOneFlight() {
        // Wait for flight search dialog goes away
        commonMethod.waitUntilElementIsClickable(xpathIDMap.get("gbShipmentFlightSearch"));
        commonMethod.waitUntilElementIsClickable(xpathIDMap.get("gbShipmentFlightSearchResults"));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(xpathIDMap.get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathIDMap.get("gbShipmentFlightSearchResults")))));
        commonMethod.waitForAction(2000);
        driver.findElement(By.cssSelector(xpathIDMap.get("gbShipmentFlightStandardSelection"))).click();
    }

    public void userWaitsToFillCargoShipmentRequest() {
        WebElement chargesConfirmationPage = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentChargeConfirmation")));
        if (chargesConfirmationPage == null) {
            waitForLoaderProgressBar();
        }
    }

    private void waitForLoaderProgressBar() {
        commonMethod.waitUntilElementVisible(xpathIDMap.get("gbLoadingWait"));
    }

    public void userEnterRecipientDetails(String[] recipientInfo) {

        //waitForLoaderProgressBar();
        // Enter recipient account number and the rest of the form will be autofill by system
        commonMethod.waitForAction(15000);
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("ShipmentDescription"), recipientInfo[0], Keys.TAB);
        commonMethod.waitForAction(2000);
        commonMethod.sendkeysUsingCssSelector(xpathIDMap.get("RecipientAccountNumber"), recipientInfo[1]);
        commonMethod.waitForAction(5000);
        // Wait for loader to go away and display charge confirmation and then click 'Accept'
        waitForLoaderProgressBar();
        commonMethod.waitUntilElementVisible(xpathIDMap.get("gbDisplayChargeConfirmation"));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(xpathIDMap.get("gbDisplayChargeConfirmation")))));
        commonMethod.waitForAction(5000);
        driver.findElement(By.cssSelector(xpathIDMap.get("gbChargeConfirmationAccept"))).click();
        waitForLoaderProgressBar();
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

        waitForLoaderProgressBar();

        commonMethod.waitForAction(200);
        driver.findElement(By.cssSelector(xpathIDMap.get("gbShipmentFinalConfirmationSubmit"))).click();

        waitForLoaderProgressBar();

        // Confirmed Booking Page is displayed
        WebElement confirmedBookingPage = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentBookingConfirmation")));
        Assert.assertNotNull(confirmedBookingPage);
        WebElement airwayBill = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentAirwayBillNumber")));
        Assert.assertNotNull(airwayBill);
        System.out.println("%%%%%%%%%%%%%% Airway Bill: " + airwayBill.getText() + " %%%%%%%%%%%%%%");
        Assert.assertFalse(airwayBill.getText().isBlank());
    }
    public void userSelectOneFlightDash() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(xpathIDMap.get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathIDMap.get("gbShipmentFlightSearchResults")))));
        commonMethod.waitForAction(2000);
        driver.findElement(By.xpath(xpathIDMap.get("gbShipmentFlightDashSelection"))).click();
    }
    public void userSelectOneFlightDashCritical() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(xpathIDMap.get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathIDMap.get("gbShipmentFlightSearchResults")))));
        commonMethod.waitForAction(2000);
        driver.findElement(By.xpath(xpathIDMap.get("gbShipmentFlightDashCriticalSelection"))).click();
    }
}
