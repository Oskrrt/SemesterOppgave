package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Case extends ComputerComponent implements ValidateForm {
    private transient SimpleStringProperty numberOfUSBPorts; //e.g 4
    private transient SimpleStringProperty HDAudioJacks;//e.g 1
    private transient SimpleStringProperty widthCM;
    private transient SimpleStringProperty heightCM;
    private transient SimpleStringProperty depthCM;

    public Case(String numberOfUSBPorts, String HDAudioJacks, String widthCM, String heightCM, String depthCM, double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
        this.numberOfUSBPorts = new SimpleStringProperty(numberOfUSBPorts);
        this.HDAudioJacks = new SimpleStringProperty(HDAudioJacks);
        this.widthCM = new SimpleStringProperty(widthCM);
        this.heightCM = new SimpleStringProperty(heightCM);
        this.depthCM = new SimpleStringProperty(depthCM);
    }


    public String getNumberOfUSBPorts() {
        return numberOfUSBPorts.get();
    }

    public String getHDAudioJacks() {
        return HDAudioJacks.get();
    }

    public String getWidthCM() {
        return widthCM.get();
    }

    public String getHeightCM() {
        return heightCM.get();
    }

    public String getDepthCM() {return depthCM.get();}

    public void setNumberOfUSBPorts(String numberOfUSBPorts) { this.numberOfUSBPorts.set(numberOfUSBPorts);}

    public void setHDAudioJacks(String HDAudioJacks) {
        this.HDAudioJacks.set(HDAudioJacks);
    }

    public void setWidthCM(String widthCM) {
        this.widthCM.set(widthCM);
    }

    public void setHeightCM(String heightCM) {
        this.heightCM.set(heightCM);
    }

    public void setDepthCM(String depthCM) {
        this.depthCM.set(depthCM);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(numberOfUSBPorts.getValue());
        s.writeUTF(HDAudioJacks.getValue());
        s.writeUTF(widthCM.getValue());
        s.writeUTF(heightCM.getValue());
        s.writeUTF(depthCM.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException, ValidationException {
        String numberOfUSBPorts = s.readUTF();
        String HDAudioJacks = s.readUTF();
        String widthCM = s.readUTF();
        String heightCM = s.readUTF();
        String depthCM = s.readUTF();

        this.numberOfUSBPorts = new SimpleStringProperty(numberOfUSBPorts);
        this.HDAudioJacks = new SimpleStringProperty(HDAudioJacks);
        this.widthCM = new SimpleStringProperty(widthCM);
        this.heightCM = new SimpleStringProperty(heightCM);
        this.depthCM = new SimpleStringProperty(depthCM);
        validate();
    }
    @Override
    public boolean validate() throws ValidationException {
        super.validate();
        String validatenumberOfUSBPorts = "[0-9]|10";
        if(Pattern.matches(validatenumberOfUSBPorts, getNumberOfUSBPorts())){
            String validateHDAudioJacks = "[0-9]|10";
            if(Pattern.matches(validateHDAudioJacks, getHDAudioJacks())){
                //0-9 that doesnt have to have decimals, but can.
                String validatewidthCM = "[0-9]+(\\.[0-9]{0,2})?$";
                if(Pattern.matches(validatewidthCM, getWidthCM())){
                    //same as width
                    String validateheightCM = "[0-9]+(\\.[0-9]{0,2})?$";
                    if(Pattern.matches(validateheightCM, getHeightCM())){
                        //same as width
                        String validatedepthCM = "[0-9]+(\\.[0-9]{0,2})?$";
                        if(Pattern.matches(validatedepthCM, getDepthCM())){
                            return true;
                        } else throw new ValidationException("Invalid depth");
                    } else throw new ValidationException("Invalid height");
                }else throw new ValidationException("Invalid width");
            } else throw new ValidationException("Invalid number of HD audio jacks. Must be between 0-10");
        } else throw new ValidationException("Invalid number of USB ports. Must be between 0-10");
    }
}
