package com.sample.Models.ComputerComponents;

public class Mouse extends ComputerComponent {
    private boolean wireless;

    public Mouse(double price, String description, String productName, String productionCompany, String serialNumber, boolean wireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.wireless = wireless;
    }

    //For a more user-friendly tableview. Yes/No instead of true/false.
    public String getIsWireless() {
        String isWireless;
        if(wireless){
            isWireless = "Yes";
        } else {
            isWireless = "No";
        }
        return isWireless;
    }
}
