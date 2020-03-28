package com.sample.Models.ComputerComponents;

public class Keyboard extends ComputerComponent {
    private String language; //e.g American, French, Norwegian...
    boolean isWireless;

    public Keyboard(double price, String description, String productName, String productionCompany, String serialNumber, String language, boolean isWireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.language = language;
        this.isWireless = isWireless;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isWireless() {
        return isWireless;
    }
}
