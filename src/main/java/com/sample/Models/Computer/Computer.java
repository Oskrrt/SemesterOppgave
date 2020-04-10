package com.sample.Models.Computer;

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
}

