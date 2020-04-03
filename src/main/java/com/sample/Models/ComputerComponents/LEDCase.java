package com.sample.Models.ComputerComponents;

public class LEDCase extends Case {
    private Case baseCase;
    private boolean hasLED;
    public LEDCase(Case baseCase, boolean hasLED){
        super("","","0","0",100.0,"","","","");
        this.baseCase = baseCase;
        this.hasLED = hasLED;
    }
}
