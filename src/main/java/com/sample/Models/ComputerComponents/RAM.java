package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class RAM extends ComputerComponent{
    private String gigabytes;
    private String MHz;

    private final String validateGigabytes  = "1|2|4|8|16|32|64|128|256|512";
    private final String validateMHz = "[0-9]{3,5}";

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

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if (Pattern.matches(validateGigabytes, getGigabytes())){
            if (Pattern.matches(validateMHz, getMHz())){
                return true;
            } else throw new ValidationException("Invalid MHz");
        } else throw new ValidationException("Invalid gigabytes");
    }
}
