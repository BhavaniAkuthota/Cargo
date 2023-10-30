package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.GBActions;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.pageobjects.guidedbooking.ValuablesActions;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;


public class GuidedBooking_ValuablesTest extends BaseTest {
    String testCaseSheetName = "GBSpecialty";
    String shipmentType = "gbValuables";
    static String prefix = "va";
    static String prefixRotated = "Safe";


    @Test(priority = 1)
    public void createAndConfirmGuidedBookingValuables_SAFE_Valuegreaterthan25000USD_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingValuables_SAFE_Valuegreaterthan25000USD_Domestic";
        fillForm(testCaseName, 1, false);
    }
    @Test(priority = 2)
    public void createAndConfirmGuidedBookingValuables_SAFE_Valuelessthan25000USD_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingValuables_SAFE_Valuelessthan25000USD_Domestic";
        fillForm(testCaseName, 2, true);
    }
    @Test(priority = 3)
    public void createAndConfirmGuidedBookingValuables_SAFE_Valuegreaterthan25000USD_International() {
        String testCaseName = "createAndConfirmGuidedBookingValuables_SAFE_Valuegreaterthan25000USD_International";
        fillForm(testCaseName, 1, false);
    }
    @Test(priority = 4)
    public void createAndConfirmGuidedBookingValuables_SAFE_Valuelessthan25000USD_International() {
        String testCaseName = "createAndConfirmGuidedBookingValuables_SAFE_Valuelessthan25000USD_International";
        fillForm(testCaseName, 2, false);
    }
    private void fillForm(String testCaseName, int valuableIndex, boolean addAdditionalValuables) {
        Login login = new Login(driver, xlMapper.getXpathIDMap());
        ValuablesActions valuablesActions = new ValuablesActions(driver, xlMapper.getXpathIDMap());
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
            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");

            String xlShipmentDoPiecesContainDangerousGoods = (String) eachRowMap.get("ShipmentDoPiecesContainDangerousGoods");
            String xlShipmentDoPiecesBeRotated = (String) eachRowMap.get("ShipmentDoPiecesBeRotated");
            String xlShipmentBePreScreened = (String) eachRowMap.get("ShipmentBePreScreened");
            String xlRecipientAccountNumber = (String) eachRowMap.get("RecipientInformationAccountNumber");
            String xlShipmentDescription = (String) eachRowMap.get("ShipmentDescription");

            //CODE
            login.userLoginAsDomesticAccount(loginUsername, loginPassword);
            login.switchAccountType(xlAccountType, xlAccountNumber);
            gbActions.userClicksOnTheGuidedBookingOption();
            gbActions.userSelectsGBShipmentType("gbSpecialty");
            gbActions.userSelectsSpecialtyValuables();
            gbActions.userEnterGBShipmentOrigin("gbValuablesOrigin", xlShipmentOrigin);
            gbActions.userEnterGBShipmentDestination("gbValuablesDestination", xlShipmentDestination);
            valuablesActions.userClickOnGBValuablesShipmentDatePicker();
            valuablesActions.userSelectGBValuablesDepartureTime();

            //Valuable Code
            valuablesActions.userSelectGBValuableType(valuableIndex, 1);

            switch (valuableIndex) {
                case 1:
                case 2:
                    // Enter quantity, length, weight, height and Units
                    Parcel parcel = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
                    // Enter details of default Pharma
                    addValuables(prefix, gbActions, parcel, 0);
                    if (addAdditionalValuables) {
                        // Now add few more parcels
                        for (int i = 0; i < 3; i++) {
                            gbActions.userChooseAddItem("gbValuablesAddItem");
                            //valuablesActions.userSelectGBValuableType(valuableIndex, i + 2);
                            addValuables(prefix, gbActions, parcel, i + 1);
                        }
                    }
                    gbActions.userChooseGBQuantityUnit("gbValuablesQuantityUnits", xlShipmentQuantityUnit, 1);

                    gbActions.userEnterGBShipmentTotalWeight("gbValuablesTotalWeight", xlShipmentWeight);
                    gbActions.userChooseGBShipmentUnit("gbValuablesWeightUnits", xlShipmentWeightUnits);

                    break;
                default:
                    fail("Invalid Valuable Type is provided");
            }

            // Contains dangerous goods Yes or No
            gbActions.userSelectYesNoRadioButton(xlShipmentDoPiecesContainDangerousGoods, "gbValuablesContainsDangerousGoodsYes", "gbValuablesContainsDangerousGoodsNo");
            // Shipment to be rotated Yes or No
            gbActions.userSelectYesNoRadioButton(xlShipmentDoPiecesBeRotated, "gbValuablesCanbeRotatedYes", "gbValuablesCanbeRotatedNo");
            // Shipment to be pre-screened Yes or No
            gbActions.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbValuablesPrescreenedYes", "gbValuablesPrescreenedNo");
            valuablesActions.userClicksOnGBValuableFindFlights();
//            valuablesActions.userSelectOneSafeFlight();
            gbActions.userSelectShipmentTypeFromFlightResults(flightOption);
            gbActions.userWaitsToFillCargoShipmentRequest();
            gbActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            gbActions.validateShipmentType(flightOption);
        }
    }

    private void addValuables(String prefix, GBActions gbActions, Parcel parcel, int index) {
        gbActions.userEnterGBShipmentQuantity(prefix, "gbValuablesQuantity", parcel.getShipmentQuantity(), index);
        gbActions.userEnterGBShipmentLength(prefix, "gbValuablesLength", parcel.getShipmentLength(), index);
        gbActions.userEnterGBShipmentWidth(prefix, "gbValuablesWidth", parcel.getShipmentWidth(), index);
        gbActions.userEnterGBShipmentHeight(prefix, "gbValuablesHeight", parcel.getShipmentHeight(), index);
        gbActions.userChooseGBQuantityUnit(prefix, "gbValuablesQuantityUnits", parcel.getShipmentUnit(), index);
    }

}
