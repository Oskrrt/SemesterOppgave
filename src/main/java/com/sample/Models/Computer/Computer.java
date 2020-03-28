package com.sample.Models.Computer;

import com.sample.Models.ComputerComponents.*;

public class Computer {
    private Case computerCase;
    private CoolingSystem cooling;
    private GraphicsCard graphicsCard;
    private StorageComponent storageComponent;
    private Keyboard keyboard;
    private Monitor monitor;
    private Motherboard motherboard;
    private Mouse mouse;
    private PowerSupply powerSupply;
    private Processor CPU;
    private RAM memory;

    public Computer(Case computerCase, CoolingSystem cooling, GraphicsCard graphicsCard, StorageComponent storageComponent, Keyboard keyboard, Monitor monitor, Motherboard motherboard, Mouse mouse, PowerSupply powerSupply, Processor CPU, RAM memory) {
        this.computerCase = computerCase;
        this.cooling = cooling;
        this.graphicsCard = graphicsCard;
        this.storageComponent = storageComponent;
        this.keyboard = keyboard;
        this.monitor = monitor;
        this.motherboard = motherboard;
        this.mouse = mouse;
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

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public Mouse getMouse() {
        return mouse;
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

