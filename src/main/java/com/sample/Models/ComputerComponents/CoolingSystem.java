package com.sample.Models.ComputerComponents;

public class CoolingSystem extends ComputerComponent {
    private String widthCM;
    private String heightCM;

    public CoolingSystem(String widthCM, String heightCM, double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
        this.widthCM = widthCM;
        this.heightCM = heightCM;
    }

    public String getWidthCM() {
        return widthCM;
    }

    public String getHeightCM() {
        return heightCM;
    }
}
