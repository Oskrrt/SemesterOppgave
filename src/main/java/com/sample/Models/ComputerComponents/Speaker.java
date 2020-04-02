package com.sample.Models.ComputerComponents;

public class Speaker extends ComputerComponent{
    private String inputType; //e.g AUX, USB-C, USB-A.

    public Speaker(double price, String description, String productName, String productionCompany, String serialNumber, String inputType) {
        super(price, description, productName, productionCompany, serialNumber);
        this.inputType = inputType;
    }
}
