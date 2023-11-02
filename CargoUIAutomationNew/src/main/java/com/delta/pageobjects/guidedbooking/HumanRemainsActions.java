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

public class HumanRemainsActions extends CommonMethod {
    private final Map<String, String> xpathIDMap;

    WebDriver driver;

    public HumanRemainsActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void userClickOnGBHumanRemainsShipmentDatePicker() {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar("gbParcelsShipmentDateCalendar", "gbHumanRemainsShipmentDateInput", "gbHumanRemainsShipmentDateOpener");
        datePicker.selectDate("gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(500);
    }

    public void userSelectGBHumanRemainsDepartureTime() {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        WebElement selectElement = driver.findElement(By.cssSelector(getXpathIDMap().get("gbHumanRemainsDepartureTimeSelector")));
        selectElement.click();
        waitForAction(200);

        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        waitForAction(200);
    }

    public void userSelectGBHumanRemainsType(int humanremainSelectIndex) {
        WebElement selectElement = driver.findElement(By.cssSelector(getXpathIDMap().get("gbHumanRemainsTypeSelector")));
        Select hrRemainsTypeSelect = new Select(selectElement);
        hrRemainsTypeSelect.selectByIndex(humanremainSelectIndex);
        waitForAction(200);
    }

    public void userSelectAirContainerType() {
        final String[] airContainerTypes = {"Air Tray", "Casket", "Combo", "Casket with Air Tray", "Cremains"};
        WebElement selectElement = driver.findElement(By.cssSelector(getXpathIDMap().get("gbHumanRemainsSelectAirContainerType")));
        Select airContainer = new Select(selectElement);
        int index = new Random().nextInt(airContainerTypes.length);
        airContainer.selectByVisibleText(airContainerTypes[index]);
        waitForAction(200);
    }
    
    public void userClicksOnGBHumanRemainsFindFlights(String shipmentType) {
        scrollDownLittle();
        waitForAction(200);
        driver.switchTo().activeElement();
        waitUntilCssSelectorElementIsClickable(getXpathIDMap().get("gbHumanRemainsFindFlight"));
    }
}
