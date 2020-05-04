package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class CoolingSystem extends ComputerComponent implements ValidateForm {
    private transient SimpleStringProperty widthCM;
    private transient SimpleStringProperty heightCM;


    public CoolingSystem(String widthCM, String heightCM, double price, String description, String productName, String productionCompany, String serialNumber) {
        super(price, description, productName, productionCompany, serialNumber);
        this.widthCM = new SimpleStringProperty(widthCM);
        this.heightCM = new SimpleStringProperty(heightCM);
    }

    public String getWidthCM() {
        return widthCM.get();
    }

    public String getHeightCM() {
        return heightCM.get();
    }

    public void setHeightCM(String heightCM) {
        this.heightCM.set(heightCM);
    }

    public void setWidthCM(String widthCM){
        this.widthCM.set(widthCM);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(widthCM.getValue());
        s.writeUTF(heightCM.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String width = s.readUTF();
        String height = s.readUTF();

        this.widthCM = new SimpleStringProperty(width);
        this.heightCM = new SimpleStringProperty(height);
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();
        //0-9 that doesnt have to have decimals, but can.
        String validateWidth = "[0-9]*(\\.[0-9]{0,2})?$";
        if (Pattern.matches(validateWidth, getWidthCM())){
            //0-9 that doesnt have to have decimals, but can.
            String validateHeight = "[0-9]*(\\.[0-9]{0,2})?$";
            if (Pattern.matches(validateHeight, getHeightCM())){
                return true;
            } else throw new ValidationException("Invalid height");
        } else throw new ValidationException("Invalid width");
    }


}
