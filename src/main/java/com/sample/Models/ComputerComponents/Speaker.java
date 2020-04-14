package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class Speaker extends ComputerComponent{
    private String inputType; //e.g AUX, USB-C, USB-A.

    private final String validateInputType = "AUX|USB-C|USB-B|USB-A|Bluetooth|";


    public Speaker(double price, String description, String productName, String productionCompany, String serialNumber, String inputType) {
        super(price, description, productName, productionCompany, serialNumber);
        this.inputType = inputType;
    }

    public String getInputType(){
        return inputType;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateInputType, getInputType())){
            return true;
        } else throw new ValidationException("Only AUX/USB-C/USB-B/USB-A and Bluetooth are currently allowed");
    }

}
