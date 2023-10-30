package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import com.delta.util.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.delta.util.xlMapper.getXpathIDMap;

public class ValuablesActions extends CommonMethod {
    private final Map<String, String> xpathIDMap;

    WebDriver driver;

    public ValuablesActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void userClickOnGBValuablesShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbValuablesShipmentDateInput", "gbValuablesShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(500);
    }



    public void userClickOnGBValuablesShipmentDatePicker(String prefix) {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbValuablesShipmentDateInput", "gbValuablesShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(500);
    }

    public void userSelectGBValuablesDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        WebElement selectElement = driver.findElement(By.cssSelector(getXpathIDMap().get("gbValuablesDepartureTimeSelector")));
        selectElement.click();
        waitForAction(200);

        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        waitForAction(200);
    }
    public void userSelectGBValuableType(int valuableSelectIndex, int valuableIndex) {
        String unitSelector = String.format(getXpathIDMap().get("gbValuablesTypeSelector"), valuableIndex);
        WebElement selectElement = driver.findElement(By.cssSelector(unitSelector));
        selectElement.click();
        waitForAction(200);

        Select pharmaTypeSelect = new Select(selectElement);
        pharmaTypeSelect.selectByIndex(valuableSelectIndex);
        waitForAction(200);
    }
    public void userClicksOnGBValuableFindFlights() {
        scrollDownLittle();
        waitForAction(200);
        driver.switchTo().activeElement();
        waitUntilCssSelectorElementIsClickable(getXpathIDMap().get("gbValuablesFindFlight"));
    }
    public void userSelectOneSafeFlight() {
        // Wait for flight search dialog goes away
        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearch"));
        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearchResults"));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbSafeFlightSelector"))).click();
    }
}
