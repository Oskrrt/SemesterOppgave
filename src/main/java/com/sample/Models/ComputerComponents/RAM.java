package com.sample.Models.ComputerComponents;

import com.sample.Exceptions.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class RAM extends ComputerComponent{
    private transient SimpleStringProperty gigabytes;
    private transient SimpleStringProperty MHz;

    public RAM(double price, String description, String productName, String productionCompany, String serialNumber, String gigabytes, String MHz) {
        super(price, description, productName, productionCompany, serialNumber);
        this.gigabytes = new SimpleStringProperty(gigabytes);
        this.MHz = new SimpleStringProperty(MHz);
    }

    public String getGigabytes() {
        return gigabytes.get();
    }

    public String getMHz() {
        return MHz.get();
    }

    public void setGigabytes(String gigabytes) {
        this.gigabytes.set(gigabytes);
    }

    public void setMHz(String MHz) {
        this.MHz.set(MHz);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeUTF(gigabytes.getValue());
        s.writeUTF(MHz.getValue());

    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String gb = s.readUTF();
        String mhz = s.readUTF();

        this.gigabytes = new SimpleStringProperty(gb);
        this.MHz = new SimpleStringProperty(mhz);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        String validateGigabytes = "1|2|4|8|16|32|64|128|256|512";
        if (Pattern.matches(validateGigabytes, getGigabytes())){
            String validateMHz = "[0-9]{3,5}";
            if (Pattern.matches(validateMHz, getMHz())){
                return true;
            } else throw new ValidationException("Invalid MHz");
        } else throw new ValidationException("Invalid gigabytes");
    }
}
