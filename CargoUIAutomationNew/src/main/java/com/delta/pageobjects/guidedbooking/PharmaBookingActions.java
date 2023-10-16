package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class PharmaBookingActions extends GBActions {

    public PharmaBookingActions(WebDriver driver) {
        super(driver);
    }

    public void userClickOnGBPharmaShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(getDriver(), getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbPharmaShipmentDateInput", "gbPharmaShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        getCommonMethod().waitForAction(500);
    }

    public void userSelectGBPharmaDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        WebElement selectElement = getDriver().findElement(By.cssSelector(getXpathIDMap().get("gbPharmaDepartureTimeSelector")));
        selectElement.click();
        getCommonMethod().waitForAction(200);

        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        getCommonMethod().waitForAction(200);
    }

    public void userSelectGBPharmaType(int pharmaSelectIndex, int pharmaIndex) {
        String unitSelector = String.format(getXpathIDMap().get("gbPharmaTypeSelector"), pharmaIndex);
        WebElement selectElement = getDriver().findElement(By.cssSelector(unitSelector));
        selectElement.click();
        getCommonMethod().waitForAction(200);

        Select pharmaTypeSelect = new Select(selectElement);
        pharmaTypeSelect.selectByIndex(pharmaSelectIndex);
        getCommonMethod().waitForAction(200);
    }

    public void userSelectContainerType() {
        final String[] containerTypes = {"CSafe RAP", "CSafe RKN", "Envirotainer RAP e2", "Envirotainer RAP t2", "Envirotainer RKN e1", "Envirotainer RKN t2", "PharmaPort 360"};
        WebElement selectElement = getDriver().findElement(By.cssSelector(getXpathIDMap().get("gbPharmaSelectContainerType")));
        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(containerTypes.length);
        departureTime.selectByValue(containerTypes[index]);
        getCommonMethod().waitForAction(200);
    }

    public void userEnterTotalNumberOfContainers(String xlTotalNumberOfContainers) {
        getCommonMethod().sendkeysUsingCssSelector(getXpathIDMap().get("gbPharmaTotalContainers"), xlTotalNumberOfContainers, Keys.TAB);
        getCommonMethod().waitForAction(200);
    }

    public void userEnterTotalContainersWeight(String xlTotalContainersWeight) {
        getCommonMethod().sendkeysUsingCssSelector(getXpathIDMap().get("gbPharmaContainersTotalWeight"), xlTotalContainersWeight, Keys.TAB);
        getCommonMethod().waitForAction(200);
    }

    public void userClicksOnGBFindFlights() {
        getCommonMethod().scrollDownLittle();
        getCommonMethod().waitForAction(200);

        List<WebElement> elements = getDriver().findElements(By.cssSelector(getXpathIDMap().get("gbPharmaFindFlights")));
        Assert.assertEquals(elements.size(), 2);
        WebElement findFlightElement = elements.get(1);
        findFlightElement.click();
    }

    public void userSelectOneFlight() {
        // Wait for flight search dialog goes away
        getCommonMethod().waitUntilElementIsClickable(getXpathIDMap().get("gbShipmentFlightSearch"));
        getCommonMethod().waitUntilElementIsClickable(getXpathIDMap().get("gbShipmentFlightSearchResults"));
        getCommonMethod().waitForAction(2000);
        getDriver().findElement(By.cssSelector(getXpathIDMap().get("gbPharmaFlightSelector"))).click();
    }
}
