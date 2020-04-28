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

    private final String validateWidth = "[0-9]*(\\.[0-9]{0,2})?$"; //0-9 that doesnt have to have decimals, but can.
    private final String validateHeight = "[0-9]*(\\.[0-9]{0,2})?$"; //0-9 that doesnt have to have decimals, but can.


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

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        super.write(s);
        s.writeUTF(widthCM.getValue());
        s.writeUTF(heightCM.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        super.read(s);
        String width = s.readUTF();
        String height = s.readUTF();

        this.widthCM = new SimpleStringProperty(width);
        this.heightCM = new SimpleStringProperty(height);
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();
        if (Pattern.matches(validateWidth, getWidthCM())){
            if (Pattern.matches(validateHeight, getHeightCM())){
                return true;
            } else throw new ValidationException("Invalid height");
        } else throw new ValidationException("Invalid width");
    }
}
