package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class CoolingSystem extends ComputerComponent implements ValidateForm {
    private String widthCM;
    private String heightCM;

    private final String validateWidth = "[0-9]*(\\.[0-9]{0,2})?$"; //0-9 that doesnt have to have decimals, but can.
    private final String validateHeight = "[0-9]*(\\.[0-9]{0,2})?$"; //0-9 that doesnt have to have decimals, but can.


    public CoolingSystem(String widthCM, String heightCM, double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
        this.widthCM = widthCM;
        this.heightCM = heightCM;
    }

    public String getWidthCM() {
        return widthCM;
    }

    public String getHeightCM() {
        return heightCM;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();
        if (Pattern.matches(validateWidth, getWidthCM())){
            if (Pattern.matches(validateHeight, getHeightCM())){
                return true;
            } else throw new ValidationException("Invalid height");
        } else throw new ValidationException("Invalid width");
    }
}
