package com.sample.Models.ComputerComponents;

import com.sample.Exceptions.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Speaker extends ComputerComponent{
    private transient SimpleStringProperty inputType; //e.g AUX, USB-C, USB-A.


    public Speaker(double price, String description, String productName, String productionCompany, String serialNumber, String inputType) {
        super(price, description, productName, productionCompany, serialNumber);
        this.inputType = new SimpleStringProperty(inputType);
    }

    public String getInputType(){
        return inputType.get();
    }

    public void setInputType(String inputType) {
        this.inputType.set(inputType);
    }

    public String toString() {
        return super.toString()+"\n"+inputType;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(inputType.getValue());

    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String input = s.readUTF();
        this.inputType = new SimpleStringProperty(input);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        String validateInputType = "AUX|USB-C|USB-B|USB-A|Bluetooth|";
        if(Pattern.matches(validateInputType, getInputType())){
            return true;
        } else throw new ValidationException("Only AUX/USB-C/USB-B/USB-A and Bluetooth are currently allowed");
    }

}
