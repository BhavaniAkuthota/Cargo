package com.delta.pageobjects.guidedbooking;

import com.delta.util.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;
import java.util.Random;

public class PetLive extends CommonMethod {

    private final Map<String, String> xpathIDMap;
    WebDriver driver;

    public PetLive(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void userSelectPetTypeSelector(int selectIndex, int i) {
        String unitSelector = String.format(xpathIDMap.get("gbPetTypeSelector"), i);
        WebElement selectElement = driver.findElement(By.cssSelector(unitSelector));
//        selectElement.click();
        waitForAction(200);

        Select pharmaTypeSelect = new Select(selectElement);
        pharmaTypeSelect.selectByIndex(selectIndex);
        waitForAction(200);
    }

    public void userSelectCommodityType() {
        final String[] containerTypes = {"Pharmaceuticals", "Medical Supplies", "Chocolate", "Hatching eggs", "Seafood", "Fish", "Other"};
        WebElement selectElement = driver.findElement(By.cssSelector(xpathIDMap.get("gbPerishableCommodityTypeSelector")));
        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(containerTypes.length);
        departureTime.selectByValue(containerTypes[index]);
        waitForAction(200);
    }


    public void userSelectGBPetSubType(int index, int i) {
        String unitSelector = String.format(xpathIDMap.get("gbPetSubTypeSelector"), i);
        WebElement selectElement = driver.findElement(By.cssSelector(unitSelector));
        waitForAction(200);

        Select pharmaTypeSelect = new Select(selectElement);
        pharmaTypeSelect.selectByIndex(index);
        waitForAction(200);

    }

    public void userSelectPersonalHousholdType(String yes, int i) {
        if (yes.equalsIgnoreCase("YES")) {
            String unitSelector = String.format(xpathIDMap.get("personalHousholdYes"), i);
            driver.findElement(By.cssSelector(unitSelector)).click();
        } else {
            String unitSelector = String.format(xpathIDMap.get("personalHousholdNo"), i);
            driver.findElement(By.cssSelector(unitSelector)).click();
        }
    }

    public void userEnterBreedName(String breed, int i) {
        String unitSelector = String.format(xpathIDMap.get("pebreedName"), i);
        driver.findElement(By.cssSelector(unitSelector)).sendKeys(breed);
    }

    public void selectPetContainerDimension(String xlShipmentLength, String xlShipmentWidth, String xlShipmentHeight, String xlShipmentQuantityUnit, String xlShipmentWeight, String xlShipmentWeightUnits) {
        driver.findElement(By.cssSelector(".icon-Kennel")).click();
        driver.findElement(By.cssSelector("#kelength")).sendKeys(xlShipmentLength);
        driver.findElement(By.cssSelector("#kewidth")).sendKeys(xlShipmentWidth);
        driver.findElement(By.cssSelector("#keheight")).sendKeys(xlShipmentHeight);
        driver.findElement(By.cssSelector("#keKennelWeight")).sendKeys(xlShipmentWeight);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div[2]/button[2]")).click();
    }

    public void selectTermsAndCondition() {
        driver.findElement(By.cssSelector("label[for='pehealthInd-1']")).click();
    }
}
