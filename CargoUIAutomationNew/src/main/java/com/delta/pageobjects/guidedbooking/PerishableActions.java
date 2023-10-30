package com.delta.pageobjects.guidedbooking;

import com.delta.util.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;
import java.util.Random;

public class PerishableActions extends CommonMethod {

    private final Map<String, String> xpathIDMap;
    WebDriver driver;

    public PerishableActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void userSelectGBPerishableType(int pharmaSelectIndex, int i) {
        String unitSelector = String.format(xpathIDMap.get("gbPerishableTypeSelector"), i);
        WebElement selectElement = driver.findElement(By.cssSelector(unitSelector));
        selectElement.click();
        waitForAction(200);

        Select pharmaTypeSelect = new Select(selectElement);
        pharmaTypeSelect.selectByIndex(pharmaSelectIndex);
        waitForAction(200);
    }

    public void userSelectCommodityType() {
        final String[] containerTypes = {"Pharmaceuticals", "Medical Supplies", "Chocolate", "Hatching eggs", "Seafood","Fish","Other"};
        WebElement selectElement = driver.findElement(By.cssSelector(xpathIDMap.get("gbPerishableCommodityTypeSelector")));
        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(containerTypes.length);
        departureTime.selectByValue(containerTypes[index]);
        waitForAction(200);
    }




}
