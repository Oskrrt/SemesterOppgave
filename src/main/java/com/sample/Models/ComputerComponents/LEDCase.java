package com.sample.Models.ComputerComponents;

public class LEDCase extends Case {
    private Case baseCase;

    public LEDCase(Case baseCase){
        super("","",0,0,null,0.0,"","","","");
        this.baseCase = baseCase;
    }
}
