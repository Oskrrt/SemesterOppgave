package com.sample.Models.Computer;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.*;
import com.sample.Models.Users.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class Computer {
    private SimpleStringProperty Name;
    private Case ComputerCase;
    private CoolingSystem Cooling;
    private GraphicsCard GraphicsCard;
    private StorageComponent StorageComponent;
    private Motherboard Motherboard;
    private PowerSupply PowerSupply;
    private Processor CPU;
    private RAM Memory;
    private SimpleDoubleProperty Price;
    private User Creator;

    public Computer(String name, Case computerCase, CoolingSystem cooling, GraphicsCard graphicsCard, StorageComponent storageComponent,  Motherboard motherboard, PowerSupply powerSupply, Processor CPU, RAM memory, double price) {
        this.Name = new SimpleStringProperty(name);
        this.ComputerCase = computerCase;
        this.Cooling = cooling;
        this.GraphicsCard = graphicsCard;
        this.StorageComponent = storageComponent;
        this.Motherboard = motherboard;
        this.PowerSupply = powerSupply;
        this.CPU = CPU;
        this.Memory = memory;
        this.Price = new SimpleDoubleProperty(price);
    }

    public Computer(String name, double price) {
        this.Name = new SimpleStringProperty(name);
        this.Price = new SimpleDoubleProperty(price);
    }

    public String toString() {
        return Name.getValueSafe()+":"+ComputerCase.toString()+":"+Cooling.toString()+":"+GraphicsCard.toString()+":"+StorageComponent.toString()+":"+Motherboard.toString()+":"+PowerSupply.toString()+":"+CPU.toString()+":"+Memory.toString();
    }

    public String getValuesToSaveToFile() {
        return Name.getValueSafe()+":"+ComputerCase.getProductName()+";"+ComputerCase.getPrice()+":"+Cooling.getProductName()+";"+Cooling.getPrice()+":"+GraphicsCard.getProductName()+";"+GraphicsCard.getPrice()+":"+StorageComponent.getProductName()+";"+StorageComponent.getPrice()+":"+Motherboard.getProductName()+";"+Motherboard.getPrice()+":"+PowerSupply.getProductName()+";"+PowerSupply.getPrice()+":"+CPU.getProductName()+";"+CPU.getPrice()+":"+Memory.getProductName()+";"+Memory.getPrice();
    }


    public boolean allFieldsSet() {
        if (this.Memory == null || this.GraphicsCard == null || this.CPU == null || this.PowerSupply == null || this.Motherboard == null || this.ComputerCase == null || this.Cooling == null || this.StorageComponent == null) {
            return false;
        }
        return true;
    }

    public Case getComputerCase() {
        return ComputerCase;
    }

    public CoolingSystem getCooling() {
        return Cooling;
    }

    public GraphicsCard getGraphicsCard() { return GraphicsCard; }

    public StorageComponent getStorageComponent() {
        return StorageComponent;
    }

    public Motherboard getMotherboard() {
        return Motherboard;
    }

    public PowerSupply getPowerSupply() {
        return PowerSupply;
    }

    public Processor getCPU() {
        return CPU;
    }

    public RAM getMemory() {
        return Memory;
    }

    public User getCreator(){return Creator;}

    public double getPrice(){return Price.getValue();}

    public void setComputerCase(Case computerCase) {
        this.ComputerCase = computerCase;
    }

    public void setCooling(CoolingSystem cooling) {
        this.Cooling = cooling;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.GraphicsCard = graphicsCard;
    }

    public void setStorageComponent(StorageComponent storageComponent) {
        this.StorageComponent = storageComponent;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.Motherboard = motherboard;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.PowerSupply = powerSupply;
    }

    public void setCPU(Processor CPU) {
        this.CPU = CPU;
    }

    public void setMemory(RAM memory) {
        this.Memory = memory;
    }
    public void setCreator(User creator) {
        this.Creator = creator;
    }
    public void setPrice(Double price) {
        this.Price.set(price);
    }

    public void setName(String name) {
        this.Name.set(name);
    }
    public String getName() {
        return Name.getValue();
    }

    public boolean validateName(String name) throws ValidationException {
        String validName = "[a-zæøåA-ZÆØÅ0-9\\-_.#]{2,20}";
        if (Pattern.matches(validName, name)) {
            return true;
        } else {
            throw new ValidationException("Invalid name, make sure it is between 2 and 20 characters long.\nAllowed characters are: a-z æ øå _ - . # 1-9");
        }
    }
}

