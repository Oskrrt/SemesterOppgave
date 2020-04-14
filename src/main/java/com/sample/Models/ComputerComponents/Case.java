package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class Case extends ComputerComponent implements ValidateForm {
    private String numberOfUSBPorts; //e.g 4
    private String HDAudioJacks;//e.g 1
    private String widthCM;
    private String heightCM;
    private String depthCM;

    private final String validatenumberOfUSBPorts = "[0-1]?[0-9]|10";
    private final String validateHDAudioJacks = "[0-1]?[0-9]|10";
    private final String validatewidthCM = "[0-9]*(\\.[0-9]{0,2})?$"; //0-9 that doesnt have to have decimals, but can.
    private final String validateheightCM = "[0-9]*(\\.[0-9]{0,2})?$"; //same as width
    private final String validatedepthCM = "[0-9]*(\\.[0-9]{0,2})?$"; //same as width

    public Case(String numberOfUSBPorts, String HDAudioJacks, String widthCM, String heightCM, String depthCM, double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
        this.numberOfUSBPorts = numberOfUSBPorts;
        this.HDAudioJacks = HDAudioJacks;
        this.widthCM = widthCM;
        this.heightCM = heightCM;
        this.depthCM = depthCM;
    }


    public String getNumberOfUSBPorts() {
        return numberOfUSBPorts;
    }

    public String getHDAudioJacks() {
        return HDAudioJacks;
    }

    public String getWidthCM() {
        return widthCM;
    }

    public String getHeightCM() {
        return heightCM;
    }

    public String getDepthCM() {return depthCM;}

    @Override
    public boolean validate() throws ValidationException {
        super.validate();
        if(Pattern.matches(validatenumberOfUSBPorts, getNumberOfUSBPorts())){
            if(Pattern.matches(validateHDAudioJacks, getHDAudioJacks())){
                if(Pattern.matches(validatewidthCM, getWidthCM())){
                    if(Pattern.matches(validateheightCM, getHeightCM())){
                        if(Pattern.matches(validatedepthCM, getDepthCM())){
                            return true;
                        } else throw new ValidationException("Invalid depth");
                    } else throw new ValidationException("Invalid height");
                }else throw new ValidationException("Invalid width");
            } else throw new ValidationException("Invalid number of HD audio jacks. Must be between 0-10");
        } else throw new ValidationException("Invalid number of USB ports. Must be between 0-10");
    }
}
