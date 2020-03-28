package com.sample.Models.ComputerComponents;

public class PowerSupply extends ComputerComponent {
    private String powerSource;//e.g corded-electric
    private String voltage; //e.g 230 volts
    private String watts; //e.g 600 watts

    public PowerSupply(double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
    }

    public String getPowerSource() {
        return powerSource;
    }

    public String getVoltage() {
        return voltage;
    }

    public String getWatts() {
        return watts;
    }
}
