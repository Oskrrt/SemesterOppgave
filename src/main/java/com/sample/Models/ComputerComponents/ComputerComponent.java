package com.sample.Models.ComputerComponents;

public class ComputerComponent {
    private double price;
    private String description;
    private String productName;
    private String productionCompany;
    private String serialNumber;

    public ComputerComponent(double price, String description, String productName, String productionCompany, String serialNumber) {
        this.price = price;
        this.description = description;
        this.productName = productName;
        this.productionCompany = productionCompany;
        this.serialNumber = serialNumber;
    }


    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public String getSerialNumber() {
        return serialNumber;
    }


}
