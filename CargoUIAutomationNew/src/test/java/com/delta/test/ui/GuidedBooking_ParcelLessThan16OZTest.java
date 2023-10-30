package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.GBActions;
import com.delta.pageobjects.guidedbooking.HumanRemainsActions;
import com.delta.pageobjects.guidedbooking.Parcellessthan16OZActions;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;

public class GuidedBooking_ParcelLessThan16OZTest extends BaseTest {
    String testCaseSheetName = "GBParcel16Oz";
    String shipmentType = "gbParcellessthan16OZ";


//    @Test(priority = 1)
//    public void createAndConfirmGuidedBookingParcellessthan16OZ_STANDARD_NA_Domestic() {
//        String testCaseName = "createAndConfirmGuidedBookingParcellessthan16OZ_STANDARD_NA_Domestic";
//        fillForm(testCaseName);
//    }
    @Test(priority = 2)
    public void createAndConfirmGuidedBookingParcellessthan16OZ_DSH_NA_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingParcellessthan16OZ_DSH_NA_Domestic";
        fillForm(testCaseName);
    }
    @Test(priority = 3)
    public void createAndConfirmGuidedBookingParcellessthan16OZ_DMD_NA_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingParcellessthan16OZ_DMD_NA_Domestic";
        fillForm(testCaseName);
    }
//    @Test(priority = 4)
//    public void createAndConfirmGuidedBookingParcellessthan16OZ_GENERAL_NA_International() {
//        String testCaseName = "createAndConfirmGuidedBookingParcellessthan16OZ_GENERAL_NA_International";
//        fillForm(testCaseName);
//    }
    @Test(priority = 5)
    public void createAndConfirmGuidedBookingParcellessthan16OZ_EXPRESS_NA_International() {
        String testCaseName = "createAndConfirmGuidedBookingParcellessthan16OZ_EXPRESS_NA_International";
        fillForm(testCaseName);
    }
    @Test(priority = 6)
    public void createAndConfirmGuidedBookingParcellessthan16OZ_CRITICAL_NA_International() {
        String testCaseName = "createAndConfirmGuidedBookingParcellessthan16OZ_CRITICAL_NA_International";
        fillForm(testCaseName);
    }
    private void fillForm(String testCaseName) {
        Login login = new Login(driver, xlMapper.getXpathIDMap());
        Parcellessthan16OZActions parcellessthan16OZActions = new Parcellessthan16OZActions(driver, xlMapper.getXpathIDMap());
        GBActions gbActions = new GBActions(driver, xlMapper.getXpathIDMap());
        Map<String, Object> eachRowMap = xlMapper.excelReadTestData(testCaseSheetName, testCaseName);
        String execution = (String) eachRowMap.get("Execution");

        if (execution.equalsIgnoreCase("Yes")) {
            String xlAccountType = (String) eachRowMap.get("AccountType");
            String xlAccountNumber = (String) eachRowMap.get("AccountNumber");

            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            String flightOption = (String) eachRowMap.get("ShipmentProduct");
            System.err.println("O: " + xlShipmentOrigin + " D: " + xlShipmentDestination +" Type: "+flightOption);
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String xlShipmentQuantity = (String) eachRowMap.get("ShipmentQuantity");
            String xlShipmentDoPiecesContainDangerousGoods = (String) eachRowMap.get("ShipmentDoPiecesContainDangerousGoods");
            String xlShipmentDoPiecesBeRotated = (String) eachRowMap.get("ShipmentDoPiecesBeRotated");
            String xlShipmentBePreScreened = (String) eachRowMap.get("ShipmentBePreScreened");


            String xlRecipientAccountNumber = (String) eachRowMap.get("RecipientInformationAccountNumber");
            String xlShipmentDescription = (String) eachRowMap.get("ShipmentDescription");

            //CODE
            login.userLoginAsDomesticAccount(loginUsername, loginPassword);
            login.switchAccountType(xlAccountType, xlAccountNumber);
            gbActions.userClicksOnTheGuidedBookingOption();
            gbActions.userSelectsGBShipmentType("gbParcellessthan16oz");

            gbActions.userEnterGBShipmentOrigin("gbParcellessthanOrigin", xlShipmentOrigin);
            gbActions.userEnterGBShipmentDestination("gbParcellessthanDestination", xlShipmentDestination);
            parcellessthan16OZActions.userClickOnGBParcellessthanShipmentDatePicker();
            parcellessthan16OZActions.userSelectGBParcellessthanDepartureTime();
            parcellessthan16OZActions.userSelectGBParcellessthanDeliveryType();

            //parcellessthan16OZActions Code
            gbActions.userEnterGBShipmentQuantity("gbParcellessthanQuantity",xlShipmentQuantity);
            gbActions.userEnterGBShipmentTotalWeight("gbParcellessthanTotalWeight", xlShipmentWeight);
            gbActions.userChooseGBShipmentUnit("gbParcellessthanWeightUnits", xlShipmentWeightUnits);
            // Contains dangerous goods Yes or No
            gbActions.userSelectYesNoRadioButton(xlShipmentDoPiecesContainDangerousGoods, "gbParcellessthanContainsDangerousGoodsYes", "gbParcellessthanContainsDangerousGoodsNo");
            // Shipment to be rotated Yes or No
            gbActions.userSelectYesNoRadioButton(xlShipmentDoPiecesBeRotated, "gbParcellessthanCanbeRotatedYes", "gbParcellessthanCanbeRotatedNo");
            // Shipment to be pre-screened Yes or No
            gbActions.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbParcellessthanPrescreenedYes", "gbParcellessthanPrescreenedNo");

            parcellessthan16OZActions.userClicksOnGBParcellessthanFindFlights(shipmentType);
            gbActions.userSelectShipmentTypeFromFlightResults(flightOption);
            gbActions.userWaitsToFillCargoShipmentRequest();
            gbActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            gbActions.validateShipmentType(flightOption);
        }
    }
}
