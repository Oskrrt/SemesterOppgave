package com.sample.Models.Computer;

import com.sample.BLL.UserLogic;
import com.sample.Models.ComputerComponents.*;
import javafx.beans.property.SimpleDoubleProperty;

import java.security.Key;
import java.util.List;

public class ComputerWithAccessories extends Computer {
    private Computer Computer;
    private Mouse Mouse;
    private Monitor Monitor;
    private Keyboard Keyboard;
    private Speaker Speaker;
    private SimpleDoubleProperty Price;
    public ComputerWithAccessories(Computer computer, Mouse mouse, Monitor monitor, Speaker speaker, Keyboard keyboard, double price) {
        super(null, null, null, null, null, null, null, null, null, 0);
        this.Price = new SimpleDoubleProperty(price);
        this.Computer = computer;
        this.Mouse = mouse;
        this.Monitor = monitor;
        this.Keyboard = keyboard;
        this.Speaker = speaker;
    }

    public ComputerWithAccessories(String name, double price){
        super(name, price);
    }
    @Override
    public String toString() {
        return Computer.getName()+":"+Mouse.getClass().getSimpleName()+";"+Mouse.toString()+":"+Monitor.getClass().getSimpleName()+";"+Monitor.toString()+":"+Keyboard.getClass().getSimpleName()+";"+Keyboard.toString()+":"+Speaker.getClass().getSimpleName()+";"+Speaker.toString()+":"+Computer.AccessoryToString();
    }


    public String getTheMouse() {
        return Mouse.getProductName();
    }
    public String getTheMonitor() {
        return Monitor.getProductName();
    }
    public String getTheKeyboard() {
        return Keyboard.getProductName();
    }
    public String getTheSpeaker() {
        return Speaker.getProductName();
    }


    public double getPriceObject() {
        return Price.getValue();
    }

    public void setPrice(double price) {
        this.Price.set(price);
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
