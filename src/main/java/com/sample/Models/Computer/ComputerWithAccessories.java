package com.sample.Models.Computer;

import com.sample.Models.ComputerComponents.*;

public class ComputerWithAccessories extends Computer {
    private Computer Computer;
    private Mouse Mouse;
    private Monitor Monitor;
    private Keyboard Keyboard;
    private Speaker Speaker;
    private double Price;
    public ComputerWithAccessories(Computer computer, Mouse mouse, Monitor monitor, Speaker speaker, Keyboard keyboard) {
        super(null, null, null, null, null, null, null, null);
        this.Computer = computer;
        this.Mouse = mouse;
        this.Monitor = monitor;
        this.Keyboard = keyboard;
        this.Speaker = speaker;
    }
    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public Computer getComputer() {
        return Computer;
    }

    public void setComputer(Computer computer) {
        this.Computer = computer;
    }

    public Mouse getMouse() {
        return Mouse;
    }

    public void setMouse(Mouse mouse) {
        this.Mouse = mouse;
    }

    public Monitor getMonitor() {
        return Monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.Monitor = monitor;
    }

    public Keyboard getKeyboard() {
        return Keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.Keyboard = keyboard;
    }

    public Speaker getSpeaker() {
        return Speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.Speaker = speaker;
    }
}
