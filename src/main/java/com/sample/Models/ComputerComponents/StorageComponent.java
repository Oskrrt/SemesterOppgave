package com.sample.Models.ComputerComponents;

public class StorageComponent extends ComputerComponent {
    public String size; //e.g 500 GB;

    public StorageComponent(double price, String description, String productName, String productionCompany, String serialNumber, String size) {
        super(price, description, productName, productionCompany, serialNumber);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
