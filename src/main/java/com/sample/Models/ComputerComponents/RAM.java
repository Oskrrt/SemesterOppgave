package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class RAM extends ComputerComponent{
    private transient SimpleStringProperty gigabytes;
    private transient SimpleStringProperty MHz;

    private final String validateGigabytes  = "1|2|4|8|16|32|64|128|256|512";
    private final String validateMHz = "[0-9]{3,5}";

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

    private void writeObject(ObjectOutputStream s) throws IOException {
        super.write(s);
        s.defaultWriteObject();

        s.writeUTF(gigabytes.getValue());
        s.writeUTF(MHz.getValue());

    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        super.read(s);
        String gb = s.readUTF();
        String mhz = s.readUTF();

        this.gigabytes = new SimpleStringProperty(gb);
        this.MHz = new SimpleStringProperty(mhz);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if (Pattern.matches(validateGigabytes, getGigabytes())){
            if (Pattern.matches(validateMHz, getMHz())){
                return true;
            } else throw new ValidationException("Invalid MHz");
        } else throw new ValidationException("Invalid gigabytes");
    }
}
