package com.delta.test.ui;

import com.delta.pageobjects.guidedbooking.GBActions;
import com.delta.pageobjects.guidedbooking.ParcelActions;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import com.delta.util.xlMapper;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;

public class GuidedBooking_ParcelTest extends BaseTest {

    private String prefix = "pr";

    String shipmentType = "gbParcel";

    @Test(priority = 1)
    public void createAndConfirmGuidedBookingParcels_STANDARD_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_STANDARD_NA_Domestic",  true);
    }

    @Test(priority = 2)
    public void createAndConfirmGuidedBookingParcels_DSH_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_DSH_NA_Domestic", false);
    }

    @Test(priority = 3)
    public void createAndConfirmGuidedBookingParcels_DMD_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_DMD_NA_Domestic", false);
    }

    @Test(priority = 4)
    public void createAndConfirmGuidedBookingParcels_GENERAL_NA_International() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_GENERAL_NA_International", false);
    }

    @Test(priority = 5)
    public void createAndConfirmGuidedBookingParcels_EXPRESS_NA_International() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_EXPRESS_NA_International", false);
    }

    @Test(priority = 6)
    public void createAndConfirmGuidedBookingParcels_CRITICAL_NA_International() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingParcels_CRITICAL_NA_International", false);
    }

    private void fillParcelForm(String testCaseName, boolean addAdditionalParcels) {
        Login login = new Login(driver, xlMapper.getXpathIDMap());
        ParcelActions parcelActions = new ParcelActions(driver, xlMapper.getXpathIDMap());
        GBActions gbActions = new GBActions(driver, xlMapper.getXpathIDMap());

        String testCaseSheetName = "GBParcel";
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
            System.err.println("O: " + xlShipmentOrigin + " D: " + xlShipmentDestination + " Type: " + flightOption);
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
            addParcel(prefix, parcelActions, parcel, 1);
            if (addAdditionalParcels) {
                // Now add few more parcels
                for (int i = 0; i < 2; i++) {
                    parcelActions.userChooseAddItem("gbParcelAddParcel");
                    addParcel(prefix, parcelActions, parcel, i + 2);
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
            parcelActions.userClicksOnGBFindFlights(shipmentType);
            gbActions.userSelectShipmentTypeFromFlightResults(flightOption);
//            parcelActions.userSelectOneFlight(flightOption);


            parcelActions.userWaitsToFillCargoShipmentRequest();
            parcelActions.userEnterRecipientDetails(new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            gbActions.validateShipmentType(flightOption);
            Reporter.log("user logged In Successfully");
        } else {
            throw new SkipException("Test Ignored");
        }
    }

    private void addParcel(String prefix, ParcelActions parcelActions, Parcel parcel, int index) {
        parcelActions.userEnterGBShipmentQuantity(prefix, "gbParcelsQuantity", parcel.getShipmentQuantity(), index);
        parcelActions.userEnterGBShipmentLength(prefix, "gbParcelsLength", parcel.getShipmentLength(), index);
        parcelActions.userEnterGBShipmentWidth(prefix, "gbParcelsWidth", parcel.getShipmentWidth(), index);
        parcelActions.userEnterGBShipmentHeight(prefix, "gbParcelsHeight", parcel.getShipmentHeight(), index);
        parcelActions.userChooseGBQuantityUnit("gbParcelsQuantityUnits", parcel.getShipmentUnit(), index);
    }
}
