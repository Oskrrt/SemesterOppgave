package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidateForm;
import com.sample.Exceptions.ValidationException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class ComputerComponent  implements Serializable, ValidateForm {
    private static final long serialVersionUID = 3;
    private SimpleDoubleProperty price;
    private SimpleStringProperty description;
    private SimpleStringProperty productName;
    private SimpleStringProperty productionCompany;
    private SimpleStringProperty serialNumber;

    public ComputerComponent(double price, String description, String productName, String productionCompany, String serialNumber) {
        this.price = new SimpleDoubleProperty(price);
        this.description = new SimpleStringProperty(description);
        this.productName = new SimpleStringProperty(productName);
        this.productionCompany = new SimpleStringProperty(productionCompany);
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }

    public ComputerComponent(double price, String productName) {
        this.price = new SimpleDoubleProperty(price);
        this.productName = new SimpleStringProperty(productName);
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
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(this.price.getValue())+";"+ this.description.getValueSafe()+";"+this.productName.getValueSafe()+";"+this.productionCompany.getValueSafe()+";"+this.serialNumber.getValueSafe()+";";
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany.set(productionCompany);;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeDouble(price.getValue());
        s.writeUTF(description.getValue());
        s.writeUTF(productName.getValue());
        s.writeUTF(productionCompany.getValue());
        s.writeUTF(serialNumber.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException, ValidationException {
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

    void read(ObjectInputStream s) throws IOException, ClassNotFoundException, ValidationException {
        readObject(s);
    }
    @Override
    public boolean validate() throws ValidationException {
        //any double with two decimal points
        String validatePrice = "[0-9]+(\\.[0-9][0-9]?)?";
        if(Pattern.matches(validatePrice, String.valueOf(getPrice()))){
            //any character. Between 5-300 characters
            String validateDescription = "[\\w ,.\\-_´``?=)0-9(/&%$#\"'!'@*¨^ÆØÅæøå \\s]{5,500}|Not selected";
            if (Pattern.matches(validateDescription, getDescription())){
                //any word + spaces and  between 2-50 characters. Also allows numbers and special characters.
                String validateName = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]{2,100}|Not selected";
                if(Pattern.matches(validateName, getProductName())){
                    //any word + spaces betwwen 2-30 characters. Allows numbers
                    String validateProductionCompany = "[\\w -]{2,30}|Not selected";
                    if(Pattern.matches(validateProductionCompany, getProductionCompany())){
                        //8 numbers.
                        String validateSerialnumber = "[0-9]{8}|Not selected";
                        if(Pattern.matches(validateSerialnumber, getSerialNumber())){
                            return true;
                        } else throw new ValidationException("Serial number must be 8 numbers.");
                    } else throw new ValidationException("Invalid manufacturer. Make sure it also is between 2 and 30 characters long.");
                } else throw new ValidationException("Invalid product name. Make sure it also is between 2 and 50 characters long.");
            } else throw new ValidationException("Invalid description. Make sure it is between 5 and 500 characters long. Colon ( : ) and semicolon ( ; ) not allowed");
        } else throw new ValidationException("Invalid price. Make sure you include decimals: X.XX");
    }
}
