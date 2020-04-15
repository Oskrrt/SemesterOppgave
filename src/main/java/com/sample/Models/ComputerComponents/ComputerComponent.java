package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;

import java.io.Serializable;
import java.util.regex.Pattern;

public class ComputerComponent  implements Serializable, ValidateForm {
    private static final long serialVersionUID = 3;
    private double price;
    private String description;
    private String productName;
    private String productionCompany;
    private String serialNumber;

    private final String validatePrice = "[0-9]+(\\.[0-9][0-9]?)?"; //any double with two decimal points
    private final String validateDescription = "[\\w ._,:;-]{5,500}"; //any character. Between 5-300 characters
    private final String validateName = "[\\w ]{2,50}"; //any word + spaces and  between 2-50 characters. Also allows numbers.
    private final String validateProductionCompany = "[\\w -]{2,30}"; //any word + spaces betwwen 2-30 characters. Allows numbers
    private final String validateSerialnumber = "[0-9]{8}"; //8 numbers.

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

    public String toString() {
        return this.price+"\n"+ this.description+"\n"+this.productName+"\n"+this.productionCompany+"\n"+this.serialNumber;
    }

    @Override
    public boolean validate() throws ValidationException {
        if(Pattern.matches(validatePrice, String.valueOf(getPrice()))){
            if (Pattern.matches(validateDescription, getDescription())){
                if(Pattern.matches(validateName, getProductName())){
                    if(Pattern.matches(validateProductionCompany, getProductionCompany())){
                        if(Pattern.matches(validateSerialnumber, getSerialNumber())){
                            return true;
                        } else throw new ValidationException("Serial number must be 8 numbers.");
                    } else throw new ValidationException("Invalid manufacturer. Make sure it also is between 2 and 30 characters long.");
                } else throw new ValidationException("Invalid product name. Make sure it also is between 2 and 50 characters long.");
            } else throw new ValidationException("Invalid description. Make sure it is between 5 and 500 characters long.");
        } else throw new ValidationException("Invalid price. Make sure you include decimals: X.XX");
    }
}
