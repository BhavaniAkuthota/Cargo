package com.delta.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class BaseTest {
    ExcelRead excelRead = new ExcelRead();
/*
    String inputTestFile = "/Users/344518/IdeaProjects/CargoUIAutomation/src/test/resources/TestCasesMapping.xlsx";
    String xpathDataFile = "/Users/344518/IdeaProjects/CargoUIAutomation/src/test/resources/XpathTestData.xlsx";*/
    public WebDriver driver;
    public static String screenshotsSubFolderName;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;



   /* @Parameters({"browser"})
    @BeforeTest
    public void setup(String browerName) {

        switch (browerName) {

            case "chrome":
                ChromeOptions opt=new ChromeOptions();
                opt.addArguments("--headless=new");
                driver = new ChromeDriver(opt);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edgedriver":
                driver = new EdgeDriver();
                break;

            default:
                System.out.println("pass the right browser name");
                break;
        }


        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://si.deltacargo.com/Cargo/");
    }
*/


    @Parameters("browser")
    @BeforeTest
    public void setup(ITestContext context, @Optional("chrome") String browserName) {
        try {
            switch (browserName.toLowerCase()) {
                case "chrome":
               /* ChromeOptions opt=new ChromeOptions();
                opt.addArguments("--headless=new");
                driver = new ChromeDriver(opt);*/
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "edgedriver":
                    driver = new EdgeDriver();
                    break;

                default:
                    System.out.println("pass the right browser name");
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://si.deltacargo.com/Cargo/");
            Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
            String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
            String author = context.getCurrentXmlTest().getParameter("author");
            String SIENV = context.getCurrentXmlTest().getParameter("url");

            extentTest = extentReports.createTest(context.getName());
            extentTest.assignAuthor("Author: " + author);
            extentTest.assignDevice("Device: " + device);
            extentTest.assignCategory("URL: " + SIENV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void teardown() {
       // driver.quit();
    }

    @BeforeSuite
    public void initialiseExtentReports() {
        ExtentSparkReporter sparkReporterAll = new ExtentSparkReporter("test-output/ExtentReport/AllTests.html");
        sparkReporterAll.config().setReportName("Delta Cargo Report");
       // sparkReporterAll.config().getTheme();


        ExtentSparkReporter sparkReporterFailed = new ExtentSparkReporter("test-output/ExtentReport/FailedTests.html");
        sparkReporterFailed.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
        sparkReporterFailed.config().setReportName("Failure report");

        ExtentSparkReporter sparkReporterSkipped= new ExtentSparkReporter("test-output/ExtentReport/skippedTests.html");
        sparkReporterFailed.filter().statusFilter().as(new Status[] {Status.SKIP}).apply();
        sparkReporterFailed.config().setReportName("Skipped report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporterAll, sparkReporterFailed,sparkReporterSkipped);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @AfterSuite
    public void generateExtentReports() throws Exception {
        extentReports.flush();
       /* Desktop.getDesktop().browse(new File("AllTests.html").toURI());
        Desktop.getDesktop().browse(new File("FailedTests.html").toURI());*/
    }

    @AfterMethod
    public void checkStatus(Method m, ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = null;
            screenshotPath = captureScreenshot(result.getTestContext().getName()+ "_" +result.getMethod().getMethodName()+".jpg");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.fail(result.getThrowable());
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass(m.getName() + " is passed");
        }

        extentTest.assignCategory(m.getAnnotation(Test.class).groups());
    }

    public String captureScreenshot(String fileName) {
        if(screenshotsSubFolderName == null) {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            screenshotsSubFolderName = myDateObj.format(myFormatObj);
        }

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./Screenshots/"+ screenshotsSubFolderName+"/"+fileName);
        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot saved successfully");
        return destFile.getAbsolutePath();
    }
}
