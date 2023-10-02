package com.delta.test.UI;

import com.delta.pageobjects.guidedbooking.GuidedBooking;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Map;

public class GuidedBookingParcel extends BaseTest {
    Login login=new Login();
    GuidedBooking guidedBooking = new GuidedBooking();
    @Test(priority=1)
    public void createAndConfirmGuidedBookingForParcels_STANDARD_NA_Domestic() throws InterruptedException {
        String testCaseSheetName="GBParcel";
        String testCaseName="createAndConfirmGuidedBookingForParcels_STANDARD_NA_Domestic";
        Map<String, Object> eachRowMap=guidedBooking.excelReadTestData(testCaseSheetName,testCaseName);
        String execution=(String) eachRowMap.get("Execution");
        System.out.println("execution  "+execution);
        if(execution.equalsIgnoreCase("Yes")) {
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
         //   String xlShipmentCalendarDate ="1";
           // String xlShipmentMethod = (String) eachRowMap.get("ShipmentMethod");
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String xlShipmentQuantity=(String) eachRowMap.get("ShipmentQuantity");
            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");
            String xlShipmentDoPiecesContainDangerousGoods = (String) eachRowMap.get("ShipmentDoPiecesContainDangerousGoods");
            String xlShipmentDoPiecesBeRotated = (String) eachRowMap.get("ShipmentDoPiecesBeRotated");
            String xlShipmentBePreScreened = (String) eachRowMap.get("ShipmentBePreScreened");
            login.userLoginAsDomesticAccount(driver,loginUsername,loginPassword);
            guidedBooking.userClicksOnTheGuidedBookingOption(driver);
            guidedBooking.userSelectsGbShipmentTypes(driver);
            guidedBooking.userEnterGbParcelShipmentOrigin(driver,xlShipmentOrigin);
            guidedBooking.userEnterGbParcelShipmentDestination(driver,xlShipmentDestination);
            guidedBooking.userClickOnGbParcelShipmentDatePicker(driver);
            guidedBooking.userSelectGbParcelDepartureTime(driver);
            guidedBooking.userSelectDeliveryType(driver);
            guidedBooking.userEnterGbParcelShipmentQuantity(driver,xlShipmentQuantity);
            guidedBooking.userEnterGbParcelShipmentLength(driver,xlShipmentLength);
            guidedBooking.userEnterGbParcelShipmentWidth(driver,xlShipmentWidth);
            guidedBooking.userEnterGbParcelShipmentHeight(driver,xlShipmentHeight);
            guidedBooking.userChooseGbParcelQuantityUnit(driver,xlShipmentQuantityUnit);
            guidedBooking.userEnterGbParcelShipmentWeight(driver,xlShipmentWeight);
            guidedBooking.userChooseGbParcelShipmentUnit(driver,xlShipmentWeightUnits);
            guidedBooking.userChooseGbParcelShipmentContainsDangerousGood(driver,xlShipmentDoPiecesContainDangerousGoods);
            guidedBooking.userChooseGbParcelShipmentPiecesBeRotated(driver,xlShipmentDoPiecesBeRotated);
            guidedBooking.userChooseGbParcelShipmentBePreScreened(driver,xlShipmentBePreScreened);
            guidedBooking.userClicksOnGbParcelViewShippingOptions(driver);
            System.out.println("xlShipmentWeightUnits  "+xlShipmentWeightUnits);
            Thread.sleep(20000);

            Reporter.log("user logged In Sucessfully");

        } else {
            throw new SkipException("Test Ignored");
        }


    }
    @Test(priority=2)
    public void createAndConfirmGuidedBookingForParcels_DSH_NA_Domestic() throws InterruptedException {
        String testCaseSheetName="GBParcel";
        String testCaseName="createAndConfirmGuidedBookingForParcels_DSH_NA_Domestic";
        Map<String, Object> eachRowMap=guidedBooking.excelReadTestData(testCaseSheetName,testCaseName);
        String execution=(String) eachRowMap.get("Execution");
        System.out.println("execution  "+execution);
        if(execution.equalsIgnoreCase("Yes")) {
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            //   String xlShipmentCalendarDate ="1";
            // String xlShipmentMethod = (String) eachRowMap.get("ShipmentMethod");
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String xlShipmentQuantity=(String) eachRowMap.get("ShipmentQuantity");
            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");
            String xlShipmentDoPiecesContainDangerousGoods = (String) eachRowMap.get("ShipmentDoPiecesContainDangerousGoods");
            String xlShipmentDoPiecesBeRotated = (String) eachRowMap.get("ShipmentDoPiecesBeRotated");
            String xlShipmentBePreScreened = (String) eachRowMap.get("ShipmentBePreScreened");
            login.userLoginAsDomesticAccount(driver,loginUsername,loginPassword);
            guidedBooking.userClicksOnTheGuidedBookingOption(driver);
            guidedBooking.userSelectsGbShipmentTypes(driver);
            guidedBooking.userEnterGbParcelShipmentOrigin(driver,xlShipmentOrigin);
            guidedBooking.userEnterGbParcelShipmentDestination(driver,xlShipmentDestination);
            guidedBooking.userClickOnGbParcelShipmentDatePicker(driver);
            guidedBooking.userSelectGbParcelDepartureTime(driver);
            guidedBooking.userSelectDeliveryType(driver);
            guidedBooking.userEnterGbParcelShipmentQuantity(driver,xlShipmentQuantity);
            guidedBooking.userEnterGbParcelShipmentLength(driver,xlShipmentLength);
            guidedBooking.userEnterGbParcelShipmentWidth(driver,xlShipmentWidth);
            guidedBooking.userEnterGbParcelShipmentHeight(driver,xlShipmentHeight);
            guidedBooking.userChooseGbParcelQuantityUnit(driver,xlShipmentQuantityUnit);
            guidedBooking.userEnterGbParcelShipmentWeight(driver,xlShipmentWeight);
            guidedBooking.userChooseGbParcelShipmentUnit(driver,xlShipmentWeightUnits);
            guidedBooking.userChooseGbParcelShipmentContainsDangerousGood(driver,xlShipmentDoPiecesContainDangerousGoods);
            guidedBooking.userChooseGbParcelShipmentPiecesBeRotated(driver,xlShipmentDoPiecesBeRotated);
            guidedBooking.userChooseGbParcelShipmentBePreScreened(driver,xlShipmentBePreScreened);
            guidedBooking.userClicksOnGbParcelViewShippingOptions(driver);
            System.out.println("xlShipmentWeightUnits  "+xlShipmentWeightUnits);
            Thread.sleep(20000);

            Reporter.log("user logged In Sucessfully");

        } else {
            throw new SkipException("Test Ignored");
        }


    }
}
