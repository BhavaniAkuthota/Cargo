package com.delta.pageobjects.common;

import com.delta.util.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DatePicker {
    private final WebDriver driver;
    private final Map<String, String> xpathIDMap;

    public DatePicker(WebDriver driver,Map<String, String> xpathIDMap) {
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void openCalendar(String calendarSelector, String inputSelector, String dateOpenerSelector) {
        if (!isCalendarOpen(calendarSelector)) {
            CommonMethod commonMethod = new CommonMethod(driver);
            // First enable the date input field
            WebElement dateElement = driver.findElement(By.cssSelector(this.xpathIDMap.get(inputSelector)));
            dateElement.sendKeys(Keys.TAB);
            commonMethod.waitForAction(2000);
            // Now perform a click to open the Calendar
            driver.findElement(By.cssSelector(this.xpathIDMap.get(dateOpenerSelector))).click();
            commonMethod.waitForAction(2000);
        }
    }

    private boolean isCalendarOpen(String calendarSelector) {
        return driver.findElement(By.id(this.xpathIDMap.get(calendarSelector))).isDisplayed();
    }

    public void selectDate(String nextMonthSelector, String dateSelector) {
        Date bookingDate = getRandomDayInNextTwoWeeks();

        // Determine if clicking on next month is needed and navigate to next month
        By nextMonth = By.cssSelector(this.xpathIDMap.get(nextMonthSelector));
        if (isNavigateToNextMonth(bookingDate)) {
            driver.findElement(nextMonth).click();
            new CommonMethod(driver).waitForAction(2000);
        }

        // Select Date on Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bookingDate);
        String month = Integer.toString(calendar.get(Calendar.MONTH));
        String date = Integer.toString(calendar.get(Calendar.DATE));
        String dateSelectorPath = String.format(this.xpathIDMap.get(dateSelector), month, date);
        driver.findElement(By.xpath(dateSelectorPath)).click();
    }

    private boolean isNavigateToNextMonth(Date futureDate) {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        Calendar future = Calendar.getInstance();
        future.setTime(futureDate);

        return today.get(Calendar.MONTH) != future.get(Calendar.MONTH);
    }

    private Date getRandomDayInNextTwoWeeks() {
        Calendar c = Calendar.getInstance();
        Date today = new Date();
        c.setTime(today);
        // Including TODAY total will be 14 days
        c.add(Calendar.DATE, 13);
        Date afterTwoWeeks = c.getTime();
        return new Date(ThreadLocalRandom.current().nextLong(today.getTime(), afterTwoWeeks.getTime()));
    }
}
