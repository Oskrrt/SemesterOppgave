package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

public class ComputerComponent  implements Serializable, ValidateForm {
    private static final long serialVersionUID = 3;
    private SimpleDoubleProperty price;
    private SimpleStringProperty description;
    private SimpleStringProperty productName;
    private SimpleStringProperty productionCompany;
    private SimpleStringProperty serialNumber;

    private final String validatePrice = "[0-9]+(\\.[0-9][0-9]?)?"; //any double with two decimal points
    private final String validateDescription = "[\\w ,;.:\\-_´``?=)(/&%$#\"'!'@*¨^ÆØÅæøå \\s]{5,500}"; //any character. Between 5-300 characters
    private final String validateName = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+"; //any word + spaces and  between 2-50 characters. Also allows numbers and special characters.
    private final String validateProductionCompany = "[\\w -]{2,30}"; //any word + spaces betwwen 2-30 characters. Allows numbers
    private final String validateSerialnumber = "[0-9]{8}"; //8 numbers.

    public ComputerComponent(double price, String description, String productName, String productionCompany, String serialNumber) {
        this.price = new SimpleDoubleProperty(price);
        this.description = new SimpleStringProperty(description);
        this.productName = new SimpleStringProperty(productName);
        this.productionCompany = new SimpleStringProperty(productionCompany);
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }

    public double getPrice() {
        return price.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getProductName() {
        return productName.get();
    }

    public String getProductionCompany() {
        return productionCompany.get();
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public String toString() {
        return this.price+"\n"+ this.description+"\n"+this.productName+"\n"+this.productionCompany+"\n"+this.serialNumber;
    }

    public void setPrice(double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public void setProductName(String productName) {
        this.productName = new SimpleStringProperty(productName);
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = new SimpleStringProperty(productionCompany);
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeDouble(price.getValue());
        s.writeUTF(description.getValue());
        s.writeUTF(productName.getValue());
        s.writeUTF(productionCompany.getValue());
        s.writeUTF(serialNumber.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        double price = s.readDouble();
        String description = s.readUTF();
        String productName = s.readUTF();
        String productionCompany = s.readUTF();
        String serialNumber = s.readUTF();

        this.price = new SimpleDoubleProperty(price);
        this.description = new SimpleStringProperty(description);
        this.productName = new SimpleStringProperty(productName);
        this.productionCompany = new SimpleStringProperty(productionCompany);
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }

    /*
    these two methods make it possible to read and write from the subclasses. readObject and writeObject MUST be private, not package private, public etc
     */
    void write(ObjectOutputStream s) throws IOException {
        writeObject(s);
    }

    void read(ObjectInputStream s) throws IOException, ClassNotFoundException {
        readObject(s);
    }
    @Override
    public boolean validate() throws ValidationException {
        if(Pattern.matches(validatePrice, String.valueOf(getPrice())) && getPrice() > 0){
            if (Pattern.matches(validateDescription, getDescription())){
                if(Pattern.matches(validateName, getProductName())){
                    if(Pattern.matches(validateProductionCompany, getProductionCompany())){
                        if(Pattern.matches(validateSerialnumber, getSerialNumber())){
                            return true;
                        } else throw new ValidationException("Serial number must be 8 numbers.");
                    } else throw new ValidationException("Invalid manufacturer. Make sure it also is between 2 and 30 characters long.");
                } else throw new ValidationException("Invalid product name. Make sure it also is between 2 and 50 characters long.");
            } else throw new ValidationException("Invalid description. Make sure it is between 5 and 500 characters long.");
        } else throw new ValidationException("Invalid price. Make sure you include decimals: X.XX");
    }
}
