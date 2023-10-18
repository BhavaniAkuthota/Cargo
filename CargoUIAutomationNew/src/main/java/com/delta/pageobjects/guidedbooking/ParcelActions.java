package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class ParcelActions extends GBActions {

    public ParcelActions(WebDriver driver) {
        super(driver);
    }

    public void userClickOnGBParcelShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(getDriver(), getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbParcelsShipmentDateInput", "gbParcelsShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        getCommonMethod().waitForAction(2000);
    }

    public void userSelectGBParcelDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        Select departureTime = new Select(getDriver().findElement(By.cssSelector(getXpathIDMap().get("gbParcelsDepartureTimeSelector"))));
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        getCommonMethod().waitForAction(200);
    }

    public void userSelectDeliveryType() {
        final String[] shipmentTypes = {"generalSelectedPickupDeliveryOptionCode0", "generalSelectedPickupDeliveryOptionCode1", "generalSelectedPickupDeliveryOptionCode2", "generalSelectedPickupDeliveryOptionCode3"};
        // Always defaulting to option 'None'. If more dynamic behavior is needed then remove hard coded value and use
        // the commented random index generator.
        int index = 3;
        // int index = new Random().nextInt(shipmentTypes.length);
        String shipmentSelector = String.format(getXpathIDMap().get("gbParcelsShipmentServiceDeliveryType"), shipmentTypes[index]);
        getDriver().findElement(By.cssSelector(shipmentSelector)).click();
        getCommonMethod().waitForAction(200);
    }

    public void userClicksOnGBFindFlights() {
        getDriver().switchTo().activeElement();
        getCommonMethod().waitUntilCssSelectorElementIsClickable(getXpathIDMap().get("gbParcelViewShippingOptions"));
    }

    public void userSelectOneFlightStandard() {
        // Wait for flight search dialog goes away
        getCommonMethod().waitUntilElementIsClickable(getXpathIDMap().get("gbShipmentFlightSearch"));
        getCommonMethod().waitUntilElementIsClickable(getXpathIDMap().get("gbShipmentFlightSearchResults"));
        // Wait for the flight search results to be displayed
        getCommonMethod().waitForAction(2000);
        getDriver().findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightStandardSelection"))).click();
    }

    public void userWaitsToFillCargoShipmentRequest() {
        WebElement chargesConfirmationPage = getDriver().findElement(By.xpath(getXpathIDMap().get("gbShipmentChargeConfirmation")));
        if (chargesConfirmationPage == null) {
            waitForLoaderProgressBar();
        }
    }

    public void userSelectOneFlightDash() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        getCommonMethod().waitForAction(2000);
        getDriver().findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightDashSelection"))).click();
    }
    public void userSelectOneFlightDashCritical() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        getCommonMethod().waitForAction(2000);
        getDriver().findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightDashCriticalSelection"))).click();
    }
}
