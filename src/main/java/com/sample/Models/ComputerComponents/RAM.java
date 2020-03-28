package com.sample.Models.ComputerComponents;

public class RAM extends ComputerComponent{
    private String gigabytes;
    private String MHz;

    public RAM(double price, String description, String productName, String productionCompany, String serialNumber, String gigabytes, String MHz) {
        super(price, description, productName, productionCompany, serialNumber);
        this.gigabytes = gigabytes;
        this.MHz = MHz;
    }

    public String getGigabytes() {
        return gigabytes;
    }

    public String getMHz() {
        return MHz;
    }
}
