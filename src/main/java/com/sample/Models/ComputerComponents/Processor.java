package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class Processor extends ComputerComponent {
    private String coreCount; //e.g 6 cores
    private String threadCount; //e.g 12 Threads
    private String maxFrequency; //e.g 4.2Hz

    private final String validateCoreCount = "[0-9]{1,2}";
    private final String validateThreadCount = "[0-9][1,2]";
    private final String validateMaxFrequency = "[0-9]*(\\.[0-9]{0,1})?$";

    public Processor(double price, String description, String productName, String productionCompany, String serialNumber, String coreCount, String threadCount, String maxFrequency) {
        super(price, description, productName, productionCompany, serialNumber);
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.maxFrequency = maxFrequency;
    }

    public String getCoreCount() {
        return coreCount;
    }

    public String getThreadCount() {
        return threadCount;
    }

    public String getMaxFrequency() {
        return maxFrequency;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateCoreCount, getCoreCount())){
            if (Pattern.matches(validateThreadCount, getThreadCount())){
                if (Pattern.matches(validateMaxFrequency, getMaxFrequency())){
                    return true;
                } else throw new ValidationException("Invalid max frequency");
            } else throw new ValidationException("Invalid thread count");
        } else throw new ValidationException("Invalid core count");
    }
}
