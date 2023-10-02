package com.delta.util;

import com.aventstack.extentreports.Status;
import com.sun.jdi.Value;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethod {

    WebDriver driver;


    public void waitUntilCssSelectorElementIsClickable(WebDriver driver, String xlWebElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xlWebElement)));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector(xlWebElement));
        executor.executeScript("arguments[0].click();", element);


    }

    public void waitUntilXpathElementIsClickable(WebDriver driver, String xlWebElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xlWebElement)));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(xlWebElement));
        executor.executeScript("arguments[0].click();", element);


    }


    public void sendkeysUsingXpath(WebDriver driver, String xlWebElement, String sendKeyValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xlWebElement)));
        driver.findElement(By.xpath(xlWebElement)).sendKeys(sendKeyValue);
        driver.findElement(By.xpath(xlWebElement)).sendKeys(Keys.ENTER);
        waitForAction(200);

//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xlWebElement)));
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.cssSelector(xlWebElement));
//        executor.executeScript("arguments[0].value='" + sendKeyValue + "';", element);
//        executor.executeScript("arguments[0].click();", element);
    }

    public void sendkeysUsingCssSelector(WebDriver driver, String xlWebElement, String sendKeyValue)  {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xlWebElement)));
        driver.findElement(By.cssSelector(xlWebElement)).sendKeys(sendKeyValue);
        driver.findElement(By.cssSelector(xlWebElement)).sendKeys(Keys.ENTER);
        waitForAction(200);



//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.cssSelector(xlWebElement));
//        executor.executeScript("arguments[0].value='" + sendKeyValue + "';", element);
//       // executor.executeScript("arguments[0].click();", element);
//        element.click();
//
//
//     /*  // WebElement element = driver.findElement(By.xpath("xpath"));
//        Actions action = new Actions(driver);
//        action.moveToElement(element).build().perform();
//        action.moveToElement(element).click();*/
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }


    public void ClickOnRadiobutton(WebDriver driver, String xlWebElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xlWebElement)));
        driver.findElement(By.cssSelector(xlWebElement)).click();
        //driver.findElement(By.cssSelector(xlWebElement)).sendKeys(Keys.ENTER);
        waitForAction(200);
    }


    public void waitForPageLoad(WebDriver driver) {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        } catch (Exception e) {
            e.printStackTrace();
            //Report.getLogger().log(Status.FAIL, "Exception occured while waiting for page load", var2);
          //  Reporter.log(Status.FAIL, "user logged In Successfully");
        }

    }

    public void selectDropdownUsingCssSelector(WebDriver driver,String element ,String  dropDownValue){
        List<WebElement> webShipmentContains = driver.findElements(By.cssSelector(element));
        for (int i = 0; webShipmentContains.size() > i; i++) {
            String webShipmentValue = webShipmentContains.get(i).getText().toString();
            if (webShipmentValue.equalsIgnoreCase(dropDownValue)) {
                webShipmentContains.get(i).click();
            }
        }
    }

    public void selectDropdownUsingXpath(WebDriver driver,String element ,String  dropDownValue){
        List<WebElement> webShipmentContains = driver.findElements(By.xpath(element));
        for (int i = 0; webShipmentContains.size() > i; i++) {
            String webShipmentValue = webShipmentContains.get(i).getText().toString();
            if (webShipmentValue.equalsIgnoreCase(dropDownValue)) {
                webShipmentContains.get(i).click();
            }
        }
    }
    public void waitForAction(int wait){
        try {
            Thread.sleep(wait);
        }
        catch (Exception e){

        }
    }
    public boolean verifyElementIsDisplayed(WebDriver driver,String xlWebElement){

        boolean visiblityofelement=false;
        try {
            visiblityofelement= driver.findElement(By.xpath(xlWebElement)).isDisplayed();
        }
        catch (Exception e){

        }
        return visiblityofelement;
    }
//    public void shippingDate(WebDriver driver, String dayValue ){
//        boolean lastVisibleDay;
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
//        String date = dtf.format(now); // 2023/09/29
//        String[] day = date.split("/");
//        int currentDay = Integer.valueOf(day[2]);
//        if(currentDay>0 && currentDay<=27 ){
//            currentDay = currentDay + Integer.valueOf(dayValue);
//        } else if (currentDay>27) {
//        if (verifyElementIsDisplayed(driver,"//td/a[text()='31']")){
//            currentDay = currentDay + Integer.valueOf(dayValue);
//        } else if (verifyElementIsDisplayed(driver,"//td/a[text()='30']")) {
//            currentDay = currentDay + Integer.valueOf(dayValue);
//        } else if (verifyElementIsDisplayed(driver,"//td/a[text()='29']")) {
//            currentDay = currentDay + Integer.valueOf(dayValue);
//        }
//        else {
//            waitUntilXpathElementIsClickable(driver,"//span[contains (text(),'Next Month')]");
//            currentDay = 1;
//        }
//        }
//        waitUntilXpathElementIsClickable(driver,"//td/a[text()='"+currentDay+"']");
//
//
//
//    }
}
