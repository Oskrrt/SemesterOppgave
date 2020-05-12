package com.sample.Models.Computer;

import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.*;
import com.sample.Models.Users.User;

import java.lang.reflect.Field;

public class Computer {
    private Case ComputerCase;
    private CoolingSystem Cooling;
    private GraphicsCard GraphicsCard;
    private StorageComponent StorageComponent;
    private Motherboard Motherboard;
    private PowerSupply PowerSupply;
    private Processor CPU;
    private RAM Memory;
    private double Price;
    private User Creator;

    public Computer(Case computerCase, CoolingSystem cooling, GraphicsCard graphicsCard, StorageComponent storageComponent,  Motherboard motherboard, PowerSupply powerSupply, Processor CPU, RAM memory) {
        this.ComputerCase = computerCase;
        this.Cooling = cooling;
        this.GraphicsCard = graphicsCard;
        this.StorageComponent = storageComponent;
        this.Motherboard = motherboard;
        this.PowerSupply = powerSupply;
        this.CPU = CPU;
        this.Memory = memory;
    }

    public String toString() {
        return ComputerCase.toString()+":"+Cooling.toString()+":"+GraphicsCard.toString()+":"+StorageComponent.toString()+":"+Motherboard.toString()+":"+PowerSupply.toString()+":"+CPU.toString()+":"+Memory.toString();
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

    public double getPrice(){return Price;}

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
        this.Price = price;
    }
}

