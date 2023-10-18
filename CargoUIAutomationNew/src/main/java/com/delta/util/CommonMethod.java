package com.delta.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class CommonMethod {
    private final WebDriver driver;
    private final WebDriverWait wait;

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

    public void waitForLoaderElement(String xlWebElement) {
        By by = By.cssSelector(xlWebElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
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
        for (WebElement webShipmentContain : webShipmentContains) {
            Select selectElement = new Select(webShipmentContain);
            for (int j = 0; j < selectElement.getOptions().size(); j++) {
                String webShipmentValue = selectElement.getOptions().get(j).getText();
                if (webShipmentValue.equalsIgnoreCase(dropDownValue)) {
                    selectElement.selectByValue(webShipmentValue);
                    break;
                }
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

    public void acceptPopUpButton(String xpathDialog, String xpathOk) {
        Wait<WebDriver> unitChangeWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        List<WebElement> elements = unitChangeWait.until(new Function<WebDriver, List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(By.xpath(xpathDialog));
            }
        });

        if (!elements.isEmpty()) {
            isElementVisible(By.xpath(xpathOk));
            driver.findElement(By.xpath(xpathOk)).click();
            waitForAction(200);
        }
    }

    public void closeBanner() {
        isElementVisible(By.cssSelector(".bannerClose"));
        driver.findElement(By.cssSelector(".bannerClose")).click();
    }

    public void scrollDownLittle() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0, 500)", "");
    }
}
