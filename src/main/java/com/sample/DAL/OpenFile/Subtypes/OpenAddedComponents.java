package com.sample.DAL.OpenFile.Subtypes;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.DAL.OpenFile.interfaces.OpenCorrectFolder;
import com.sample.Models.Computer.Computer;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;


public class OpenAddedComponents extends FileOpenerJobj {

    @Override
    protected List<? extends ComputerComponent> call() throws Exception {
        return perform();
    }


    //using subtype polimorfism we can use multithreading with only one call-method in this super-class.
    //all subclasses (like OpenCases, OpenCoolingSystems) implement the interface OpenCorrectFolder to ensure
    //the right files are being read when trying to initiate their respective tableview. Because of this interface
    //we can simply call this.perform() in this super-class
    public List<? extends ComputerComponent> perform() throws IOException {
        return this.perform();
    }

}

