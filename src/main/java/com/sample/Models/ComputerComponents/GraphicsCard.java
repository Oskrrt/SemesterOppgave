package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;
import com.sample.DAL.SaveFile.SaveJobj;

import java.util.regex.Pattern;

public class GraphicsCard extends ComputerComponent implements ValidateForm {
    private String memoryCapacity; // e.g 16 GB
    private String memoryType;// e.g GDDR6 SDRAM

    private final String validateMemoryCapacity = "1|2|4|8|16|32|64|128|256|512";
    private final String validateMemoryType = "[\\w]{3,30}";

    public GraphicsCard(double price, String description, String productName, String productionCompany, String serialNumber, String memoryCapacity, String memoryType) {
        super(price, description, productName, productionCompany, serialNumber);
        this.memoryCapacity = memoryCapacity;
        this.memoryType = memoryType;
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public String toString() {
        return super.toString()+"\n"+this.memoryCapacity+"\n"+this.memoryType;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if (Pattern.matches(validateMemoryCapacity, getMemoryCapacity())){
            if (Pattern.matches(validateMemoryType, getMemoryType())){
                return true;
            } else throw new ValidationException("Invalid memory type");
        }else throw new ValidationException("Invalid memory capacity");
    }
}
