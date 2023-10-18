package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.ParcelActions;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;

public class GuidedBookingParcelTest extends BaseTest {

    @AfterTest
    public void afterEach() {
        driver.close();
    }

    @Test(priority=1)
    public void createAndConfirmGuidedBookingParcels_STANDARD_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_STANDARD_NA_Domestic", "STANDARD", true);
    }

    @Test(priority=2)
    public void createAndConfirmGuidedBookingParcels_DSH_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_DSH_NA_Domestic", "DASH", false);
    }

    @Test(priority=3)
    public void createAndConfirmGuidedBookingParcels_DMD_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_DMD_NA_Domestic", "DASH_CRITICAL", false);
    }

    private void fillParcelForm(String testCaseName, String flightOption, boolean addAdditionalParcels) {
        Login login=new Login(driver);
        ParcelActions parcelActions = new ParcelActions(driver);

        String testCaseSheetName = "GBParcel";
        Map<String, Object> eachRowMap = login.excelReadTestData(testCaseSheetName, testCaseName);
        String execution=(String) eachRowMap.get("Execution");

        if(execution.equalsIgnoreCase("Yes")) {
            String loginUsername = (String) eachRowMap.get("LoginUsername");
            String loginPassword = (String) eachRowMap.get("LoginPassword");
            String xlShipmentOrigin = (String) eachRowMap.get("ShipmentOrigin");
            String xlShipmentDestination = (String) eachRowMap.get("ShipmentDestination");
            String xlShipmentWeight = (String) eachRowMap.get("ShipmentWeight");
            String xlShipmentWeightUnits = (String) eachRowMap.get("ShipmentWeightUnits");
            String xlShipmentQuantity=(String) eachRowMap.get("ShipmentQuantity");
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
            parcelActions.userClicksOnTheGuidedBookingOption();
            parcelActions.userSelectsGBShipmentType("gbParcels");
            parcelActions.userEnterGBShipmentOrigin("gbParcelsOrigin", xlShipmentOrigin);
            parcelActions.userEnterGBShipmentDestination("gbParcelsDestination", xlShipmentDestination);
            parcelActions.userClickOnGBParcelShipmentDatePicker();
            parcelActions.userSelectGBParcelDepartureTime();
            parcelActions.userSelectDeliveryType();

            // Enter quantity, length, weight, height and Units
            Parcel parcel = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
            // Enter details of default Parcel
            addParcel(parcelActions, parcel, 1);
            if (addAdditionalParcels) {
                // Now add few more parcels
                for (int i = 0; i < 3; i++) {
                    parcelActions.userChooseAddItem("gbParcelAddParcel");
                    addParcel(parcelActions, parcel, i + 2);
                }
            }

            parcelActions.userEnterGBShipmentTotalWeight("gbParcelsTotalWeight", xlShipmentWeight);
            parcelActions.userChooseGBShipmentUnit("gbParcelsWeightUnits", xlShipmentWeightUnits);
            // Contains dangerous goods Yes or No
            parcelActions.userSelectYesNoRadioButton(xlShipmentDoPiecesContainDangerousGoods, "gbParcelContainsDangerousGoodsYes", "gbParcelContainsDangerousGoodsNo");
            // Shipment to be rotated Yes or No
            parcelActions.userSelectYesNoRadioButton(xlShipmentDoPiecesBeRotated, "gbParcelCanbeRotatedYes", "gbParcelCanbeRotatedNo");
            // Shipment to be pre-screened Yes or No
            parcelActions.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbParcelPrescreenedYes", "gbParcelPrescreenedNo");
            parcelActions.userClicksOnGBFindFlights();

            // Select flight option
            switch (flightOption) {
                case "STANDARD":
                    parcelActions.userSelectOneFlightStandard();
                    break;
                case "DASH":
                    parcelActions.userSelectOneFlightDash();
                    break;
                case "DASH_CRITICAL":
                    parcelActions.userSelectOneFlightDashCritical();
                    break;
                default:
                    fail("Invalid flight option is provided");
            }

            parcelActions.userWaitsToFillCargoShipmentRequest();
            parcelActions.userEnterRecipientDetails( new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            Reporter.log("user logged In Successfully");
        } else {
            throw new SkipException("Test Ignored");
        }
    }

    private void addParcel(ParcelActions parcelActions, Parcel parcel, int index) {
        parcelActions.userEnterGBShipmentQuantity("gbParcelsQuantity", parcel.getShipmentQuantity(), index);
        parcelActions.userEnterGBShipmentLength("gbParcelsLength", parcel.getShipmentLength(), index);
        parcelActions.userEnterGBShipmentWidth("gbParcelsWidth", parcel.getShipmentWidth(), index);
        parcelActions.userEnterGBShipmentHeight("gbParcelsHeight", parcel.getShipmentHeight(), index);
        parcelActions.userChooseGBQuantityUnit("gbParcelsQuantityUnits", parcel.getShipmentUnit(), index);
    }
}
