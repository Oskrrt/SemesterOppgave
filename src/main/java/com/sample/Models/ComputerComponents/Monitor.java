package com.sample.Models.ComputerComponents;

public class Monitor extends ComputerComponent {
    private String displayType; //e.g LCD, OLED...
    private String inches; //e.g 16"
    private String resolution; //e.g 4k
    private String connector; //e.g HDMI, USB-C...

    public Monitor(double price, String description, String productName, String productionCompany, String serialNumber, String displayType, String inches, String resolution, String connector) {
        super(price, description, productName, productionCompany, serialNumber);
        this.displayType = displayType;
        this.inches = inches;
        this.resolution = resolution;
        this.connector = connector;
    }

    public String getDisplayType() {
        return displayType;
    }

    public String getInches() {
        return inches;
    }

    public String getResolution() {
        return resolution;
    }

    public String getConnector() {
        return connector;
    }
}
