package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.GBActions;
import com.delta.pageobjects.guidedbooking.HumanRemainsActions;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;

public class GuidedBooking_HumanRemainsTest extends BaseTest {

    String testCaseSheetName = "GBHumanRemains";
    String shipmentType = "gbHumanRemains";
    static String prefix = "hu";
    static String prefixRotated = "Care";

    @Test(priority = 1)
    public void createAndConfirmGuidedBookingHumanRemains_CARE_Adult_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingHumanRemains_CARE_Adult_Domestic";
        fillForm(testCaseName, 1);
    }
    @Test(priority = 2)
    public void createAndConfirmGuidedBookingHumanRemains_CARE_ChildInfant_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingHumanRemains_CARE_ChildInfant_Domestic";
        fillForm(testCaseName, 2);
    }
    @Test(priority = 3)
    public void createAndConfirmGuidedBookingHumanRemains_CARE_CrematedRemains_Domestic() {
        String testCaseName = "createAndConfirmGuidedBookingHumanRemains_CARE_CrematedRemains_Domestic";
        fillForm(testCaseName, 3);
    }
    @Test(priority = 4)
    public void createAndConfirmGuidedBookingHumanRemains_CARE_Adult_International() {
        String testCaseName = "createAndConfirmGuidedBookingHumanRemains_CARE_Adult_International";
        fillForm(testCaseName, 2);
    }
    @Test(priority = 5)
    public void createAndConfirmGuidedBookingHumanRemains_CARE_ChildInfant_International() {
        String testCaseName = "createAndConfirmGuidedBookingHumanRemains_CARE_ChildInfant_International";
        fillForm(testCaseName, 3);
    }
    @Test(priority = 6)
    public void createAndConfirmGuidedBookingHumanRemains_CARE_CrematedRemains_International() {
        String testCaseName = "createAndConfirmGuidedBookingHumanRemains_CARE_CrematedRemains_International";
        fillForm(testCaseName, 1);
    }
    private void fillForm(String testCaseName, int humanremainSelectIndex) {
        Login login = new Login(driver, xlMapper.getXpathIDMap());
        HumanRemainsActions humanRemainsActions = new HumanRemainsActions(driver, xlMapper.getXpathIDMap());
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

            String xlShipmentLength = (String) eachRowMap.get("ShipmentLength");
            String xlShipmentWidth = (String) eachRowMap.get("ShipmentWidth");
            String xlShipmentHeight = (String) eachRowMap.get("ShipmentHeight");
            String xlShipmentQuantityUnit = (String) eachRowMap.get("ShipmentUnit");


            String xlRecipientAccountNumber = (String) eachRowMap.get("RecipientInformationAccountNumber");
            String xlShipmentDescription = (String) eachRowMap.get("ShipmentDescription");

            //CODE
            login.userLoginAsDomesticAccount(loginUsername, loginPassword);
            login.switchAccountType(xlAccountType, xlAccountNumber);
            gbActions.userClicksOnTheGuidedBookingOption();
            gbActions.userSelectsGBShipmentType("gbSpecialty");
            gbActions.userSelectsSpecialtyHumanRemains();
            gbActions.userEnterGBShipmentOrigin("gbHumanRemainsOrigin", xlShipmentOrigin);
            gbActions.userEnterGBShipmentDestination("gbHumanRemainsDestination", xlShipmentDestination);
            humanRemainsActions.userClickOnGBHumanRemainsShipmentDatePicker();
            humanRemainsActions.userSelectGBHumanRemainsDepartureTime();

            //HumanRemains Code
            humanRemainsActions.userSelectGBHumanRemainsType(humanremainSelectIndex);

            switch (humanremainSelectIndex) {
                case 1:
                case 2:
                case 3:
                    humanRemainsActions.userSelectAirContainerType();
                    gbActions.userEnterGBShipmentTotalWeight("gbHumanRemainsTotalWeight", xlShipmentWeight);
                    gbActions.userChooseGBShipmentUnit("gbHumanRemainsWeightUnits", xlShipmentWeightUnits);
                    gbActions.userEnterGBShipmentLength("gbHumanRemainsLength", xlShipmentLength);
                    gbActions.userEnterGBShipmentWidth("gbHumanRemainsWidth", xlShipmentWidth);
                    gbActions.userEnterGBShipmentHeight("gbHumanRemainsHeight", xlShipmentHeight);
                    gbActions.userChooseGBQuantityUnit("gbHumanRemainsQuantityUnits", xlShipmentQuantityUnit);
                    break;
                default:
                    fail("Invalid HumanRemain Type is provided");
            }
            humanRemainsActions.userClicksOnGBHumanRemainsFindFlights(shipmentType);
            gbActions.userSelectShipmentTypeFromFlightResults(flightOption);
            gbActions.userWaitsToFillCargoShipmentRequest();
            gbActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            gbActions.validateShipmentType(flightOption);
        }
    }
}
