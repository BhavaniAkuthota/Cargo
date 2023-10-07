package com.delta.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Login extends  CommonMethod{

    ExcelRead excelRead = new ExcelRead();
    String xpathDataFile = "src/test/resources/XpathTestData.xlsx";
    Map<String, String> xpathIDMap = excelRead.readXPathData(xpathDataFile);

    public WebDriver driver;


    public Login(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }


    public Map<String, String> getXpathIDMap() {

        return xpathIDMap;
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

    public Map<String, Object> excelReadTestData(String workSheetName, String testCaseName) {
        Map<String, Object> eachRowMap = new HashMap<>();
        String inputTestFile = "src/test/resources/TestCasesMapping.xlsx";

        List<String> getTestCaseMasterSheet = excelRead.getTestCaseMasterSheet(inputTestFile);
        Map<String, List<Map<String, Object>>> readInputTestCaseFile = excelRead.readInputTestCaseFile(inputTestFile, getTestCaseMasterSheet);

        for (Map.Entry<String, List<Map<String, Object>>> entry : readInputTestCaseFile.entrySet()) {
            String keyHeading = entry.getKey();
            // String KeyValue = entry.getValue().toString();
            List<Map<String, Object>> TestCasesValue = entry.getValue();
            //System.out.println("TestCasesValue- - - "+TestCasesValue);

            if (keyHeading.equalsIgnoreCase(workSheetName)) {// passing the work sheet name example ABGeneralCargo
                for (Map<String, Object> stringObjectMap : TestCasesValue) {
                    eachRowMap = stringObjectMap;
                    String xlTestCaseName = eachRowMap.get("TestCases").toString();
                    System.out.println("xlTestCaseName- - " + xlTestCaseName);
                    if (xlTestCaseName.equalsIgnoreCase(testCaseName)) {
//                        eachRowMap = stringObjectMap;
                        System.out.println(eachRowMap);
                        System.out.println("Testcase- - -" + eachRowMap.get("TestCases"));
                        System.out.println("ShipmentContains- - --" + eachRowMap.get("ShipmentContains"));
                        break;
                    }
                }
                // System.out.println("keyHeading - - -"+keyHeading+" /n"+ "KeyValue- - - "+ KeyValue);
                // if(TestCasesValue.get(1)) {
            }
        }
        return eachRowMap;
    }

}




