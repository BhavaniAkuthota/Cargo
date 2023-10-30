package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.GBActions;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.pageobjects.guidedbooking.PerishableActions;
import com.delta.pageobjects.guidedbooking.PharmaActions;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.annotations.Test;

import java.util.Map;

public class GuidedBooking_PerishablesTest extends BaseTest {

    String testCaseSheetName = "GBPerishable";
    String shipmentType = "gbPerishables";
    static String prefix = "fr";
    static String prefixRotated = "fresh";


    @Test(priority = 1)
    public void createAndConfirmGuidedBookingPerishables_FRESH_Fresh1Activecontainer_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_FRESH_Fresh1Activecontainer_Domestic";
        fillForm(testCaseName, 1, false);

    }

    @Test(priority = 2)
    public void createAndConfirmGuidedBookingPerishables_FRESH_Fresh2Temperaturecontrolledbetween2Cto8C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_FRESH_Fresh2Temperaturecontrolledbetween2Cto8C_Domestic";
        fillForm(testCaseName, 2, false);
    }

    @Test(priority = 3)
    public void createAndConfirmGuidedBookingPerishables_DASH_Fresh3Flowersbetween2Cto25C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_DSH_Fresh3Flowersbetween2Cto25C_Domestic";
        fillForm(testCaseName, 3, false);
    }

    @Test(priority = 4)
    public void createAndConfirmGuidedBookingPerishables_DASH_HEAVY_Fresh3Foodstuffsbetween2Cto25C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_DHV_Fresh3Foodstuffsbetween2Cto25C_Domestic";
        fillForm(testCaseName, 4, false);
    }

    @Test(priority = 5)
    public void createAndConfirmGuidedBookingPerishables_DMD_Fresh3Seafoodbetween2Cto25C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_DMD_Fresh3Seafoodbetween2Cto25C_Domestic";
        fillForm(testCaseName, 5, false);
    }

    @Test(priority = 6)
    public void createAndConfirmGuidedBookingPerishables_FRESH_Fresh1Activecontainer_International() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_FRESH_Fresh1Activecontainer_International";
        fillForm(testCaseName, 1, false);
    }

    @Test(priority = 7)
    public void createAndConfirmGuidedBookingPerishables_FRESH_Fresh2Temperaturecontrolledbetween2Cto8C_International() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_FRESH_Fresh2Temperaturecontrolledbetween2Cto8C_International";
        fillForm(testCaseName, 2, false);
    }


    @Test(priority = 8)
    public void createAndConfirmGuidedBookingPerishables_EXPRESS_Fresh3Flowersbetween2Cto25C_International() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_EXPRESS_Fresh3Flowersbetween2Cto25C_International";
        fillForm(testCaseName, 3, false);
    }

    @Test(priority = 9)
    public void createAndConfirmGuidedBookingPerishables_EXPRESS_HEAVY_Fresh3Foodstuffsbetween2Cto25C_International() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_EXPRESS_HEAVY_Fresh3Foodstuffsbetween2Cto25C_International";
        fillForm(testCaseName, 5, false);
    }

    @Test(priority = 10)
    public void createAndConfirmGuidedBookingPerishables_CRITICAL_HEAVY_Fresh3Seafoodbetween2Cto25C_International() {
        String testCaseName = "createAndConfirmGuidedBookingPerishables_CRITICAL_HEAVY_Fresh3Seafoodbetween2Cto25C_International";
        fillForm(testCaseName, 12, false);
    }


    private void fillForm(String testCaseName, int index, boolean addAdditionalInfo) {

        Login login = new Login(driver, xlMapper.getXpathIDMap());
        PharmaActions pharmaBookingActions = new PharmaActions(driver, xlMapper.getXpathIDMap());
        PerishableActions perishableBookingActions = new PerishableActions(driver, xlMapper.getXpathIDMap());
        GBActions gbActions = new GBActions(driver, xlMapper.getXpathIDMap());
        Map<String, Object> eachRowMap = xlMapper.excelReadTestData(testCaseSheetName, testCaseName);

        String execution = (String) eachRowMap.get("Execution");
        if (execution.equalsIgnoreCase("Yes")) {
            String xlAccountType = (String) eachRowMap.get("AccountType");
            String xlAccountNumber = (String) eachRowMap.get("AccountNumber");
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentOrigin = ((String) eachRowMap.get("ShipmentOrigin"));
            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");

            String xlShipmentRequireDeltaContainer = (String) eachRowMap.get("ShipmentRequireDeltaContainer");
            String flightOption = (String) eachRowMap.get("ShipmentProduct");
            System.err.println("O: " + xlShipmentOrigin + " D: " + xlShipmentDestination +" Type: "+flightOption);
            String xlShipmentQuantity = (String) eachRowMap.get("ShipmentQuantity");
            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String xlShipmentDoPiecesContainDangerousGoods = (String) eachRowMap.get("ShipmentDoPiecesContainDangerousGoods");
            String xlShipmentDoPiecesBeRotated = (String) eachRowMap.get("ShipmentDoPiecesBeRotated");
            String xlShipmentBePreScreened = (String) eachRowMap.get("ShipmentBePreScreened");
            String xlRecipientAccountNumber = (String) eachRowMap.get("RecipientInformationAccountNumber");
            String xlShipmentDescription = (String) eachRowMap.get("ShipmentDescription");
            //CODE
            login.userLoginAsDomesticAccount(loginUsername, loginPassword);
            login.switchAccountType(xlAccountType, xlAccountNumber);
            gbActions.userClicksOnTheGuidedBookingOption();
            gbActions.userSelectsGBShipmentType(shipmentType);
            gbActions.userEnterGBShipmentOrigin(prefix, "gbPharmaOrigin", xlShipmentOrigin);
            gbActions.userEnterGBShipmentDestination(prefix, "gbPharmaDestination", xlShipmentDestination);
            gbActions.userClickOnShipmentDatePicker(prefix);
            //Perishable Code
            perishableBookingActions.userSelectGBPerishableType(index, 1);
            switch (index) {
                case 1:
                    gbActions.userSelectYesNoRadioButton(prefix, xlShipmentRequireDeltaContainer, "gbPharmaContainerRequiredYes", "gbPharmaContainerRequiredNo");
                    perishableBookingActions.userSelectCommodityType();
                    gbActions.userSelectContainerType(prefix, xlShipmentRequireDeltaContainer);
                    gbActions.userEnterTotalNumberOfContainers(prefix, xlShipmentQuantity);
                    gbActions.userEnterTotalContainersWeight(prefix, xlShipmentWeight);
                    gbActions.userChooseGBQuantityUnit(prefix, "gbPharmaUnit", xlShipmentWeightUnits, 1);
                    break;
                default:
                    Parcel parcel1 = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
                    addShipmentDimensions(pharmaBookingActions, parcel1, 1);
                    gbActions.userEnterGBShipmentTotalWeight(prefix, "gbParcelsTotalWeight", xlShipmentWeight);
                    gbActions.userChooseGBQuantityUnit(prefix, "gbPharmaUnit", xlShipmentWeightUnits, 1);
                    break;
            }
            gbActions.userSelectYesNoRadioButton(prefix, xlShipmentDoPiecesContainDangerousGoods, "gbPharmaContainsDangerousGoodsYes", "gbPharmaContainsDangerousGoodsNo");
            gbActions.userSelectYesNoRadioButtonRotated(prefixRotated, xlShipmentDoPiecesBeRotated, "gbPharmaCanbeRotatedYes", "gbPharmaCanbeRotatedNo");
            gbActions.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbPerishablePrescreenedYes", "gbPerishablePrescreenedNo");
            gbActions.userClicksOnGBFindFlights(shipmentType);
            gbActions.userSelectShipmentTypeFromFlightResults(flightOption);
            gbActions.userWaitsToFillCargoShipmentRequest();
            gbActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            gbActions.validateShipmentType(flightOption);
        }
    }

    private void addShipmentDimensions(GBActions gbActions, Parcel parcel, int index) {
        gbActions.userEnterGBShipmentQuantity(prefix, "gbPharmaQuantity", parcel.getShipmentQuantity(), index);
        gbActions.userEnterGBShipmentLength(prefix, "gbPharmaLength", parcel.getShipmentLength(), index);
        gbActions.userEnterGBShipmentWidth(prefix, "gbPharmaWidth", parcel.getShipmentWidth(), index);
        gbActions.userEnterGBShipmentHeight(prefix, "gbPharmaHeight", parcel.getShipmentHeight(), index);
        gbActions.userChooseGBQuantityUnit(prefix, "gbPharmaUnit", parcel.getShipmentUnit(), index);
    }

}
