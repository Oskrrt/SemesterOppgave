package com.sample.Models.ComputerComponents;

public class Mouse extends ComputerComponent {
    private boolean isWireless;

    public Mouse(double price, String description, String productName, String productionCompany, String serialNumber, boolean isWireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.isWireless = isWireless;
    }

    public boolean isWireless() {
        return isWireless;
    }
}
