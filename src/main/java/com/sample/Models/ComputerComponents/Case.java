package com.sample.Models.ComputerComponents;

import java.util.ArrayList;

public class Case extends ComputerComponent {
    public String numberOfUSBPorts; //e.g 4
    public String HDAudioJacks;//e.g 1
    public String widthCM;
    public String heightCM;

    public Case(String numberOfUSBPorts, String HDAudioJacks, String widthCM, String heightCM, double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
        this.numberOfUSBPorts = numberOfUSBPorts;
        this.HDAudioJacks = HDAudioJacks;
        this.widthCM = widthCM;
        this.heightCM = heightCM;
    }


    public String getNumberOfUSBPorts() {
        return numberOfUSBPorts;
    }

    public String getHDAudioJacks() {
        return HDAudioJacks;
    }

    public String getWidthCM() {
        return widthCM;
    }

    public String getHeightCM() {
        return heightCM;
    }
}
