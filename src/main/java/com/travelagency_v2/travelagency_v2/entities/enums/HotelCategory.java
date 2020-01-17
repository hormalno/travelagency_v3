package com.travelagency_v2.travelagency_v2.entities.enums;


public enum HotelCategory {

    LUXURY("Luxury"), CASINO("Casino"), RESORT("Resort"), SPA("Spa");

    private String type;

    HotelCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
