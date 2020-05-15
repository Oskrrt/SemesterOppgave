package com.sample.Models.ComputerComponents;

import com.sample.Exceptions.ValidationException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Mouse extends ComputerComponent {
    private transient SimpleBooleanProperty wireless;
    private transient SimpleStringProperty isWireLess;

    public Mouse(double price, String description, String productName, String productionCompany, String serialNumber, boolean wireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.wireless = new SimpleBooleanProperty(wireless);
        setIsWirelessFromForm();
    }

    //For a more user-friendly tableview. Yes/No instead of true/false.
    public void setIsWirelessFromForm() {
        if(wireless.get()){
            isWireLess = new SimpleStringProperty("Yes");
        } else {
            isWireLess = new SimpleStringProperty("No");
        }
    }

    public String toString() {
        if (this.getDescription() == null){
            super.setDescription("Not selected");
            super.setPrice(0);
            super.setProductionCompany("Not selected");
            super.setProductName("Not selected");
            super.setSerialNumber("Not selected");
            setIsWireLess("Not selected");
        }
        return super.toString()+isWireLess.getValueSafe();
    }

    public String getIsWireless(){
        return isWireLess.getValue();
    }

    public void setWireless(boolean wireless) {
        this.wireless.set(wireless);
    }

    public void setIsWireLess(String isWireLess) {
        this.isWireLess.set(isWireLess);
    }


    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(isWireLess.getValue());
    }


    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String isWireless = s.readUTF();

        this.isWireLess = new SimpleStringProperty(isWireless);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();
        String validateWireless = "Yes|No|Not selected";
        if (Pattern.matches(validateWireless.toLowerCase(), getIsWireless().toLowerCase())){
            return true;

        } else throw new ValidationException("Wireless has to either be yes or no.");
    }
}
