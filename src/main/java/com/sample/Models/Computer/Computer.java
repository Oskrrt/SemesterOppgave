package com.sample.Models.Computer;

import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.*;

public class Computer {
    private Case computerCase;
    private CoolingSystem cooling;
    private GraphicsCard graphicsCard;
    private StorageComponent storageComponent;
    private Motherboard motherboard;
    private PowerSupply powerSupply;
    private Processor CPU;
    private RAM memory;
    private double price;

    public Computer(Case computerCase, CoolingSystem cooling, GraphicsCard graphicsCard, StorageComponent storageComponent,  Motherboard motherboard, PowerSupply powerSupply, Processor CPU, RAM memory) {
        this.computerCase = computerCase;
        this.cooling = cooling;
        this.graphicsCard = graphicsCard;
        this.storageComponent = storageComponent;
        this.motherboard = motherboard;
        this.powerSupply = powerSupply;
        this.CPU = CPU;
        this.memory = memory;
    }

    public Case getComputerCase() {
        return computerCase;
    }

    public CoolingSystem getCooling() {
        return cooling;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public StorageComponent getStorageComponent() {
        return storageComponent;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public Processor getCPU() {
        return CPU;
    }

    public RAM getMemory() {
        return memory;
    }

    public void setComputerCase(Case computerCase) {
        this.computerCase = computerCase;
    }

    public void setCooling(CoolingSystem cooling) {
        this.cooling = cooling;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public void setStorageComponent(StorageComponent storageComponent) {
        this.storageComponent = storageComponent;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public void setCPU(Processor CPU) {
        this.CPU = CPU;
    }

    public void setMemory(RAM memory) {
        this.memory = memory;
    }
}

