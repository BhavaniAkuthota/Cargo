package com.delta.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Login extends CommonMethod {


    public WebDriver driver;
    private final Map<String, String> xpathIDMap;

    public Login(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }


    public void userLoginAsDomesticAccount(String loginUsername, String loginPassword) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            String weburl = driver.getCurrentUrl();
            System.out.println("webTitle- - - -" + weburl);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("CargoAppLogin"))));
            WebElement l = driver.findElement(By.cssSelector(xpathIDMap.get("CargoAppLogin")));
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("arguments[0].click();", l);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("CargoUsernameLogin"))));
            WebElement a = driver.findElement(By.cssSelector(xpathIDMap.get("CargoUsernameLogin")));
            JavascriptExecutor b = (JavascriptExecutor) driver;
            b.executeScript("arguments[0].click();", a);
            a.sendKeys(loginUsername);


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("CargoUsernamepassword"))));
            WebElement c = driver.findElement(By.cssSelector(xpathIDMap.get("CargoUsernamepassword")));
            JavascriptExecutor d = (JavascriptExecutor) driver;
            d.executeScript("arguments[0].click();", c);
            c.sendKeys(loginPassword);


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("LoginSubmitButton"))));
            WebElement e = driver.findElement(By.cssSelector(xpathIDMap.get("LoginSubmitButton")));
            JavascriptExecutor f = (JavascriptExecutor) driver;
            f.executeScript("arguments[0].click();", e);
            Thread.sleep(5000);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public void switchAccountType(String xlAccountType, String xlAccountNumber) {


        waitForAction(4000);
        System.out.println("Switching to " + xlAccountType+" : "+xlAccountNumber);
        new Actions(driver).clickAndHold(driver.findElement(By.cssSelector("#desktopUserDropdown .dc-selected-account"))).pause(Duration.ofMillis(1000)).perform();
        driver.findElement(By.cssSelector("#desktopUserDropdown .dc-selected-account")).click();
        driver.findElement(By.cssSelector("label[for='searchDesktop']")).click();
        driver.findElement(By.cssSelector("#searchDesktop")).sendKeys(xlAccountNumber);
        waitForAction(2000);
        driver.findElement(By.cssSelector(xpathIDMap.get("accountSearchButton"))).click();
        waitForAction(2000);
        driver.findElement(By.cssSelector(" div div ul li:nth-child(1) div ul li.ng-scope a")).click();

    }
}




