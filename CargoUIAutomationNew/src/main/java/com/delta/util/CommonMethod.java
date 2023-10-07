package com.delta.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommonMethod {

    WebDriver driver;
    WebDriverWait wait;

    public CommonMethod(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }



    public void waitUntilElementIsClickable(String xlWebElement) {
        By by = selectLocator(xlWebElement);
        isElementVisible(by);
        jseClick(by);
    }


    public void waitUntilCssSelectorElementIsClickable(String xlWebElement) {
        By by = By.cssSelector(xlWebElement);
        isElementVisible(by);
        jseClick(by);
    }

    private void isElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitUntilElementVisible(String xlWebElement) {
        By by = By.cssSelector(xlWebElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public void waitUntilXpathElementIsClickable(String xlWebElement) {
        By by = By.xpath(xlWebElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        jseClick(by);
    }

    public void sendkeysUsingXpath(String xlWebElement, String sendKeyValue, Keys specialKey) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xlWebElement)));
        driver.findElement(By.xpath(xlWebElement)).sendKeys(sendKeyValue);
        driver.findElement(By.xpath(xlWebElement)).sendKeys(specialKey);
        waitForAction(200);
    }


    public void sendkeysUsingXpath(String xlWebElement, String sendKeyValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xlWebElement)));
        driver.findElement(By.xpath(xlWebElement)).sendKeys(sendKeyValue);
        driver.findElement(By.xpath(xlWebElement)).sendKeys(Keys.ENTER);
        waitForAction(200);

    }

    public void sendkeysUsingCssSelector(String xlWebElement, String sendKeyValue, Keys specialKey) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xlWebElement)));
        driver.findElement(By.cssSelector(xlWebElement)).sendKeys(sendKeyValue);
        driver.findElement(By.cssSelector(xlWebElement)).sendKeys(specialKey);
        waitForAction(200);
    }

    public void sendkeysUsingCssSelector(String xlWebElement, String sendKeyValue) {


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xlWebElement)));
        driver.findElement(By.cssSelector(xlWebElement)).sendKeys(sendKeyValue);
        driver.findElement(By.cssSelector(xlWebElement)).sendKeys(Keys.ENTER);
        waitForAction(200);
    }


    public void ClickOnRadiobutton(String xlWebElement) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xlWebElement)));
        driver.findElement(By.cssSelector(xlWebElement)).click();
        waitForAction(200);
    }


    public void waitForPageLoad(WebDriver driver) {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void selectDropdownUsingCssSelector(String element, String dropDownValue) {
        List<WebElement> webShipmentContains = driver.findElements(By.cssSelector(element));
        for (int i = 0; webShipmentContains.size() > i; i++) {
            String webShipmentValue = webShipmentContains.get(i).getText();
            if (webShipmentValue.equalsIgnoreCase(dropDownValue)) {
                webShipmentContains.get(i).click();
            }
        }
    }

    public void selectDropdownUsingXpath(String element, String dropDownValue) {
        List<WebElement> webShipmentContains = driver.findElements(By.xpath(element));
        for (int i = 0; webShipmentContains.size() > i; i++) {
            String webShipmentValue = webShipmentContains.get(i).getText().toString();
            if (webShipmentValue.equalsIgnoreCase(dropDownValue)) {
                webShipmentContains.get(i).click();
            }
        }
    }

    public void waitForAction(int wait) {
        try {
            Thread.sleep(wait);
        } catch (Exception e) {

        }
    }

    public boolean verifyElementIsDisplayed(String xlWebElement) {

        boolean visiblityofelement = false;
        try {
            visiblityofelement = driver.findElement(By.xpath(xlWebElement)).isDisplayed();
        } catch (Exception e) {

        }
        return visiblityofelement;
    }

    private void jseClick(By by) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(by);
        executor.executeScript("arguments[0].click();", element);
    }

    private By selectLocator(String xlWebElement) {
        By by;
        if (xlWebElement.contains("//"))
            by = By.xpath(xlWebElement);
        else
            by = By.cssSelector(xlWebElement);
        return by;
    }


    public void acceptPopUpButton() {
        isElementVisible(By.xpath("//button[text()='OK']"));
        driver.findElement(By.xpath("//button[text()='OK']")).click();
    }


    public void closeBanner() {
        isElementVisible(By.cssSelector(".bannerClose"));
        driver.findElement(By.cssSelector(".bannerClose")).click();


    }
}