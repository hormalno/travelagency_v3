package com.travelagency_v2.travelagency_v2.entities.enums;


public enum Facility {
    WIFI("Wi-Fi"), FITNESS("Fitness"), RESTAURANT("Restaurant"), CAR_PARK("Car Park"), RECEPTION("24h Reception"),
    ROOM_SERVICE("Room Service"), CONFERENCE_ROOMS("Conference Rooms"), AIR_CONDITIONING("Air Conditioning");

    private String type;

    Facility(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
