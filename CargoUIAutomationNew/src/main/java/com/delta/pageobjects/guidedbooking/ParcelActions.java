package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;
import java.util.Random;

import static org.testng.AssertJUnit.fail;

public class ParcelActions extends GBActions {

    WebDriver driver;
    private final Map<String, String> xpathIDMap;

    public ParcelActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver, xpathIDMap);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void userClickOnGBParcelShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbParcelsShipmentDateInput", "gbParcelsShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(2000);
    }

    public void userSelectGBParcelDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        Select departureTime = new Select(driver.findElement(By.cssSelector(getXpathIDMap().get("gbParcelsDepartureTimeSelector"))));
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        waitForAction(200);
    }

    public void userSelectDeliveryType() {
        final String[] shipmentTypes = {"generalSelectedPickupDeliveryOptionCode0", "generalSelectedPickupDeliveryOptionCode1", "generalSelectedPickupDeliveryOptionCode2", "generalSelectedPickupDeliveryOptionCode3"};
        int index = 3;
        String shipmentSelector = String.format(getXpathIDMap().get("gbParcelsShipmentServiceDeliveryType"), shipmentTypes[index]);
        driver.findElement(By.cssSelector(shipmentSelector)).click();
        waitForAction(200);
    }

    public void userClicksOnGBFindFlights(String shipmentType) {
        driver.switchTo().activeElement();
        waitUntilCssSelectorElementIsClickable(getXpathIDMap().get("gbParcelViewShippingOptions"));
    }

    public void userSelectOneFlightStandard() {
        // Wait for flight search dialog goes away
//        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearch"));
        waitForAction(6000);
        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearchResults"));
        // Wait for the flight search results to be displayed
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightStandardSelection"))).click();
    }

    public void userWaitsToFillCargoShipmentRequest() {
        WebElement chargesConfirmationPage = driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentChargeConfirmation")));
        if (chargesConfirmationPage == null) {
            waitForLoaderProgressBar();
        }
    }

    public void userSelectOneFlightDash() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightDashSelection"))).click();
    }

    public void userSelectOneFlightDashCritical() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightDashCriticalSelection"))).click();
    }

    public void userSelectOneFlightGeneral() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightGeneralSelection"))).click();
    }

    public void userSelectOneFlightExpress() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightExpressSelection"))).click();
    }

    public void userSelectOneFlightCritical() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightCriticalSelection"))).click();
    }

    public void userSelectOneFlight(String flightOption ) {


        switch (flightOption) {
            case "STANDARD":
                if (flightValidations("gbShipmentFlightStandardSelection"))
                userSelectOneFlightStandard();
                break;
            case "DASH":
                if (flightValidations("gbShipmentFlightDashSelection"))
                userSelectOneFlightDash();
                break;
            case "DASH_CRITICAL":
                if (flightValidations("gbShipmentFlightDashCriticalSelection"))
                userSelectOneFlightDashCritical();
                break;
            case "GENERAL":
                if (flightValidations("gbShipmentFlightGeneralSelection"))
                userSelectOneFlightGeneral();
                break;
            case "EXPRESS":
                if (flightValidations("gbShipmentFlightExpressSelection"))
                userSelectOneFlightExpress();
                break;
            case "CRITICAL":
                if (flightValidations("gbShipmentFlightCriticalSelection"))
                userSelectOneFlightCritical();
                break;
            default:
                fail("Invalid flight option is provided");
        }
    }

    public boolean flightValidations(String val) {
        int size = driver.findElements(By.xpath(getXpathIDMap().get("gbPharmaFlightSelectorNoRoute"))).size();
        String outcome;
        if (size > 0) {
            outcome = driver.findElement(By.xpath(getXpathIDMap().get("gbPharmaFlightSelectorNoRoute"))).getText();
            Assert.assertNotEquals("There are no available routes based on your search criteria. For additional assistance, please contact the Cargo Customer Service Center at 1-800-DL-CARGO (1-800-352-2746).", outcome);
        }
        isElementVisible(By.cssSelector(getXpathIDMap().get(val)));
        outcome = driver.findElement(By.cssSelector(getXpathIDMap().get(val))).getText();
        Assert.assertNotEquals("Product not available for selection", outcome);
        return true;
    }
}
