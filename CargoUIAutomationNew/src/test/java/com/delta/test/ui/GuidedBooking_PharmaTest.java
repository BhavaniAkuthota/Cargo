package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.GBActions;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.pageobjects.guidedbooking.PharmaActions;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;

public class GuidedBooking_PharmaTest extends BaseTest {
    private String prefix = "ph";


    @Test(priority = 1)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma1ActiveContainer_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma1ActiveContainer_Domestic";
        fillPharmaForm(testCaseName, 1, false);
    }

    @Test(priority = 2)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma1PassiveContainer_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma1PassiveContainer_Domestic";
        fillPharmaForm(testCaseName, 2, false);
    }

    @Test(priority = 3)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma2Temperaturecontrolledbetween2Cto8C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma2Temperaturecontrolledbetween2Cto8C_Domestic";
        fillPharmaForm(testCaseName, 3, true);
    }

    @Test(priority = 4)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma3Temperaturecontrolledbetween2Cto25C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma3Temperaturecontrolledbetween2Cto25C_Domestic";
        fillPharmaForm(testCaseName, 4, false);
    }

    @Test(priority = 5)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma4Temperaturecontrolledbetween15Cto25C_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma4Temperaturecontrolledbetween15Cto25C_Domestic";
        fillPharmaForm(testCaseName, 5, false);
    }

    @Test(priority = 6)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma1ActiveContainer_International() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma1ActiveContainer_International";
        fillPharmaForm(testCaseName, 1, false);
    }

    @Test(priority = 7)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma1PassiveContainer_International() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma1PassiveContainer_International";
        fillPharmaForm(testCaseName, 2, false);
    }

    @Test(priority = 8)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma2Temperaturecontrolledbetween2Cto8C_International() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma2Temperaturecontrolledbetween2Cto8C_International";
        fillPharmaForm(testCaseName, 3, true);
    }

    @Test(priority = 9)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma3Temperaturecontrolledbetween2Cto25C_International() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma3Temperaturecontrolledbetween2Cto25C_International";
        fillPharmaForm(testCaseName, 4, false);
    }

    @Test(priority = 10)
    public void createAndConfirmGuidedBookingPharma_PHARMA_Pharma4Temperaturecontrolledbetween15Cto25C_International() {
        String testCaseName = "createAndConfirmGuidedBookingPharma_PHARMA_Pharma4Temperaturecontrolledbetween15Cto25C_International";
        fillPharmaForm(testCaseName, 5, false);
    }

    private void fillPharmaForm(String testCaseName, int pharmaIndex, boolean addAdditionalPharma) {
        Login login = new Login(driver, xlMapper.getXpathIDMap());
        PharmaActions pharmaActions = new PharmaActions(driver, xlMapper.getXpathIDMap());
        GBActions gbActions = new GBActions(driver, xlMapper.getXpathIDMap());

        String testCaseSheetName = "GBPharma";
        Map<String, Object> eachRowMap = xlMapper.excelReadTestData(testCaseSheetName, testCaseName);
        String execution = (String) eachRowMap.get("Execution");

        if (execution.equalsIgnoreCase("Yes")) {
            //Switch Account
            String xlAccountType = (String) eachRowMap.get("AccountType");
            String xlAccountNumber = (String) eachRowMap.get("AccountNumber");

            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");

            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            String flightOption = (String) eachRowMap.get("ShipmentProduct");
            System.err.println("O: " + xlShipmentOrigin + " D: " + xlShipmentDestination + " Type: " + flightOption);
            String xlShipmentRequireDeltaContainer = (String) eachRowMap.get("ShipmentRequireDeltaContainer");

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
            pharmaActions.userClicksOnTheGuidedBookingOption();
            pharmaActions.userSelectsGBShipmentType("gbPharma");
            pharmaActions.userEnterGBShipmentOrigin("gbPharmaOrigin", xlShipmentOrigin);
            pharmaActions.userEnterGBShipmentDestination("gbPharmaDestination", xlShipmentDestination);
            pharmaActions.userClickOnGBPharmaShipmentDatePicker();
            pharmaActions.userSelectGBPharmaDepartureTime();
            pharmaActions.userSelectGBPharmaType(pharmaIndex, 1);

            switch (pharmaIndex) {
                case 1:
                case 2:
                case 6:
                case 7:
                    // Container required Yes or No
                    pharmaActions.userSelectYesNoRadioButton(xlShipmentRequireDeltaContainer, "gbPharmaContainerRequiredYes", "gbPharmaContainerRequiredNo");
                    pharmaActions.userSelectContainerType();
                    pharmaActions.userEnterTotalNumberOfContainers(xlShipmentQuantity);
                    // Total Pharma Weight
                    pharmaActions.userEnterTotalContainersWeight(xlShipmentWeight);
                    pharmaActions.userChooseGBQuantityUnit("gbPharmaUnit", xlShipmentWeightUnits, 1);
                    break;
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                    // Enter quantity, length, weight, height and Units
                    Parcel parcel = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
                    // Enter details of default Pharma
                    addPharma(prefix, pharmaActions, parcel, 1);
                    if (addAdditionalPharma) {
                        // Now add few more parcels
                        for (int i = 0; i < 3; i++) {
                            pharmaActions.userChooseAddItem("gbPharmaAddItem");
                            pharmaActions.userSelectGBPharmaType(pharmaIndex, i + 2);
                            addPharma(prefix, pharmaActions, parcel, i + 2);
                        }
                    }

                    pharmaActions.userEnterGBShipmentTotalWeight("gbPharmaTotalWeight", xlShipmentWeight);
                    pharmaActions.userChooseGBShipmentUnit("gbPharmaSelectTotalWeightUnit", xlShipmentWeightUnits);

                    break;
                default:
                    fail("Invalid Pharma Type is provided");
            }

            // Contains dangerous goods Yes or No
            pharmaActions.userSelectYesNoRadioButton(xlShipmentDoPiecesContainDangerousGoods, "gbPharmaContainsDangerousGoodsYes", "gbPharmaContainsDangerousGoodsNo");
            // Pieces to be rotated Yes or No
            pharmaActions.userSelectYesNoRadioButton(xlShipmentDoPiecesBeRotated, "gbPharmaCanbeRotatedYes", "gbPharmaCanbeRotatedNo");
            // Shipment to be pre-screened Yes or No
            pharmaActions.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbPharmaPrescreenedYes", "gbPharmaPrescreenedNo");

            pharmaActions.userClicksOnGBFindFlights();
            gbActions.userSelectShipmentTypeFromFlightResults(flightOption);
//            gbActions.userSelectOneFlight();
            pharmaActions.userWaitsToFillCargoShipmentRequest();
            pharmaActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            gbActions.validateShipmentType(flightOption);
        }
    }

    private void addPharma(String prefix, PharmaActions pharmaActions, Parcel parcel, int index) {
        pharmaActions.userEnterGBShipmentQuantity(prefix, "gbPharmaQuantity", parcel.getShipmentQuantity(), index);
        pharmaActions.userEnterGBShipmentLength(prefix, "gbPharmaLength", parcel.getShipmentLength(), index);
        pharmaActions.userEnterGBShipmentWidth(prefix, "gbPharmaWidth", parcel.getShipmentWidth(), index);
        pharmaActions.userEnterGBShipmentHeight(prefix, "gbPharmaHeight", parcel.getShipmentHeight(), index);
        pharmaActions.userChooseGBQuantityUnit("gbPharmaUnit", parcel.getShipmentUnit(), index);
    }
}
