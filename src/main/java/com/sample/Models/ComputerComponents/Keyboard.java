package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class Keyboard extends ComputerComponent {
    private String language; //e.g American, French, Norwegian...
    boolean wireless;

    private final String validateLanguage = "Norwegian|English"; //an API with all keyboard language would be nice

    public Keyboard(double price, String description, String productName, String productionCompany, String serialNumber, String language, boolean wireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.language = language;
        this.wireless = wireless;
    }

    public String getLanguage() {
        return language;
    }

    //For a more user-friendly tableview. Yes/No instead of true/false.
    public String getIsWireless() {
        String isWireless;
        if (wireless){
            isWireless = "Yes";
        } else {
            isWireless = "No";
        }
        return isWireless;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateLanguage, getLanguage())){
            return true;
        } else throw new ValidationException("Only Norwegian and English keyboards are supported currently");
    }
}
