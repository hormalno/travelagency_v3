package com.travelagency_v2.travelagency_v2.models.bindingModels;


import com.travelagency_v2.travelagency_v2.entities.enums.CruiseCategory;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class CruiseDto {

    private long id;

    @Pattern(regexp = "[A-z\\s]+",message = "Invalid name")
    private String name;

    @NotNull(message = "Invalid price")
    @Range(min=1,message = "Price must be minimum 1 $")
    private double price;

    @Size(max= 300, message = "Description must be less than 300 symbols")
    private String description;

    @NotBlank(message = "Invalid information")
    private String information;

    private String originName;

    private Set<String> destinationNames;

    private CruiseCategory cruiseCategory;

    @NotBlank(message = "Invalid cruiseline name")
    private String cruiseLine;

    @Range(min = 1)
    private int duration;

    @NotBlank(message = "Invalid ship name")
    private String shipName;

    public CruiseDto() {
        this.destinationNames = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Set<String> getDestinationNames() {
        return destinationNames;
    }

    public void setDestinationNames(Set<String> destinationNames) {
        this.destinationNames = destinationNames;
    }

    public CruiseCategory getCruiseCategory() {
        return cruiseCategory;
    }

    public void setCruiseCategory(CruiseCategory cruiseCategory) {
        this.cruiseCategory = cruiseCategory;
    }

    public String getCruiseLine() {
        return cruiseLine;
    }

    public void setCruiseLine(String cruiseLine) {
        this.cruiseLine = cruiseLine;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
