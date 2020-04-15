package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class PowerSupply extends ComputerComponent {
    private String powerSource;//e.g corded-electric
    private String voltage; //e.g 230 volts
    private String watts; //e.g 600 watts

    private final String validatePowerSource = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,30}";
    private final String validateVoltage = "[0-9]{3,4}";
    private final String validateWatts = "[0-9]{3,4}";

    public PowerSupply(double price, String description, String productName, String productionCompany, String serialNumber, String powerSource, String voltage, String watts) {
        super(price, description, productName, productionCompany, serialNumber);
        this.powerSource = powerSource;
        this.voltage = voltage;
        this.watts = watts;
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

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if (Pattern.matches(validatePowerSource, getPowerSource())){
            if(Pattern.matches(validateVoltage, getVoltage())){
                if (Pattern.matches(validateWatts, getWatts())){
                    return true;
                } else throw new ValidationException("Invalid watts");
            } else throw new ValidationException("Invalid voltage");
        } else throw new ValidationException("Invalid power source. (Most are corded-electric)");
    }
}
