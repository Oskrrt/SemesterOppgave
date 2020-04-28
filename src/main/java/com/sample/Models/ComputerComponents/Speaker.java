package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Speaker extends ComputerComponent{
    private transient SimpleStringProperty inputType; //e.g AUX, USB-C, USB-A.

    private final String validateInputType = "AUX|USB-C|USB-B|USB-A|Bluetooth|";


    public Speaker(double price, String description, String productName, String productionCompany, String serialNumber, String inputType) {
        super(price, description, productName, productionCompany, serialNumber);
        this.inputType = new SimpleStringProperty(inputType);
    }

    public String getInputType(){
        return inputType.get();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        super.write(s);

        s.defaultWriteObject();
        s.writeUTF(inputType.getValue());

    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        super.read(s);
        String input = s.readUTF();
        this.inputType = new SimpleStringProperty(input);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateInputType, getInputType())){
            return true;
        } else throw new ValidationException("Only AUX/USB-C/USB-B/USB-A and Bluetooth are currently allowed");
    }

}
