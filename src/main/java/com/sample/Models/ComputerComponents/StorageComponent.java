package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class StorageComponent extends ComputerComponent {
    private String size; //e.g 500 GB;

    private final String validateSize = "[0-9]{2,4}";
    public StorageComponent(double price, String description, String productName, String productionCompany, String serialNumber, String size) {
        super(price, description, productName, productionCompany, serialNumber);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateSize, getSize())){
            return true;
        } else throw new ValidationException("Invalid size. must a number with 2-4 digits");
    }
}
