package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Mouse extends ComputerComponent {
    private transient SimpleBooleanProperty wireless;
    private transient SimpleStringProperty isWireLess;

    public Mouse(double price, String description, String productName, String productionCompany, String serialNumber, boolean wireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.wireless = new SimpleBooleanProperty(wireless);
        setIsWireless();
    }

    //For a more user-friendly tableview. Yes/No instead of true/false.
    public void setIsWireless() {
        if(wireless.get()){
            isWireLess = new SimpleStringProperty("Yes");
        } else {
            isWireLess = new SimpleStringProperty("No");
        }
    }

    public String getIsWireless(){
        return isWireLess.getValue();
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
        return true;
    }
}
