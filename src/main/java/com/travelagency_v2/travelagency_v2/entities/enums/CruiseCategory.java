package com.travelagency_v2.travelagency_v2.entities.enums;


public enum CruiseCategory {

    INSIDE("Inside room"), OUTSIDE("Outside room"), BALCONY("Room with balcony"), SUITE("Suite");

    private String type;

    CruiseCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String classType) {
        this.type = classType;
    }
}
