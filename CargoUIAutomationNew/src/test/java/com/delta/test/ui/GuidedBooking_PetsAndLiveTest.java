package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.GBActions;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.pageobjects.guidedbooking.PetLive;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.annotations.Test;

import java.util.Map;

public class GuidedBooking_PetsAndLiveTest extends BaseTest {


    String testCaseSheetName = "GBPetLive";
    String shipmentType = "gbPetsandLive";
    static String prefix = "pe";
    static String prefixRotated = "medical";


    @Test(priority = 1)
    public void createAndConfirmGuidedBookingPetsandLive_LIVE_Bird_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_LIVE_Bird_Domestic";
        fillForm(testCaseName, 1, "Bird", false);

    }

    @Test(priority = 2)
    public void createAndConfirmGuidedBookingPetsandLive_LIVE_Cat_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_LIVE_Cat_Domestic";
        fillForm(testCaseName, 2, "Bird", false);
    }

    @Test(priority = 3)
    public void createAndConfirmGuidedBookingPetsandLive_LIVE_ColdBloodedAnimal_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_LIVE_ColdBloodedAnimal_Domestic";
        fillForm(testCaseName, 3, "Bird", false);
    }

    @Test(priority = 4)
    public void createAndConfirmGuidedBookingPetsandLive_LIVE_Dog_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_LIVE_Dog_Domestic";
        fillForm(testCaseName, 4, "Bird", false);
    }

    @Test(priority = 5)
    public void createAndConfirmGuidedBookingPetsandLive_LIVE_HatchingEggs_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_LIVE_HatchingEggs_Domestic";
        fillForm(testCaseName, 5, "Bird", false);
    }

    @Test(priority = 6)
    public void createAndConfirmGuidedBookingPetsandLive_LIVE_WarmBloodedAnimal_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_LIVE_WarmBloodedAnimal_Domestic";
        fillForm(testCaseName, 4, "Bird", false);
    }


    @Test(priority = 7)
    public void createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_Bird_International() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_Bird_International";
        fillForm(testCaseName, 3, "Bird", false);
    }

    @Test(priority = 8)
    public void createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_Cat_International() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_Cat_International";
        fillForm(testCaseName, 1, "Bird", false);
    }

    @Test(priority = 9)
    public void createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_ColdBloodedAnimal_International() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_ColdBloodedAnimal_International";
        fillForm(testCaseName, 2, "Bird", false);
    }

    @Test(priority = 10)
    public void createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_Dog_International() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_Dog_International";
        fillForm(testCaseName, 5, "Bird", false);
    }

    @Test(priority = 11)
    public void createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_HatchingEggs_International() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_HatchingEggs_International";
        fillForm(testCaseName, 6, "Bird", false);
    }

    @Test(priority = 12)
    public void createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_WarmBloodedAnimal_International() {
        String testCaseName = "createAndConfirmGuidedBookingPetsandLive_SPECIALIZED_LIVE_WarmBloodedAnimal_International";
        fillForm(testCaseName, 6, "Bird", false);
    }


    private void fillForm(String testCaseName, int index, String subtype, boolean addAdditionalInfo) {

        Login login = new Login(driver, xlMapper.getXpathIDMap());

        PetLive petLive = new PetLive(driver, xlMapper.getXpathIDMap());
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

            String xlShipmentQuantity = (String) eachRowMap.get("ShipmentQuantity");
            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
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
            petLive.userSelectPetTypeSelector(index, 1);

            switch (subtype) {
                case "Bird":
                    petLive.userSelectGBPetSubType(index, index);
                    petLive.userSelectPersonalHousholdType("Yes", index);
                    petLive.userEnterBreedName("BreedNameABC", index);
                    gbActions.userEnterGBShipmentQuantity(prefix, "gbPharmaQuantity", xlShipmentQuantity, index);
                    gbActions.userEnterGBShipmentTotalWeight(prefix, "gbPharmaWeight", xlShipmentWeight, index);
                    petLive.selectPetContainerDimension(xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit, xlShipmentWeight, xlShipmentWeightUnits);
                    petLive.selectTermsAndCondition();
                    break;
                case "Day Old Chicks":

                case "Cat":
                case "Reptile/Amphibian/etc":
                case "Fish":
                case "Dog":
                case "Hatching Eggs":
                case "Rats/Mice":
                case "Others":
                    break;
                default:
                    Parcel parcel1 = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
                    gbActions.userEnterGBShipmentTotalWeight(prefix, "gbParcelsTotalWeight", xlShipmentWeight);
                    gbActions.userChooseGBQuantityUnit(prefix, "gbPharmaUnit", xlShipmentWeightUnits, 1);
                    break;
            }
            gbActions.userClicksOnGBFindFlights(shipmentType);
            gbActions.userSelectOneFlight();
            gbActions.userWaitsToFillCargoShipmentRequest();
            gbActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
        }
    }


}
