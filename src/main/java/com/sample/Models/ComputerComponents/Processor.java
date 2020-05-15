package com.sample.Models.ComputerComponents;

import com.sample.Exceptions.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Processor extends ComputerComponent {
    private transient SimpleStringProperty coreCount; //e.g 6 cores
    private transient SimpleStringProperty threadCount; //e.g 12 Threads
    private transient SimpleStringProperty maxFrequency; //e.g 4.2Hz

    public Processor(double price, String description, String productName, String productionCompany, String serialNumber, String coreCount, String threadCount, String maxFrequency) {
        super(price, description, productName, productionCompany, serialNumber);
        this.coreCount = new SimpleStringProperty(coreCount);
        this.threadCount = new SimpleStringProperty(threadCount);
        this.maxFrequency = new SimpleStringProperty(maxFrequency);
    }

    @Override
    public String toString() {
        return super.toString()+coreCount.getValueSafe()+";"+threadCount.getValueSafe()+";"+maxFrequency.getValueSafe();
    }

    public String getCoreCount() {
        return coreCount.get();
    }

    public String getThreadCount() {
        return threadCount.get();
    }

    public String getMaxFrequency() {
        return maxFrequency.get();
    }

    public void set(String coreCount) {
        this.coreCount.set(coreCount);
    }

    public void setThreadCount(String threadCount) {
        this.threadCount.set(threadCount);
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency.set(maxFrequency);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeUTF(coreCount.getValue());
        s.writeUTF(threadCount.getValue());
        s.writeUTF(maxFrequency.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String coreCount = s.readUTF();
        String threadCount = s.readUTF();
        String maxFreq = s.readUTF();

        this.coreCount = new SimpleStringProperty(coreCount);
        this.threadCount = new SimpleStringProperty(threadCount);
        this.maxFrequency = new SimpleStringProperty(maxFreq);

    }
    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        String validateCoreCount = "[0-9]{1,2}";
        if(Pattern.matches(validateCoreCount, getCoreCount())){
            String validateThreadCount = "[0-9][1,2]";
            if (Pattern.matches(validateThreadCount, getThreadCount())){
                String validateMaxFrequency = "[0-9]+(\\.[0-9]{0,1})?$";
                if (Pattern.matches(validateMaxFrequency, getMaxFrequency())){
                    return true;
                } else throw new ValidationException("Invalid max frequency");
            } else throw new ValidationException("Invalid thread count");
        } else throw new ValidationException("Invalid core count");
    }
}
