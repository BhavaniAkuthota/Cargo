package com.delta.test.UI;

import com.delta.pageobjects.advancebooking.AdvanceBooking;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import io.netty.util.Timeout;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class AdvanceBookingGeneralCargo extends BaseTest {

    Login login=new Login();
    AdvanceBooking advanceBooking=new AdvanceBooking();
    @Test(priority=1)
    public void createAndConfirmAdvanceBookingForGeneral_Cargo_STANDARD_Loose() throws InterruptedException {
        String testCaseSheetName="ABGeneralCargo";
        String testCaseName="createAndConfirmAdvanceBookingForGeneral_Cargo_STANDARD_Loose";
        Map<String, Object> eachRowMap=advanceBooking.excelReadTestData(testCaseSheetName,testCaseName);
        String execution=(String) eachRowMap.get("Execution");
        System.out.println("execution  "+execution);
        if(execution.equalsIgnoreCase("Yes")) {
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentContains = (String) eachRowMap.get("ShipmentContains");
            String xlShipmentDescription = (String) eachRowMap.get("ShipmentDescription");
            String xlShipmentTypeFromTheList = (String) eachRowMap.get("ShipmentTypeFromTheList");
            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            String xlShipmentMethod = (String) eachRowMap.get("ShipmentMethod");
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String xlShipmentQuantity=(String) eachRowMap.get("ShipmentQuantity");
            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");
            login.userLoginAsDomesticAccount(driver,loginUsername,loginPassword);
            advanceBooking.userClicksTheAdvancedOption(driver);
            advanceBooking.userClickAssignNewAirWaybillNumber(driver);
            advanceBooking.userEnterMyShipmentContains(driver,xlShipmentContains);
            advanceBooking.userEnterShipmentDescription(driver,xlShipmentDescription);
            advanceBooking.userChooseTheShipmentMethod(driver,xlShipmentMethod);
            advanceBooking.userEnterShipmentWeight(driver,xlShipmentWeight);
            advanceBooking.userChooseShipmentUnit(driver,xlShipmentWeightUnits);
            advanceBooking.userEnterShipmentQuantity(driver,xlShipmentQuantity);
            advanceBooking.userEnterShipmentLength(driver,xlShipmentLength);
            advanceBooking.userEnterShipmentWidth(driver,xlShipmentWidth);
            advanceBooking.userEnterShipmentHeight(driver,xlShipmentHeight);
            advanceBooking.userChooseQuantityUnit(driver,xlShipmentQuantityUnit);
            System.out.println("xlShipmentWeightUnits  "+xlShipmentWeightUnits);
            Thread.sleep(20000);

            Reporter.log("user logged In Sucessfully");

        } else {
            throw new SkipException("Test Ignored");
        }


    }

    @Test(priority = 2)
    public void createAndConfirmAdvanceBookingForGeneral_Cargo_DSH_Loose() throws InterruptedException {
        String testCaseSheetName="ABGeneralCargo";
        String testCaseName="createAndConfirmAdvanceBookingForGeneral_Cargo_DSH_Loose";
        Map<String, Object> eachRowMap=advanceBooking.excelReadTestData(testCaseSheetName,testCaseName);
        String execution=(String) eachRowMap.get("Execution");
        // System.out.println("execution  "+execution);
        if(execution.equalsIgnoreCase("Yes")) {
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String shipmentContains = (String) eachRowMap.get("ShipmentContains");
            String shipmentTypeFromTheList = (String) eachRowMap.get("ShipmentTypeFromTheList");
            String shipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String shipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            String shipmentMethod = (String) eachRowMap.get("ShipmentMethod");
            String shipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String ShipmentMethod = (String) eachRowMap.get("ShipmentContains");
            String shipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String shipmentLength = (String) eachRowMap.get("ShipmentLength");
            String shipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String shipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String shipmentUnit = (String) eachRowMap.get("ShipmentUnit");
            login.userLoginAsDomesticAccount(driver,loginUsername,loginPassword);
            advanceBooking.userClicksTheAdvancedOption(driver);
            advanceBooking.userClickAssignNewAirWaybillNumber(driver);
            //Thread.sleep(5000);
            System.out.println(driver.getTitle());
            Reporter.log("user logged In Sucessfully");



        } else {
            throw new SkipException("Test Ignored");
        }



    }

    @Test(priority = 3)
    public void createAndConfirmAdvanceBookingForGeneral_Cargo_DMD_Loose() throws InterruptedException {
        String testCaseSheetName="ABGeneralCargo";
        String testCaseName="createAndConfirmAdvanceBookingForGeneral_Cargo_DMD_Loose";
        Map<String, Object> eachRowMap=advanceBooking.excelReadTestData(testCaseSheetName,testCaseName);
        String execution=(String) eachRowMap.get("Execution");
        // System.out.println("execution  "+execution);
        if(execution.equalsIgnoreCase("Yes")) {
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String shipmentContains = (String) eachRowMap.get("ShipmentContains");
            String shipmentTypeFromTheList = (String) eachRowMap.get("ShipmentTypeFromTheList");
            String shipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String shipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            String shipmentMethod = (String) eachRowMap.get("ShipmentMethod");
            String shipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String ShipmentMethod = (String) eachRowMap.get("ShipmentContains");
            String shipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String shipmentLength = (String) eachRowMap.get("ShipmentLength");
            String shipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String shipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String shipmentUnit = (String) eachRowMap.get("ShipmentUnit");
            login.userLoginAsDomesticAccount(driver,loginUsername,loginPassword);
            advanceBooking.userClicksTheAdvancedOption(driver);
            advanceBooking.userClickAssignNewAirWaybillNumber(driver);
           // Thread.sleep(5000);
            System.out.println(driver.getTitle());
            Reporter.log("user logged In Sucessfully");

        } else {
            throw new SkipException("Test Ignored");
        }


    }
}
