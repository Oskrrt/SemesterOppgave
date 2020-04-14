package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class Monitor extends ComputerComponent {
    private String displayType; //e.g LCD, OLED...
    private String inches; //e.g 16"
    private String resolution; //e.g 4k
    private String connector; //e.g HDMI

    private final String validateDisplayType = "LCD|CRT|LED|OLED|Plasma";
    private final String validateInches = "[0-9]{1,2}";
    private final String validateResolution = "[\\w]{2,6}";
    private final String validateConnector = "SCART|VGA|DVI|SDI|HDMI|DisplayPort|Mini-DVI|RCA|";

    public Monitor(double price, String description, String productName, String productionCompany, String serialNumber, String displayType, String inches, String resolution, String connector) {
        super(price, description, productName, productionCompany, serialNumber);
        this.displayType = displayType;
        this.inches = inches;
        this.resolution = resolution;
        this.connector = connector;
    }

    public String getDisplayType() {
        return displayType;
    }

    public String getInches() {
        return inches;
    }

    public String getResolution() {
        return resolution;
    }

    public String getConnector() {
        return connector;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateDisplayType, getDisplayType())){
            if (Pattern.matches(validateInches, getInches())){
                if (Pattern.matches(validateResolution, getResolution())){
                    if (Pattern.matches(validateConnector, getConnector())){
                        return true;
                    } else throw new ValidationException("Only SCART/VGA/DVI/SDI/HDMI/DisplayPort/Mini-DVI and RCA are allowed");
                } else throw new ValidationException("Invalid resolution");
            } else throw new ValidationException("Invalid inches");
        } else throw new ValidationException("Only LCD/CRT/LED/OLED and Plasma are allowed");

    }
}
