package com.delta.pageobjects.guidedbooking;

public class Parcel {
    private final String shipmentQuantity;
    private final String shipmentLength;
    private final String shipmentWidth;
    private final String shipmentHeight;
    private final String shipmentUnit;

    public Parcel(String quantity, String length, String width, String height, String unit) {
        this.shipmentQuantity = quantity;
        this.shipmentLength = length;
        this.shipmentWidth = width;
        this.shipmentHeight = height;
        this.shipmentUnit = unit;
    }

    public String getShipmentQuantity() {
        return shipmentQuantity;
    }

    public String getShipmentLength() {
        return shipmentLength;
    }

    public String getShipmentWidth() {
        return shipmentWidth;
    }

    public String getShipmentHeight() {
        return shipmentHeight;
    }

    public String getShipmentUnit() {
        return shipmentUnit;
    }
}
