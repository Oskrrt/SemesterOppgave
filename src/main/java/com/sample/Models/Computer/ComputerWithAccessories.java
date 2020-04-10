package com.sample.Models.Computer;

import com.sample.Models.ComputerComponents.*;

public class ComputerWithAccessories extends Computer {
    private Computer computer;
    private Mouse mouse;
    private Monitor monitor;
    private Keyboard keyboard;
    private Speaker speaker;
    public ComputerWithAccessories(Computer computer, Mouse mouse, Monitor monitor, Speaker speaker, Keyboard keyboard) {
        super(null, null, null, null, null, null, null, null);
        this.computer = computer;
        this.mouse = mouse;
        this.monitor = monitor;
        this.keyboard = keyboard;
        this.speaker = speaker;
    }
}
