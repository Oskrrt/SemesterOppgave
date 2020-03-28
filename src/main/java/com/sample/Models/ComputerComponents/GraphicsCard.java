package com.sample.Models.ComputerComponents;

public class GraphicsCard extends ComputerComponent {
    private String memoryCapacity; // e.g 16 GB
    private String memoryType;// e.g GDDR6 SDRAM

    public GraphicsCard(double price, String description, String productName, String productionCompany, String serialNumber, String memoryCapacity, String memoryType) {
        super(price, description, productName, productionCompany, serialNumber);
        this.memoryCapacity = memoryCapacity;
        this.memoryType = memoryType;
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public String getMemoryType() {
        return memoryType;
    }
}
