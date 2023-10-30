package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.*;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.annotations.Test;

import java.util.Map;

import static com.delta.util.BaseTest.driver;

public class GuidedBooking_MedicalTest extends BaseTest {


    String testCaseSheetName = "GBMedical";
    String shipmentType = "gbMedical";
    static String prefix = "me";
    static String prefixRotated = "medical";


    @Test(priority = 1)
    public void createAndConfirmGuidedBookingMedical_DMD_MedicalshipmentMedevac_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_DMD_MedicalshipmentMedevac_Domestic";
        fillForm(testCaseName, 1, false);

    }

    @Test(priority = 2)
    public void createAndConfirmGuidedBookingMedical_DMD_MedicalshipmentfortransplantLiveHumanOrgan_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_DMD_MedicalshipmentfortransplantLiveHumanOrgan_Domestic";
        fillForm(testCaseName, 2, false);
    }

    @Test(priority = 3)
    public void createAndConfirmGuidedBookingMedical_DMD_Lifesavingdrugsandmedicationorcordblood_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_DMD_Lifesavingdrugsandmedicationorcordblood_Domestic";
        fillForm(testCaseName, 3, false);
    }

    @Test(priority = 4)
    public void createAndConfirmGuidedBookingMedical_DMD_MedicalshipmentfortransplantOther_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_DMD_MedicalshipmentfortransplantOther_Domestic";
        fillForm(testCaseName, 4, false);
    }

    @Test(priority = 5)
    public void createAndConfirmGuidedBookingMedical_DSH_MedicalshipmentnotfortransplantDiagnosticSpecimen_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_DSH_MedicalshipmentnotfortransplantDiagnosticSpecimen_Domestic";
        fillForm(testCaseName, 5, false);
    }

    @Test(priority = 6)
    public void createAndConfirmGuidedBookingMedical_DHV_MedicalshipmentnotfortransplantOther_International() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_DHV_MedicalshipmentnotfortransplantOther_International";
        fillForm(testCaseName, 4, false);
    }


    @Test(priority = 7)
    public void createAndConfirmGuidedBookingMedical_CRITICAL_HEAVY_Lifesavingdrugsandmedicationorcordblood_International() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_CRITICAL_HEAVY_Lifesavingdrugsandmedicationorcordblood_International";
        fillForm(testCaseName, 3, false);
    }

    @Test(priority = 8)
    public void createAndConfirmGuidedBookingMedical_CRITICAL_MedicalshipmentMedevac_International() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_CRITICAL_MedicalshipmentMedevac_International";
        fillForm(testCaseName, 1, false);
    }

    @Test(priority = 9)
    public void createAndConfirmGuidedBookingMedical_CRITICAL_HEAVY_MedicalshipmentfortransplantLiveHumanOrgan_International() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_CRITICAL_HEAVY_MedicalshipmentfortransplantLiveHumanOrgan_International";
        fillForm(testCaseName, 2, false);
    }

    @Test(priority = 10)
    public void createAndConfirmGuidedBookingMedical_CRITICAL_MedicalshipmentfortransplantOther_International() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_CRITICAL_MedicalshipmentfortransplantOther_International";
        fillForm(testCaseName, 5, false);
    }

    @Test(priority = 11)
    public void createAndConfirmGuidedBookingMedical_EXPRESS_HEAVY_MedicalshipmentnotfortransplantDiagnosticSpecimen_International() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_EXPRESS_HEAVY_MedicalshipmentnotfortransplantDiagnosticSpecimen_International";
        fillForm(testCaseName, 6, false);
    }

    @Test(priority = 12)
    public void createAndConfirmGuidedBookingMedical_EXPRESS_MedicalshipmentnotfortransplantOther_International() {
        String testCaseName = "createAndConfirmGuidedBookingMedical_EXPRESS_MedicalshipmentnotfortransplantOther_International";
        fillForm(testCaseName, 6, false);
    }


    private void fillForm(String testCaseName, int index, boolean addAdditionalInfo) {

        Login login = new Login(driver, xlMapper.getXpathIDMap());

        MedicalActions medicalActions = new MedicalActions(driver, xlMapper.getXpathIDMap());
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
            medicalActions.userSelectGBMedicalType(index, 1);
            switch (index) {
//                case 11:
//                    gbActions.userSelectYesNoRadioButton(prefix, xlShipmentRequireDeltaContainer, "gbPharmaContainerRequiredYes", "gbPharmaContainerRequiredNo");
//                    medicalActions.userSelectCommodityType();
//                    gbActions.userSelectContainerType(prefix, xlShipmentRequireDeltaContainer);
//                    gbActions.userEnterTotalNumberOfContainers(prefix, xlShipmentQuantity);
//                    gbActions.userEnterTotalContainersWeight(prefix, xlShipmentWeight);
//                    gbActions.userChooseGBQuantityUnit(prefix, "gbPharmaUnit", xlShipmentWeightUnits, 1);
//                    break;
                default:
                    Parcel parcel1 = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
                    addShipmentDimensions(gbActions, parcel1, 1);
                    gbActions.userEnterGBShipmentTotalWeight(prefix, "gbParcelsTotalWeight", xlShipmentWeight);
                    gbActions.userChooseGBQuantityUnit(prefix, "gbPharmaUnit", xlShipmentWeightUnits, 1);
                    break;
            }
            gbActions.userSelectYesNoRadioButton(prefix, xlShipmentDoPiecesContainDangerousGoods, "gbPharmaContainsDangerousGoodsYes", "gbPharmaContainsDangerousGoodsNo");
            gbActions.userSelectYesNoRadioButtonRotated(prefixRotated, xlShipmentDoPiecesBeRotated, "gbPharmaCanbeRotatedYes", "gbPharmaCanbeRotatedNo");
            gbActions.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbMedicalPreScreenedYes", "gbMedicalPreScreenedNo");
            gbActions.userClicksOnGBFindFlights(shipmentType);
            gbActions.userSelectOneFlight();
            gbActions.userWaitsToFillCargoShipmentRequest();
            gbActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
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
