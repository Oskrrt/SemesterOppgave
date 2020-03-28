package com.sample.Models.ComputerComponents;

import java.util.ArrayList;

public class Case extends ComputerComponent {
    public String numberOfUSBPorts; //e.g 4
    public String HDAudioJacks;//e.g 1
    public int widthCM;
    public int heightCM;
    public ArrayList<Fan> compatibleFans;

    public Case(String numberOfUSBPorts, String HDAudioJacks, int widthCM, int heightCM, ArrayList<Fan> compatibleFans, double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
        this.numberOfUSBPorts = numberOfUSBPorts;
        this.HDAudioJacks = HDAudioJacks;
        this.widthCM = widthCM;
        this.heightCM = heightCM;
        this.compatibleFans = compatibleFans;
    }


    public String getNumberOfUSBPorts() {
        return numberOfUSBPorts;
    }

    public String getHDAudioJacks() {
        return HDAudioJacks;
    }

    public int getWidthCM() {
        return widthCM;
    }

    public int getHeightCM() {
        return heightCM;
    }

    public ArrayList<Fan> getCompatibleFans() {
        return compatibleFans;
    }
}
