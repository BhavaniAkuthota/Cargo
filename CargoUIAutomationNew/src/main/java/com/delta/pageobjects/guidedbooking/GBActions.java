package com.delta.pageobjects.guidedbooking;

import com.delta.util.CommonMethod;
import com.delta.util.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

public class GBActions {
    private final WebDriver driver;
    private final CommonMethod commonMethod;
    private final Map<String, String> xpathIDMap;

    public GBActions(WebDriver driver) {
        this.driver = driver;

        Login login = new Login(this.driver);
        commonMethod = new CommonMethod(this.driver);
        this.xpathIDMap = login.getXpathIDMap();
    }

    public void userSelectsGBShipmentType(String shipmentTypeSelector) {
        commonMethod.waitUntilXpathElementIsClickable(getXpathIDMap().get(shipmentTypeSelector));
    }

    public void userClicksOnTheGuidedBookingOption() {
        commonMethod.closeBanner();
        commonMethod.waitUntilElementIsClickable(getXpathIDMap().get("abbookAShipment"));
        commonMethod.waitUntilXpathElementIsClickable(getXpathIDMap().get("gbGuided"));
    }

    public void userEnterGBShipmentOrigin(String originSelector, String xlShipmentOrigin) {
        commonMethod.sendkeysUsingCssSelector(getXpathIDMap().get(originSelector), xlShipmentOrigin, Keys.TAB);
        commonMethod.waitForAction(200);
    }

    public void userEnterGBShipmentDestination(String destinationSelector, String xlShipmentDestination) {
        commonMethod.sendkeysUsingCssSelector(getXpathIDMap().get(destinationSelector), xlShipmentDestination, Keys.TAB);
        commonMethod.waitForAction(200);
    }

    public void userEnterGBShipmentQuantity(String shipmentQuantitySelector, String xlShipmentQuantity, int index) {
        String quantitySelector = String.format(xpathIDMap.get(shipmentQuantitySelector), index);
        commonMethod.sendkeysUsingCssSelector(quantitySelector, xlShipmentQuantity, Keys.TAB);
        commonMethod.waitForAction(200);
    }

    public void userEnterGBShipmentLength(String shipmentLengthSelector, String xlShipmentLength, int index) {
        String lengthSelector = String.format(xpathIDMap.get(shipmentLengthSelector), index);
        commonMethod.sendkeysUsingCssSelector(lengthSelector, xlShipmentLength, Keys.TAB);
        commonMethod.waitForAction(200);
    }

    public void userEnterGBShipmentWidth(String shipmentWidthSelector, String xlShipmentWidth, int index) {
        String widthSelector = String.format(xpathIDMap.get(shipmentWidthSelector), index);
        commonMethod.sendkeysUsingCssSelector(widthSelector, xlShipmentWidth, Keys.TAB);
        commonMethod.waitForAction(200);
    }

    public void userEnterGBShipmentHeight(String shipmentHeightSelector, String xlShipmentHeight, int index) {
        String heightSelector = String.format(xpathIDMap.get(shipmentHeightSelector), index);
        commonMethod.sendkeysUsingCssSelector(heightSelector, xlShipmentHeight, Keys.TAB);
        commonMethod.waitForAction(200);
    }

    public void userChooseGBQuantityUnit(String shipmentQuantitySelector, String xlShipmentUnit, int index) {
        String unitSelector = String.format(xpathIDMap.get(shipmentQuantitySelector), index);
        commonMethod.selectDropdownUsingCssSelector(unitSelector, xlShipmentUnit);
        commonMethod.waitForAction(200);
        // unit change popup confirmation
        commonMethod.acceptPopUpButton(xpathIDMap.get("gbParcelUnitSelect"), xpathIDMap.get("gbParcelUnitSelectOk"));
    }

    public void userEnterGBShipmentTotalWeight(String totalWeightSelector, String xlTotalWeight) {
        commonMethod.sendkeysUsingCssSelector(getXpathIDMap().get(totalWeightSelector), xlTotalWeight, Keys.TAB);
        commonMethod.waitForAction(200);
    }

