package com.delta.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;


public class Login {

    ExcelRead excelRead = new ExcelRead();
    public WebDriver driver;

    //Map<String,String> xpathIDMap=new LinkedHashMap<String,String>();
    String xpathDataFile = "src/test/resources/XpathTestData.xlsx";
    /* String inputTestFile="/Users/344518/IdeaProjects/CargoUIAutomation/src/test/resources/TestCasesMapping.xlsx";

     Map<String, Map<String, Object>> getConfigurationSheet=excelRead.getConfigurationSheet(inputTestFile);
     List<String> getTestCaseMasterSheet=excelRead.getTestCaseMasterSheet(inputTestFile);
     Map<String, List<Map<String, Object>>> readInputTestCaseFile=excelRead.readInputTestCaseFile(inputTestFile,getTestCaseMasterSheet);*/
    Map<String, String> xpathIDMap = excelRead.readXPathData(xpathDataFile);
public  Map<String, String> getXpathIDMap(){

    return xpathIDMap;
}



    public void userLoginAsDomesticAccount(WebDriver driver, String loginUsername, String loginPassword) {
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



           /* wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("CargoAppLogin")))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("CargoUsernameLogin")))).sendKeys(loginUsername);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("CargoUsernamepassword")))).sendKeys(loginPassword);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(xpathIDMap.get("LoginSubmitButton")))).click();*/
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}




