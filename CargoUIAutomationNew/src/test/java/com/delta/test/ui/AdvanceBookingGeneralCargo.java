package com.delta.test.ui;

import com.delta.util.BaseTest;


public class AdvanceBookingGeneralCargo extends BaseTest {
//
//    Login login = new Login(driver);
//    AdvanceBooking advanceBooking = new AdvanceBooking(driver);
//
//    @Test(priority = 1)
//    public void createAndConfirmAdvanceBookingForGeneral_Cargo_STANDARD_Loose() throws InterruptedException {
//        String testCaseSheetName = "ABGeneralCargo";
//        String testCaseName = "createAndConfirmAdvanceBookingForGeneral_Cargo_STANDARD_Loose";
//        Map<String, Object> eachRowMap = advanceBooking.excelReadTestData(testCaseSheetName, testCaseName);
//        String execution = (String) eachRowMap.get("Execution");
//        System.out.println("execution  " + execution);
//        if (execution.equalsIgnoreCase("Yes")) {
//            String loginUsername = (String) eachRowMap.get("LoginUsername");
//            String loginPassword = (String) eachRowMap.get("LoginPassword");
//            String xlShipmentContains = (String) eachRowMap.get("ShipmentContains");
//            String xlShipmentDescription = (String) eachRowMap.get("ShipmentDescription");
//            String xlShipmentTypeFromTheList = (String) eachRowMap.get("ShipmentTypeFromTheList");
//            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
//            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
//            String xlShipmentMethod = (String) eachRowMap.get("ShipmentMethod");
//            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
//            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
//            String xlShipmentQuantity = (String) eachRowMap.get("ShipmentQuantity");
//            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
//            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
//            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
//            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");
//            login.userLoginAsDomesticAccount( loginUsername, loginPassword);
//            advanceBooking.userClicksTheAdvancedOption();
//            advanceBooking.userClickAssignNewAirWaybillNumber();
//            advanceBooking.userEnterMyShipmentContains( xlShipmentContains);
//            advanceBooking.userEnterShipmentDescription( xlShipmentDescription);
//            advanceBooking.userChooseTheShipmentMethod( xlShipmentMethod);
//            advanceBooking.userEnterShipmentWeight( xlShipmentWeight);
//            advanceBooking.userChooseShipmentUnit( xlShipmentWeightUnits);
//            advanceBooking.userEnterShipmentQuantity( xlShipmentQuantity);
//            advanceBooking.userEnterShipmentLength( xlShipmentLength);
//            advanceBooking.userEnterShipmentWidth( xlShipmentWidth);
//            advanceBooking.userEnterShipmentHeight( xlShipmentHeight);
//            advanceBooking.userChooseQuantityUnit( xlShipmentQuantityUnit);
//            System.out.println("xlShipmentWeightUnits  " + xlShipmentWeightUnits);
//            Thread.sleep(20000);
//
//            Reporter.log("user logged In Sucessfully");
//
//        } else {
//            throw new SkipException("Test Ignored");
//        }
//
//
//    }
//
////    @Test(priority = 2)
////    public void createAndConfirmAdvanceBookingForGeneral_Cargo_DSH_Loose() throws InterruptedException {
////        String testCaseSheetName = "ABGeneralCargo";
////        String testCaseName = "createAndConfirmAdvanceBookingForGeneral_Cargo_DSH_Loose";
////        Map<String, Object> eachRowMap = advanceBooking.excelReadTestData(testCaseSheetName, testCaseName);
////        String execution = (String) eachRowMap.get("Execution");
////        // System.out.println("execution  "+execution);
////        if (execution.equalsIgnoreCase("Yes")) {
////            String loginUsername = (String) eachRowMap.get("LoginUsername");
////            String loginPassword = (String) eachRowMap.get("LoginPassword");
////            String shipmentContains = (String) eachRowMap.get("ShipmentContains");
////            String shipmentTypeFromTheList = (String) eachRowMap.get("ShipmentTypeFromTheList");
////            String shipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
////            String shipmentDestination = (String) eachRowMap.get("ShipmentDestination");
////            String shipmentMethod = (String) eachRowMap.get("ShipmentMethod");
////            String shipmentWeight = (String) eachRowMap.get("ShipmentWeight");
////            String ShipmentMethod = (String) eachRowMap.get("ShipmentContains");
////            String shipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
////            String shipmentLength = (String) eachRowMap.get("ShipmentLength");
////            String shipmentWidth = (String) eachRowMap.get("ShipmentWidth");
////            String shipmentHeight = (String) eachRowMap.get("ShipmentHeight");
////            String shipmentUnit = (String) eachRowMap.get("ShipmentUnit");
////            login.userLoginAsDomesticAccount( loginUsername, loginPassword);
////            advanceBooking.userClicksTheAdvancedOption(driver);
////            advanceBooking.userClickAssignNewAirWaybillNumber(driver);
////            //Thread.sleep(5000);
////            System.out.println(driver.getTitle());
////            Reporter.log("user logged In Sucessfully");
////
////
////        } else {
////            throw new SkipException("Test Ignored");
////        }
////
////
////    }
////
////    @Test(priority = 3)
////    public void createAndConfirmAdvanceBookingForGeneral_Cargo_DMD_Loose() throws InterruptedException {
////        String testCaseSheetName = "ABGeneralCargo";
////        String testCaseName = "createAndConfirmAdvanceBookingForGeneral_Cargo_DMD_Loose";
////        Map<String, Object> eachRowMap = advanceBooking.excelReadTestData(testCaseSheetName, testCaseName);
////        String execution = (String) eachRowMap.get("Execution");
////        // System.out.println("execution  "+execution);
////        if (execution.equalsIgnoreCase("Yes")) {
////            String loginUsername = (String) eachRowMap.get("LoginUsername");
////            String loginPassword = (String) eachRowMap.get("LoginPassword");
////            String shipmentContains = (String) eachRowMap.get("ShipmentContains");
////            String shipmentTypeFromTheList = (String) eachRowMap.get("ShipmentTypeFromTheList");
////            String shipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
////            String shipmentDestination = (String) eachRowMap.get("ShipmentDestination");
////            String shipmentMethod = (String) eachRowMap.get("ShipmentMethod");
////            String shipmentWeight = (String) eachRowMap.get("ShipmentWeight");
////            String ShipmentMethod = (String) eachRowMap.get("ShipmentContains");
////            String shipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
////            String shipmentLength = (String) eachRowMap.get("ShipmentLength");
////            String shipmentWidth = (String) eachRowMap.get("ShipmentWidth");
////            String shipmentHeight = (String) eachRowMap.get("ShipmentHeight");
////            String shipmentUnit = (String) eachRowMap.get("ShipmentUnit");
////            login.userLoginAsDomesticAccount( loginUsername, loginPassword);
////            advanceBooking.userClicksTheAdvancedOption(driver);
////            advanceBooking.userClickAssignNewAirWaybillNumber(driver);
////            // Thread.sleep(5000);
////            System.out.println(driver.getTitle());
////            Reporter.log("user logged In Sucessfully");
////
////        } else {
////            throw new SkipException("Test Ignored");
////        }
////
////
////    }
}