    public void userChooseGBShipmentUnit(String totalWeightUnitSelector, String xlShipmentWeightUnits) {
        commonMethod.selectDropdownUsingCssSelector(xpathIDMap.get(totalWeightUnitSelector), xlShipmentWeightUnits);
        commonMethod.waitForAction(200);
        // unit change popup confirmation
        commonMethod.acceptPopUpButton(getXpathIDMap().get("gbParcelUnitSelect"), getXpathIDMap().get("gbParcelUnitSelectOk"));
    }

    public void userChooseAddItem(String addItemSelector) {
        driver.findElement(By.linkText(xpathIDMap.get(addItemSelector))).click();
        commonMethod.waitForAction(200);
    }

    public void userSelectYesNoRadioButton(String yesOrNo, String yesSelector, String noSelector) {
        if (yesOrNo.equalsIgnoreCase("Yes")) {
            commonMethod.waitForPageLoad(getDriver());
            commonMethod.ClickOnRadiobutton(getXpathIDMap().get(yesSelector));
        } else if (yesOrNo.equalsIgnoreCase("No")) {
            commonMethod.waitForPageLoad(getDriver());
            commonMethod.ClickOnRadiobutton(getXpathIDMap().get(noSelector));
        }
        commonMethod.waitForAction(200);
    }

    public void userWaitsToFillCargoShipmentRequest() {
        WebElement chargesConfirmationPage = driver.findElement(By.xpath(xpathIDMap.get("gbShipmentChargeConfirmation")));
        if (chargesConfirmationPage == null) {
            waitForLoaderProgressBar();
        }
    }

    public void waitForLoaderProgressBar() {
        commonMethod.waitForLoaderElement(xpathIDMap.get("gbLoadingWait"));
    }

    public void userEnterRecipientDetails(String[] recipientInfo) {
        // Wait for the recipient page to be completely rendered
//        waitForLoaderProgressBar();
        // Enter recipient account number and the rest of the form will be autofill by system
        commonMethod.waitForAction(2000);
        commonMethod.sendkeysUsingCssSelector(getXpathIDMap().get("ShipmentDescription"), recipientInfo[0], Keys.TAB);
        commonMethod.waitForAction(200);
        commonMethod.sendkeysUsingCssSelector(getXpathIDMap().get("RecipientAccountNumber"), recipientInfo[1], Keys.TAB);

        // Wait for loader to go away and display charge confirmation and then click 'Accept'
        waitForLoaderProgressBar();

        // Select Payment method
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentPaymentMethod"))).click();
        commonMethod.waitForAction(200);
        // Select one available credit card (first one)
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentPaymentWithFirstCreditCard"))).click();
        commonMethod.waitForAction(200);
        // Select agreement check box
        driver.findElement(By.xpath(getXpathIDMap().get("gbShipmentOptInFlag"))).click();
        commonMethod.waitForAction(200);

        // Submit shipment confirmation
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFinalConfirmation"))).click();

        waitForLoaderProgressBar();

        commonMethod.waitForAction(200);
        driver.findElement(By.cssSelector(getXpathIDMap().get("gbShipmentFinalConfirmationSubmit"))).click();

        waitForLoaderProgressBar();

        // Confirmed Booking Page is displayed
        WebElement confirmedBookingPage = getDriver().findElement(By.xpath(getXpathIDMap().get("gbShipmentBookingConfirmation")));
        Assert.assertNotNull(confirmedBookingPage);
        WebElement airwayBill = getDriver().findElement(By.xpath(getXpathIDMap().get("gbShipmentAirwayBillNumber")));
        Assert.assertNotNull(airwayBill);
        System.out.println("%%%%%%%%%%%%%% Airway Bill: " + airwayBill.getText() + " %%%%%%%%%%%%%%");
        Assert.assertFalse(airwayBill.getText().isBlank());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public CommonMethod getCommonMethod() {
        return commonMethod;
    }

    public Map<String, String> getXpathIDMap() {
        return xpathIDMap;
    }
}
