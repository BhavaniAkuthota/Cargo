package com.delta.test.UI;

import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.pageobjects.guidedbooking.PharmaBookingActions;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;

public class PharmaBookingTest extends BaseTest {

    @AfterTest
    public void afterEach() {
        driver.close();
    }

    @Test(priority=1)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma1ActiveContainer_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma1ActiveContainer_Domestic";
        fillPharmaForm(testCaseName, 1, false);
    }

    @Test(priority=2)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma1PassiveContainer_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma1PassiveContainer_Domestic";
        fillPharmaForm(testCaseName, 2, false);
    }

    @Test(priority=3)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma2Temperaturecontrolledbetween2Cto8C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma2Temperaturecontrolledbetween2Cto8C_Domestic";
        fillPharmaForm(testCaseName, 3, true);
    }

    @Test(priority=4)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma3Temperaturecontrolledbetween2Cto25C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma3Temperaturecontrolledbetween2Cto25C_Domestic";
        fillPharmaForm(testCaseName, 4, false);
    }

    @Test(priority=5)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma4Temperaturecontrolledbetween15Cto25C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma4Temperaturecontrolledbetween15Cto25C_Domestic";
        fillPharmaForm(testCaseName, 5, false);
    }

    private void fillPharmaForm(String testCaseName, int pharmaIndex, boolean addAdditionalPharma) {
        Login login = new Login(driver);
        PharmaBookingActions pharmaBookingActions = new PharmaBookingActions(driver);

        String testCaseSheetName = "GBPharma";
        Map<String, Object> eachRowMap = login.excelReadTestData(testCaseSheetName, testCaseName);
        String execution=(String) eachRowMap.get("Execution");

        if(execution.equalsIgnoreCase("Yes")) {
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            String xlShipmentRequireDeltaContainer = (String) eachRowMap.get("ShipmentRequireDeltaContainer");

            String xlShipmentQuantity = (String) eachRowMap.get("ShipmentQuantity");
            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");

            String xlShipmentDoPiecesContainDangerousGoods = (String) eachRowMap.get("ShipmentDoPiecesContainDangerousGoods");
            String xlShipmentDoPiecesBeRotated = (String) eachRowMap.get("ShipmentDoPiecesBeRotated");
            String xlShipmentBePreScreened = (String) eachRowMap.get("ShipmentBePreScreened");
            String xlRecipientAccountNumber = (String) eachRowMap.get("RecipientInformationAccountNumber");
            String xlShipmentDescription = (String) eachRowMap.get("ShipmentDescription");

            //CODE
            login.userLoginAsDomesticAccount(loginUsername, loginPassword);
            pharmaBookingActions.userClicksOnTheGuidedBookingOption();
            pharmaBookingActions.userSelectsGBShipmentType("gbPharma");
            pharmaBookingActions.userEnterGBShipmentOrigin("gbPharmaOrigin", xlShipmentOrigin);
            pharmaBookingActions.userEnterGBShipmentDestination("gbPharmaDestination", xlShipmentDestination);
            pharmaBookingActions.userClickOnGBPharmaShipmentDatePicker();
            pharmaBookingActions.userSelectGBPharmaDepartureTime();
            pharmaBookingActions.userSelectGBPharmaType(pharmaIndex, 1);

            switch (pharmaIndex) {
                case 1:
                case 2:
                    // Container required Yes or No
                    pharmaBookingActions.userSelectYesNoRadioButton(xlShipmentRequireDeltaContainer, "gbPharmaContainerRequiredYes", "gbPharmaContainerRequiredNo");
                    pharmaBookingActions.userSelectContainerType();
                    pharmaBookingActions.userEnterTotalNumberOfContainers(xlShipmentQuantityUnit);
                    // Total Pharma Weight
                    pharmaBookingActions.userEnterTotalContainersWeight(xlShipmentWeight);
                    break;
                case 3:
                case 4:
                case 5:
                    // Enter quantity, length, weight, height and Units
                    Parcel parcel = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
                    // Enter details of default Pharma
                    addPharma(pharmaBookingActions, parcel, 1);
                    if (addAdditionalPharma) {
                        // Now add few more parcels
                        for (int i = 0; i < 3; i++) {
                            pharmaBookingActions.userChooseAddItem("gbPharmaAddItem");
                            pharmaBookingActions.userSelectGBPharmaType(pharmaIndex, i + 2);
                            addPharma(pharmaBookingActions, parcel, i + 2);
                        }
                    }

                    pharmaBookingActions.userEnterGBShipmentTotalWeight("gbPharmaTotalWeight", xlShipmentWeight);

                    break;
                default:
                    fail("Invalid Pharma Type is provided");
            }

            // Contains dangerous goods Yes or No
            pharmaBookingActions.userSelectYesNoRadioButton(xlShipmentDoPiecesContainDangerousGoods, "gbPharmaContainsDangerousGoodsYes", "gbPharmaContainsDangerousGoodsNo");
            // Pieces to be rotated Yes or No
            pharmaBookingActions.userSelectYesNoRadioButton(xlShipmentDoPiecesBeRotated, "gbPharmaCanbeRotatedYes", "gbPharmaCanbeRotatedNo");
            // Shipment to be pre-screened Yes or No
            pharmaBookingActions.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbPharmaPrescreenedYes", "gbPharmaPrescreenedNo");

            pharmaBookingActions.userClicksOnGBFindFlights();
            pharmaBookingActions.userSelectOneFlight();
            pharmaBookingActions.userWaitsToFillCargoShipmentRequest();
            pharmaBookingActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
        }
    }

    private void addPharma(PharmaBookingActions pharmaBookingActions, Parcel parcel, int index) {
        pharmaBookingActions.userEnterGBShipmentQuantity("gbPharmaQuantity", parcel.getShipmentQuantity(), index);
        pharmaBookingActions.userEnterGBShipmentLength("gbPharmaLength", parcel.getShipmentLength(), index);
        pharmaBookingActions.userEnterGBShipmentWidth("gbPharmaWidth", parcel.getShipmentWidth(), index);
        pharmaBookingActions.userEnterGBShipmentHeight("gbPharmaHeight", parcel.getShipmentHeight(), index);
        pharmaBookingActions.userChooseGBQuantityUnit("gbPharmaUnit", parcel.getShipmentUnit(), index);
    }
}
