package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;
import com.sample.DAL.SaveFile.SaveJobj;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class GraphicsCard extends ComputerComponent implements ValidateForm {
    private transient SimpleStringProperty memoryCapacity; // e.g 16 GB
    private transient SimpleStringProperty memoryType;// e.g GDDR6 SDRAM

    public GraphicsCard(double price, String description, String productName, String productionCompany, String serialNumber, String memoryCapacity, String memoryType) {
        super(price, description, productName, productionCompany, serialNumber);
        this.memoryCapacity = new SimpleStringProperty(memoryCapacity);
        this.memoryType = new SimpleStringProperty(memoryType);
    }

    public String getMemoryCapacity() {
        return memoryCapacity.get();
    }

    public String getMemoryType() {
        return memoryType.get();
    }

    public String toString() {
        return super.toString()+"\n"+this.memoryCapacity+"\n"+this.memoryType;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeUTF(memoryCapacity.getValue());
        s.writeUTF(memoryType.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String memoryCap = s.readUTF();
        String memoryType = s.readUTF();

        this.memoryCapacity = new SimpleStringProperty(memoryCap);
        this.memoryType = new SimpleStringProperty(memoryType);
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        String validateMemoryCapacity = "1|2|4|8|16|32|64|128|256|512";
        if (Pattern.matches(validateMemoryCapacity, getMemoryCapacity())){
            String validateMemoryType = "[\\w]{3,30}";
            if (Pattern.matches(validateMemoryType, getMemoryType())){
                return true;
            } else throw new ValidationException("Invalid memory type");
        }else throw new ValidationException("Invalid memory capacity");
    }
}
