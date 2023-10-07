package com.delta.test.UI;

import com.delta.pageobjects.guidedbooking.GuidedBooking;
import com.delta.pageobjects.guidedbooking.Parcel;
import com.delta.util.BaseTest;
import com.delta.util.Login;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Map;

public class GuidedBookingParcel extends BaseTest {



    @Test(priority=1 )
    public void createAndConfirmGuidedBookingForParcels_STANDARD_NA_Domestic() throws InterruptedException {

        Login login=new Login(driver);
        GuidedBooking guidedBooking = new GuidedBooking(driver);

        String testCaseSheetName = "GBParcel";
        String testCaseName = "createAndConfirmGuidedBookingForParcels_STANDARD_NA_Domestic";
        Map<String, Object> eachRowMap = login.excelReadTestData(testCaseSheetName,testCaseName);
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
            login.userLoginAsDomesticAccount(loginUsername,loginPassword);
            guidedBooking.userClicksOnTheGuidedBookingOption();
            guidedBooking.userSelectsGbShipmentTypes();
            guidedBooking.userEnterGbParcelShipmentOrigin(xlShipmentOrigin);
            guidedBooking.userEnterGbParcelShipmentDestination(xlShipmentDestination);
            guidedBooking.userClickOnGbParcelShipmentDatePicker();
            guidedBooking.userSelectGbParcelDepartureTime();
            guidedBooking.userSelectDeliveryType();

            Parcel parcel = new Parcel(xlShipmentQuantity, xlShipmentLength, xlShipmentWidth, xlShipmentHeight, xlShipmentQuantityUnit);
            // Enter details of default Parcel
            addParcel(guidedBooking, parcel, 1, false);
            // Now add few more parcels
            for (int i = 0; i < 3; i++) {
                addParcel(guidedBooking, parcel, i + 2, true);
            }

            guidedBooking.userEnterGbParcelShipmentWeight(xlShipmentWeight);
            guidedBooking.userChooseGbParcelShipmentUnit(xlShipmentWeightUnits);
            guidedBooking.userChooseGbParcelShipmentContainsDangerousGood(xlShipmentDoPiecesContainDangerousGoods);
            guidedBooking.userChooseGbParcelShipmentPiecesBeRotated(xlShipmentDoPiecesBeRotated);
            guidedBooking.userChooseGbParcelShipmentBePreScreened(xlShipmentBePreScreened);
            guidedBooking.userClicksOnGbFindFlights();
            guidedBooking.userSelectOneFlight();
            guidedBooking.userWaitsToFillCargoShipmentRequest();
            guidedBooking.userEnterRecipientDetails( new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            Reporter.log("user logged In Successfully");
        } else {
            throw new SkipException("Test Ignored");
        }
    }

    private void addParcel(GuidedBooking guidedBooking, Parcel parcel, int index, boolean addParcel) {
        if (addParcel) {
            guidedBooking.userChooseAddParcel();
        }
        guidedBooking.userEnterGbParcelShipmentQuantity(parcel.getShipmentQuantity(), index);
        guidedBooking.userEnterGbParcelShipmentLength(parcel.getShipmentLength(), index);
            guidedBooking.userEnterGbParcelShipmentWidth(parcel.getShipmentWidth(), index);
        guidedBooking.userEnterGbParcelShipmentHeight(parcel.getShipmentHeight(), index);
        guidedBooking.userChooseGbParcelQuantityUnit(parcel.getShipmentUnit(), index);
    }

    @Test(priority=2)
    public void createAndConfirmGuidedBookingForParcels_DSH_NA_Domestic() throws InterruptedException {

        Login login=new Login(driver);
        GuidedBooking guidedBooking = new GuidedBooking(driver);

        String testCaseSheetName = "GBParcel";
        String testCaseName = "";
        Map<String, Object> eachRowMap = login.excelReadTestData(testCaseSheetName,testCaseName);
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
            login.userLoginAsDomesticAccount(loginUsername,loginPassword);
            guidedBooking.userClicksOnTheGuidedBookingOption();
            guidedBooking.userSelectsGbShipmentTypes();
            guidedBooking.userEnterGbParcelShipmentOrigin(xlShipmentOrigin);
            guidedBooking.userEnterGbParcelShipmentDestination(xlShipmentDestination);
            guidedBooking.userClickOnGbParcelShipmentDatePicker();
            guidedBooking.userSelectGbParcelDepartureTime();
            guidedBooking.userSelectDeliveryType();
            guidedBooking.userEnterGbParcelShipmentQuantity(xlShipmentQuantity, 1);
            guidedBooking.userEnterGbParcelShipmentLength(xlShipmentLength, 1);
            guidedBooking.userEnterGbParcelShipmentWidth(xlShipmentWidth, 1);
            guidedBooking.userEnterGbParcelShipmentHeight(xlShipmentHeight, 1);
            guidedBooking.userChooseGbParcelQuantityUnit(xlShipmentQuantityUnit, 1);
            guidedBooking.userEnterGbParcelShipmentWeight(xlShipmentWeight);
            guidedBooking.userChooseGbParcelShipmentUnit(xlShipmentWeightUnits);
            guidedBooking.userChooseGbParcelShipmentContainsDangerousGood(xlShipmentDoPiecesContainDangerousGoods);
            guidedBooking.userChooseGbParcelShipmentPiecesBeRotated(xlShipmentDoPiecesBeRotated);
            guidedBooking.userChooseGbParcelShipmentBePreScreened(xlShipmentBePreScreened);
            guidedBooking.userClicksOnGbFindFlights();
            guidedBooking.userSelectOneFlightDash();
            guidedBooking.userWaitsToFillCargoShipmentRequest();
            guidedBooking.userEnterRecipientDetails( new String[]{xlShipmentDescription,
                    xlRecipientAccountNumber});
            Reporter.log("user logged In Successfully");
        } else {
            throw new SkipException("Test Ignored");
        }
    }
}
