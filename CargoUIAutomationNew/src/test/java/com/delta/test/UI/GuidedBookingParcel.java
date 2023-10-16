package com.delta.test.UI;

import com.delta.pageobjects.guidedbooking.GuidedBooking;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.fail;

public class GuidedBookingParcel extends BaseTest {

    @AfterTest
    public void afterEach() {
        driver.close();
    }

    @Test(priority=1)
    public void createAndConfirmGuidedBookingForParcels_STANDARD_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingForParcels_STANDARD_NA_Domestic", "STANDARD", true);
    }

    @Test(priority=2)
    public void createAndConfirmGuidedBookingForParcels_DSH_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingForParcels_DSH_NA_Domestic", "DASH", false);
    }

    @Test(priority=3)
    public void createAndConfirmGuidedBookingForParcels_DMD_NA_Domestic() throws InterruptedException {
        fillParcelForm("createAndConfirmGuidedBookingForParcels_DMD_NA_Domestic", "DASH_CRITICAL", false);
    }

    private void fillParcelForm(String testCaseName, String flightOption, boolean addAdditionalParcels) {
        Login login=new Login(driver);
        GuidedBooking guidedBooking = new GuidedBooking(driver);

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
            guidedBooking.userClicksOnTheGuidedBookingOption();
            guidedBooking.userSelectsGBShipmentType("gbParcels");
            guidedBooking.userEnterGBShipmentOrigin("gbParcelsOrigin", xlShipmentOrigin);
            guidedBooking.userEnterGBShipmentDestination("gbParcelsDestination", xlShipmentDestination);
            guidedBooking.userClickOnGBParcelShipmentDatePicker();
            guidedBooking.userSelectGBParcelDepartureTime();
            guidedBooking.userSelectDeliveryType();

            // Enter quantity, length, weight, height and Units
            Parcel parcel = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
            // Enter details of default Parcel
            addParcel(guidedBooking, parcel, 1);
            if (addAdditionalParcels) {
                // Now add few more parcels
                for (int i = 0; i < 3; i++) {
                    guidedBooking.userChooseAddItem("gbParcelAddParcel");
                    addParcel(guidedBooking, parcel, i + 2);
                }
            }

            guidedBooking.userEnterGBShipmentTotalWeight("gbParcelsTotalWeight", xlShipmentWeight);
            guidedBooking.userChooseGBShipmentUnit("gbParcelsWeightUnits", xlShipmentWeightUnits);
            // Contains dangerous goods Yes or No
            guidedBooking.userSelectYesNoRadioButton(xlShipmentDoPiecesContainDangerousGoods, "gbParcelContainsDangerousGoodsYes", "gbParcelContainsDangerousGoodsNo");
            // Shipment to be rotated Yes or No
            guidedBooking.userSelectYesNoRadioButton(xlShipmentDoPiecesBeRotated, "gbParcelCanbeRotatedYes", "gbParcelCanbeRotatedNo");
            // Shipment to be pre-screened Yes or No
            guidedBooking.userSelectYesNoRadioButton(xlShipmentBePreScreened, "gbParcelPrescreenedYes", "gbParcelPrescreenedNo");
            guidedBooking.userClicksOnGBFindFlights();

            // Select flight option
            switch (flightOption) {
                case "STANDARD":
                    guidedBooking.userSelectOneFlightStandard();
                    break;
                case "DASH":
                    guidedBooking.userSelectOneFlightDash();
                    break;
                case "DASH_CRITICAL":
                    guidedBooking.userSelectOneFlightDashCritical();
                    break;
                default:
                    fail("Invalid flight option is provided");
            }

            guidedBooking.userWaitsToFillCargoShipmentRequest();
            guidedBooking.userEnterRecipientDetails( new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            Reporter.log("user logged In Successfully");
        } else {
            throw new SkipException("Test Ignored");
        }
    }

    private void addParcel(GuidedBooking guidedBooking, Parcel parcel, int index) {
        guidedBooking.userEnterGBShipmentQuantity("gbParcelsQuantity", parcel.getShipmentQuantity(), index);
        guidedBooking.userEnterGBShipmentLength("gbParcelsLength", parcel.getShipmentLength(), index);
        guidedBooking.userEnterGBShipmentWidth("gbParcelsWidth", parcel.getShipmentWidth(), index);
        guidedBooking.userEnterGBShipmentHeight("gbParcelsHeight", parcel.getShipmentHeight(), index);
        guidedBooking.userChooseGBQuantityUnit("gbParcelsQuantityUnits", parcel.getShipmentUnit(), index);
    }
}
