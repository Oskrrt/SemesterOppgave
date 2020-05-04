package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class StorageComponent extends ComputerComponent {
    private transient SimpleStringProperty size; //e.g 500 GB;

    public StorageComponent(double price, String description, String productName, String productionCompany, String serialNumber, String size) {
        super(price, description, productName, productionCompany, serialNumber);
        this.size = new SimpleStringProperty(size);
    }

    public String getSize() {
        return size.get();
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(size.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String size = s.readUTF();
        this.size = new SimpleStringProperty(size);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        String validateSize = "[0-9]{2,4}";
        if(Pattern.matches(validateSize, getSize())){
            return true;
        } else throw new ValidationException("Invalid size. must a number with 2-4 digits");
    }
}
