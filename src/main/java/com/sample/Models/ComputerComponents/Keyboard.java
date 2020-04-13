package com.sample.Models.ComputerComponents;

public class Keyboard extends ComputerComponent {
    private String language; //e.g American, French, Norwegian...
    boolean wireless;

    public Keyboard(double price, String description, String productName, String productionCompany, String serialNumber, String language, boolean wireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.language = language;
        this.wireless = wireless;
    }

    public String getLanguage() {
        return language;
    }

    //For a more user-friendly tableview. Yes/No instead of true/false.
    public String getIsWireless() {
        String isWireless;
        if (wireless){
            isWireless = "Yes";
        } else {
            isWireless = "No";
        }
        return isWireless;
    }
}
