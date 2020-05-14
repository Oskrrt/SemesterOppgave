package com.sample.Models.Computer;

import com.sample.BLL.UserLogic;
import com.sample.Models.ComputerComponents.*;
import javafx.beans.property.SimpleDoubleProperty;

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

    public String toString() {
        return Computer.toString()+":"+Mouse.toString()+":"+Monitor.toString()+":"+Keyboard.toString()+":"+Speaker.toString();
    }
    public String getValuesToSaveToFile() {
        String msg = "";
        List<ComputerComponent> accessoriesNotNull = UserLogic.getCurrentlyChosenComponentsForAccessorisedComputer(this);
        for (ComputerComponent accessory : accessoriesNotNull) {
            msg+=":"+accessory.getProductName()+";"+accessory.getPrice()+";"+accessory.getClass().getSimpleName();
        }
        return Computer.getValuesToSaveToFile()+msg;
    }
    public double getPrice() {
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
