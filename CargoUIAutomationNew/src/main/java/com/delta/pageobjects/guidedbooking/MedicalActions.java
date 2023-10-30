package com.delta.pageobjects.guidedbooking;

import com.delta.util.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;
import java.util.Random;

public class MedicalActions extends CommonMethod {

    private final Map<String, String> xpathIDMap;
    WebDriver driver;

    public MedicalActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void userSelectGBMedicalType(int pharmaSelectIndex, int i) {

        WebElement selectElement = driver.findElement(By.cssSelector(xpathIDMap.get("gbMedicalTypeSelector")));
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
