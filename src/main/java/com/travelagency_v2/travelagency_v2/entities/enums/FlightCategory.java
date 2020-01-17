package com.travelagency_v2.travelagency_v2.entities.enums;

public enum FlightCategory {

    ECONOMY_CLASS("Economy Class"), ECONOMY_PREMIUM_CLASS("Economy Premium Class"), FIRST_CLASS("First Class"), BUSINESS_CLASS("Business Class");

    private String type;

    FlightCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
