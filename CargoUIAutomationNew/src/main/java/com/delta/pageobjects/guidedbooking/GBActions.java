package com.delta.pageobjects.guidedbooking;

import com.delta.pageobjects.common.DatePicker;
import com.delta.util.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.Map;
import java.util.Random;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

public class GBActions extends CommonMethod {
    private final WebDriver driver;
    private final Map<String, String> xpathIDMap;

    public GBActions(WebDriver driver, Map<String, String> xpathIDMap) {
        super(driver);
        this.driver = driver;
        this.xpathIDMap = xpathIDMap;
    }

    public void userSelectsGBShipmentType(String shipmentTypeSelector) {
        waitUntilXpathElementIsClickable(getXpathIDMap().get(shipmentTypeSelector));
    }

    public void userClicksOnTheGuidedBookingOption() {
        waitForAction(5000);
        waitUntilElementIsClickableAndClick(getXpathIDMap().get("abbookAShipment"));
        waitUntilXpathElementIsClickable(getXpathIDMap().get("gbGuided"));
        waitForAction(2000);
        closeBanner();
    }

    public void userEnterGBShipmentOrigin(String originSelector, String xlShipmentOrigin) {
        sendkeysUsingCssSelector(getXpathIDMap().get(originSelector), xlShipmentOrigin, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentOrigin(String prefix, String originSelector, String xlShipmentOrigin) {
        sendkeysUsingCssSelector((getXpathIDMap().get(originSelector)).replace("ph", prefix), xlShipmentOrigin, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentDestination(String destinationSelector, String xlShipmentDestination) {
        sendkeysUsingCssSelector(getXpathIDMap().get(destinationSelector), xlShipmentDestination, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentDestination(String prefix, String destinationSelector, String xlShipmentDestination) {
        sendkeysUsingCssSelector((getXpathIDMap().get(destinationSelector)).replace("ph", prefix), xlShipmentDestination, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentQuantity(String prefix, String shipmentQuantitySelector, String xlShipmentQuantity, int index) {
        String quantitySelector = String.format((xpathIDMap.get(shipmentQuantitySelector)).replace("ph", prefix), index);
        sendkeysUsingCssSelector(quantitySelector, xlShipmentQuantity, Keys.TAB);
        waitForAction(200);
    }
    public void userEnterGBShipmentQuantity(String shipmentQuantitySelector, String xlShipmentQuantity) {
        String quantitySelector = String.format((xpathIDMap.get(shipmentQuantitySelector)));
        sendkeysUsingCssSelector(quantitySelector, xlShipmentQuantity, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentLength(String prefix, String shipmentLengthSelector, String xlShipmentLength, int index) {
        String lengthSelector = String.format((xpathIDMap.get(shipmentLengthSelector).replace("ph", prefix)), index);
        sendkeysUsingCssSelector(lengthSelector, xlShipmentLength, Keys.TAB);
        waitForAction(200);
    }
    public void userEnterGBShipmentLength( String shipmentLengthSelector, String xlShipmentLength) {
        String lengthSelector = String.format((xpathIDMap.get(shipmentLengthSelector)));
        sendkeysUsingCssSelector(lengthSelector, xlShipmentLength, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentWidth(String prefix, String shipmentWidthSelector, String xlShipmentWidth, int index) {
        String widthSelector = String.format((xpathIDMap.get(shipmentWidthSelector)).replace("ph", prefix), index);
        sendkeysUsingCssSelector(widthSelector, xlShipmentWidth, Keys.TAB);
        waitForAction(200);
    }
    public void userEnterGBShipmentWidth(String shipmentWidthSelector, String xlShipmentWidth) {
        String widthSelector = String.format((xpathIDMap.get(shipmentWidthSelector)));
        sendkeysUsingCssSelector(widthSelector, xlShipmentWidth, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentHeight(String prefix, String shipmentHeightSelector, String xlShipmentHeight, int index) {
        String heightSelector = String.format((xpathIDMap.get(shipmentHeightSelector)).replace("ph", prefix), index);
        sendkeysUsingCssSelector(heightSelector, xlShipmentHeight, Keys.TAB);
        waitForAction(200);
    }
    public void userEnterGBShipmentHeight(String shipmentHeightSelector, String xlShipmentHeight) {
        String heightSelector = String.format((xpathIDMap.get(shipmentHeightSelector)));
        sendkeysUsingCssSelector(heightSelector, xlShipmentHeight, Keys.TAB);
        waitForAction(200);
    }

    public void userChooseGBQuantityUnit(String shipmentQuantitySelector, String xlShipmentUnit, int index) {
        String unitSelector = String.format(xpathIDMap.get(shipmentQuantitySelector), index);
        selectDropdownUsingCssSelector(unitSelector, xlShipmentUnit);
        waitForAction(200);
        // unit change popup confirmation
        acceptPopUpButton(xpathIDMap.get("gbParcelUnitSelect"), xpathIDMap.get("gbParcelUnitSelectOk"));
    }
    public void userChooseGBQuantityUnit(String shipmentQuantitySelector, String xlShipmentUnit) {
        String unitSelector = String.format(xpathIDMap.get(shipmentQuantitySelector));
        selectDropdownUsingCssSelector(unitSelector, xlShipmentUnit);
        waitForAction(200);
        // unit change popup confirmation
        acceptPopUpButton(xpathIDMap.get("gbParcelUnitSelect"), xpathIDMap.get("gbParcelUnitSelectOk"));
    }

    public void userChooseGBQuantityUnit(String prefix, String shipmentQuantitySelector, String xlShipmentUnit, int index) {
        String unitSelector = String.format((xpathIDMap.get(shipmentQuantitySelector)).replace("ph", prefix), index);
        selectDropdownUsingCssSelector(unitSelector, xlShipmentUnit);
        waitForAction(200);
        // unit change popup confirmation
        acceptPopUpButton(xpathIDMap.get("gbParcelUnitSelect"), xpathIDMap.get("gbParcelUnitSelectOk"));
    }

    public void userEnterGBShipmentTotalWeight(String totalWeightSelector, String xlTotalWeight) {
        sendkeysUsingCssSelector(getXpathIDMap().get(totalWeightSelector), xlTotalWeight, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentTotalWeight(String prefix, String totalWeightSelector, String xlTotalWeight) {
        sendkeysUsingCssSelector(getXpathIDMap().get(totalWeightSelector).replace("ge", prefix), xlTotalWeight, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterGBShipmentTotalWeight(String prefix, String totalWeightSelector, String xlTotalWeight, int index) {
        String quantitySelector = String.format((xpathIDMap.get(totalWeightSelector)).replace("ph", prefix), index);
        sendkeysUsingCssSelector(quantitySelector, xlTotalWeight, Keys.TAB);
        waitForAction(200);
    }


    public void userChooseGBShipmentUnit(String totalWeightUnitSelector, String xlShipmentWeightUnits) {
//        selectDropdownUsingCssSelector(totalWeightUnitSelector, xlShipmentWeightUnits);
        selectDropdownUsingCssSelector(xpathIDMap.get(totalWeightUnitSelector), xlShipmentWeightUnits);
        waitForAction(200);
        // unit change popup confirmation
        acceptPopUpButton(getXpathIDMap().get("gbParcelUnitSelect"), getXpathIDMap().get("gbParcelUnitSelectOk"));
    }

    public void userChooseAddItem(String addItemSelector) {
        driver.findElement(By.linkText(xpathIDMap.get(addItemSelector))).click();
        waitForAction(200);
    }

    public void userSelectYesNoRadioButton(String yesOrNo, String yesSelector, String noSelector) {
        if (yesOrNo.equalsIgnoreCase("Yes")) {
            waitForPageLoad(driver);
            ClickOnRadiobutton(getXpathIDMap().get(yesSelector));
        } else if (yesOrNo.equalsIgnoreCase("No")) {
            waitForPageLoad(driver);
            ClickOnRadiobutton(getXpathIDMap().get(noSelector));
        }
        waitForAction(200);
    }

    public void userSelectYesNoRadioButton(String prefix, String yesOrNo, String yesSelector, String noSelector) {
        if (yesOrNo.equalsIgnoreCase("Yes")) {
            waitForPageLoad(driver);
            ClickOnRadiobutton((getXpathIDMap().get(yesSelector)).replace("ph", prefix));
        } else if (yesOrNo.equalsIgnoreCase("No")) {
            waitForPageLoad(driver);
            ClickOnRadiobutton((getXpathIDMap().get(noSelector)).replace("ph", prefix));
        }
        waitForAction(200);
    }

    public void userSelectYesNoRadioButtonRotated(String prefix, String yesOrNo, String yesSelector, String noSelector) {
        if (yesOrNo.equalsIgnoreCase("Yes")) {
            waitForPageLoad(driver);
            ClickOnRadiobutton((getXpathIDMap().get(yesSelector)).replace("pharma", prefix));
        } else if (yesOrNo.equalsIgnoreCase("No")) {
            waitForPageLoad(driver);
            ClickOnRadiobutton((getXpathIDMap().get(noSelector)).replace("pharma", prefix));
        }
        waitForAction(200);
    }

    public void userWaitsToFillCargoShipmentRequest() {
        WebElement chargesConfirmationPage = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentChargeConfirmation")));
        if (chargesConfirmationPage == null) {
            waitForLoaderProgressBar();
        }
    }

    public void waitForLoaderProgressBar() {
        waitForLoaderElement(xpathIDMap.get("gbLoadingWait"));
    }

    public void userEnterRecipientDetails(String[] recipientInfo) {

        waitForAction(2000);
        sendkeysUsingCssSelector(getXpathIDMap().get("ShipmentDescription"), recipientInfo[0], Keys.TAB);
        waitForAction(200);
        sendkeysUsingCssSelector(getXpathIDMap().get("RecipientAccountNumber"), recipientInfo[1], Keys.TAB);
        waitForAction(10000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentPaymentMethod"))).click();
        waitForAction(200);
        // Select one available credit card (first one)
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentPaymentWithFirstCreditCard"))).click();
        waitForAction(200);
        // Select agreement check box
        driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentOptInFlag"))).click();
        waitForAction(200);

        // Submit shipment confirmation
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFinalConfirmation"))).click();

        waitForLoaderProgressBar();

        waitForAction(200);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFinalConfirmationSubmit"))).click();
        waitForLoaderProgressBar();
        waitForAction(200);
        WebElement confirmedBookingPage = driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentBookingConfirmation")));
        Assert.assertNotNull(confirmedBookingPage);
        WebElement airwayBill = driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentAirwayBillNumber")));
        Assert.assertNotNull(airwayBill);
        System.out.println("%%%%%%%%%%%%%% Airway Bill: " + airwayBill.getText() + " %%%%%%%%%%%%%%");
        Assert.assertFalse(airwayBill.getText().isEmpty());
    }

    public void userClickOnShipmentDatePicker(String prefix) {
        DatePicker datePicker = new DatePicker(driver, getXpathIDMap());
        datePicker.openCalendar(prefix, "gbParcelsShipmentDateCalendar", "gbPharmaShipmentDateInput", "gbPharmaShipmentDateOpener");
        datePicker.selectDate(prefix, "gbParcelsShipmentDateCalendarNextMonth", "gbParcelsShipmentDateSelector");
        waitForAction(500);
    }

    public void userSelectGBPharmaDepartureTime(String prefix) {
        final String[] departureTimes = {"0000", "0800", "1200", "1600", "2000"};
        WebElement selectElement = driver.findElement(By.cssSelector(getXpathIDMap().get("gbPharmaDepartureTimeSelector").replace("ph", prefix)));
        selectElement.click();
        waitForAction(200);

        Select departureTime = new Select(selectElement);
        int index = new Random().nextInt(departureTimes.length);
        departureTime.selectByValue(departureTimes[index]);
        waitForAction(200);
    }


    public Map<String, String> getXpathIDMap() {
        return xpathIDMap;
    }

    public void userClicksOnGBFindFlights(String shipmentType) {

        if (shipmentType.contains("Perishables"))
            driver.findElement(By.xpath(" //li[contains(@data-reset-form-method,'Fresh')]//button[text()='Find Flight']")).click();
        else if (shipmentType.contains("Medical"))
            driver.findElement(By.xpath("//li[contains(@data-reset-form-method,'Medical')]//button[text()='Find Flight']")).click();
        else if (shipmentType.contains("Pet"))
            driver.findElement(By.xpath("//li[contains(@data-reset-form-method,'Pet')]//button[text()='Find Flight']")).click();


    }

    public boolean flightValidations(String val) {
        int size = driver.findElements(By.xpath(getXpathIDMap().get("gbPharmaFlightSelectorNoRoute"))).size();
        String outcome;
        if (size > 0) {
            outcome = driver.findElement(By.xpath(getXpathIDMap().get("gbPharmaFlightSelectorNoRoute"))).getText();
            Assert.assertNotEquals("There are no available routes based on your search criteria. For additional assistance, please contact the Cargo Customer Service Center at 1-800-DL-CARGO (1-800-352-2746).", outcome);
        }
        isElementVisible(By.cssSelector(getXpathIDMap().get(val)));
        outcome = driver.findElement(By.cssSelector(getXpathIDMap().get(val))).getText();
        Assert.assertNotEquals("Product not available for selection", outcome);
        return true;
    }

    public void userSelectOneFlightDash() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightDashSelection"))).click();
    }

    public void userSelectOneFlightDashCritical() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightDashCriticalSelection"))).click();
    }

    public void userSelectOneFlightGeneral() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightGeneralSelection"))).click();
    }

    public void userSelectOneFlightExpress() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightExpressSelection"))).click();
    }

    public void userSelectOneFlightCritical() {
        // Wait for flight search dialog goes away
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(getXpathIDMap().get("gbShipmentFlightSearch"))));
        // Wait for the flight search results to be displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentFlightSearchResults")))));
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightCriticalSelection"))).click();
    }

    public void userSelectOneFlightStandard() {
        // Wait for flight search dialog goes away
//        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearch"));
        waitForAction(6000);
        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearchResults"));
        // Wait for the flight search results to be displayed
        waitForAction(2000);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFlightStandardSelection"))).click();
    }

    public void userSelectShipmentTypeFromFlightResults(String flightOption) {
        waitForAction(5000);
        waitUntilElementIsClickableAndClick(getXpathIDMap().get("gbShipmentFlightSearchResults"));
        validateRoutesAvailable();
        String i = String.valueOf((getShipmentSelectionIndex(flightOption)));
        String locator = String.format(xpathIDMap.get("gbShipmentFlighShipmentSelectionMod").replace("%s", i));
        validatePriceIsAvailable(locator);
        driver.findElement(By.cssSelector(locator)).click();
    }

    private void validatePriceIsAvailable(String val) {
        int size = driver.findElements(By.cssSelector(val)).size();
        Assert.assertNotEquals(size, 0);
        Reporter.log("locator size : " +size);
        if (size > 0) {
            String outcome = driver.findElement(By.cssSelector(val)).getText();
            Assert.assertNotEquals("Product not available for selection", outcome);
        }
    }

    private void validateRoutesAvailable() {
        int size = driver.findElements(By.xpath(getXpathIDMap().get("gbPharmaFlightSelectorNoRoute"))).size();
        String outcome;
        if (size > 0) {
            outcome = driver.findElement(By.xpath(getXpathIDMap().get("gbPharmaFlightSelectorNoRoute"))).getText();
            Assert.assertNotEquals("There are no available routes based on your search criteria. For additional assistance, please contact the Cargo Customer Service Center at 1-800-DL-CARGO (1-800-352-2746).", outcome);
        }
    }

    private int getShipmentSelectionIndex(String s) {

        if (s.contains("DSH")) {
            s = "DASH";
        } else if (s.contains("DMD")) {
            s = "DASH CRITICAL";
        } else if (s.contains("DHV")) {
            s = "DASH HEAVY";
        }
        int k = 0;
        int i = driver.findElements(By.cssSelector("div[class='text-container'] span")).size();
        for (k = 0; k < i; k++) {
            if (driver.findElements(By.cssSelector("div[class='text-container'] span")).get(k).getText().equalsIgnoreCase(s)) {
                return k + 2;
            }
        }
        return -1;
    }

    public void userSelectOneFlight() {
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbPharmaFlightSelector"))).click();
    }

    public void userEnterTotalNumberOfContainers(String prefix, String xlTotalNumberOfContainers) {
        sendkeysUsingCssSelector((xpathIDMap.get("gbPerishableTotalContainers")).replace("ph", prefix), xlTotalNumberOfContainers, Keys.TAB);
        waitForAction(200);
    }

    public void userEnterTotalContainersWeight(String prefix, String xlTotalContainersWeight) {
        sendkeysUsingCssSelector((getXpathIDMap().get("gbPharmaContainersTotalWeight")).replace("ph", prefix), xlTotalContainersWeight, Keys.TAB);
        waitForAction(200);
    }

    public void userSelectContainerType(String prefix, String xlShipmentRequireDeltaContainer) {
        if (xlShipmentRequireDeltaContainer.contains("No")) {
            String[] containerTypes = new String[]{"Envirotainer RAP e2", "Envirotainer RAP t2", "Envirotainer RKN e1", "Envirotainer RKN t2", "PharmaPort 360"};
            WebElement selectElement = driver.findElement(By.cssSelector((xpathIDMap.get("gbPharmaSelectContainerType")).replace("ph", prefix)));
            Select departureTime = new Select(selectElement);
            int index = new Random().nextInt(containerTypes.length);
            departureTime.selectByValue(containerTypes[index]);
            waitForAction(200);
        } else {
            driver.findElement(By.cssSelector((xpathIDMap.get("gbPerishableYesDeltaContainer")))).click();
            driver.findElement(By.cssSelector((xpathIDMap.get("gbPerishableYesDeltaContainer1")))).click();
            driver.findElement(By.cssSelector((xpathIDMap.get("gbPerishableYesDeltaContainerSubmitBtn")))).click();
        }
    }

    public void userSelectsSpecialtyValuables() {
        waitUntilCssSelectorElementIsClickable(getXpathIDMap().get("gbValuables"));
    }
    public void userSelectsSpecialtyHumanRemains() {
        waitUntilCssSelectorElementIsClickable(getXpathIDMap().get("gbHumanRemains"));
    }


    public void validateShipmentType(String s) {
        String s2 = shipmentTypeModerator(s);
        String s1 = driver.findElement(By.xpath("(//*[@id=\"dc-print\"]/section/section[2]/div[4])[1]")).getText().toUpperCase();
        System.out.println("%%%%%%%%"+s1+"%%%%%%%%%%%%%");
        Reporter.log("user logged In Sucessfully" +s1);
        assertTrue(s1.contains(s2));

    }

    private String shipmentTypeModerator(String s) {
        if (s.contains("DSH")) {
            s = "DASH";
        } else if (s.contains("DMD")) {
            s = "DASH CRITICAL";
        } else if (s.contains("DHV")) {
            s = "DASH HEAVY";
        } else if (s.contains("EXPRESS_HEAVY")) {
            s = "EXPRESS HEAVY";
        }
        return s;
    }
}



