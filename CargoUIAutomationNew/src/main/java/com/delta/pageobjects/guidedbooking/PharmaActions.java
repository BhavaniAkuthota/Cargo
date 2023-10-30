package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class PharmaActions extends GBActions {
    WebDriver driver;

    public PharmaActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver,xpathIDMap);
        this.driver = driver;
    }

    public void userClickOnGBPharmaShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbPharmaShipmentDateInput", "gbPharmaShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(500);
    }



    public void userClickOnGBPharmaShipmentDatePicker(String prefix) {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbPharmaShipmentDateInput", "gbPharmaShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(500);
    }

    public void userSelectGBPharmaDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        WebElement selectElement = driver.findElement(By.cssSelector(getXpathIDMap().get("gbPharmaDepartureTimeSelector")));
        selectElement.click();
        waitForAction(200);

        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        waitForAction(200);
    }

    public void userSelectGBPharmaType(int pharmaSelectIndex, int pharmaIndex) {
        String unitSelector = String.format(getXpathIDMap().get("gbPharmaTypeSelector"), pharmaIndex);
        WebElement selectElement = driver.findElement(By.cssSelector(unitSelector));
        selectElement.click();
        waitForAction(200);

        Select pharmaTypeSelect = new Select(selectElement);
        pharmaTypeSelect.selectByIndex(pharmaSelectIndex);
        waitForAction(200);
    }

    public void userSelectContainerType() {
        final String[] containerTypes = {"CSafe RAP", "CSafe RKN", "Envirotainer RAP e2", "Envirotainer RAP t2", "Envirotainer RKN e1", "Envirotainer RKN t2", "PharmaPort 360"};
        WebElement selectElement = driver.findElement(By.cssSelector(getXpathIDMap().get("gbPharmaSelectContainerType")));
        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(containerTypes.length);
        departureTime.selectByValue(containerTypes[index]);
        waitForAction(200);
    }

    public void userEnterTotalNumberOfContainers(String xlTotalNumberOfContainers) {
        sendkeysUsingCssSelector(getXpathIDMap().get("gbPharmaTotalContainers"), xlTotalNumberOfContainers, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterTotalContainersWeight(String xlTotalContainersWeight) {
        sendkeysUsingCssSelector(getXpathIDMap().get("gbPharmaContainersTotalWeight"), xlTotalContainersWeight, Keys.TAB);
        waitForAction(200);
    }

    public void userClicksOnGBFindFlights() {
        scrollDownLittle();
        waitForAction(200);

        List<WebElement> elements = driver.findElements(By.cssSelector(getXpathIDMap().get("gbPharmaFindFlights")));
        Assert.assertEquals(elements.size(), 2);
        WebElement findFlightElement = elements.get(1);
        findFlightElement.click();
    }

//    public void userSelectOneFlight() {
//        // Wait for flight search dialog goes away
//        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearch"));
//        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearchResults"));
//        waitForAction(2000);
//        driver.findElement(By.cssSelector(getXpathIDMap().get("gbPharmaFlightSelector"))).click();
//    }
}

