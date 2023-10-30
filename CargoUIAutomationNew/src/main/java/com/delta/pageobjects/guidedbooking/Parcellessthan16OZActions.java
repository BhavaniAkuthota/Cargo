package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import com.delta.util.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;
import java.util.Random;

import static com.delta.util.xlMapper.getXpathIDMap;

public class Parcellessthan16OZActions extends CommonMethod {
    private final Map<String, String> xpathIDMap;

    WebDriver driver;

    public Parcellessthan16OZActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }
    public void userClickOnGBParcellessthanShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbParcellessthanShipmentDateInput", "gbParcellessthanShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(2000);
    }

    public void userSelectGBParcellessthanDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        Select departureTime = new Select(driver.findElement(By.cssSelector(getXpathIDMap().get("gbParcellessthanDepartureTimeSelector"))));
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        waitForAction(200);
    }

    public void userSelectGBParcellessthanDeliveryType() {
        final String[] shipmentTypes = {"documentSelectedPickupDeliveryOptionCode0", "documentSelectedPickupDeliveryOptionCode1", "documentSelectedPickupDeliveryOptionCode2", "documentSelectedPickupDeliveryOptionCode3"};
        int index = 3;
        String shipmentSelector = String.format(getXpathIDMap().get("gbParcellessthanShipmentServiceDeliveryType"), shipmentTypes[index]);
        driver.findElement(By.cssSelector(shipmentSelector)).click();
        waitForAction(200);
    }

    public void userClicksOnGBParcellessthanFindFlights(String shipmentType) {
        driver.switchTo().activeElement();
        waitUntilCssSelectorElementIsClickable(getXpathIDMap().get("gbParcellessthanViewShippingOptions"));
    }
}
