package com.sample.DAL.OpenFile.Subtypes;

import com.sample.Exceptions.InvalidFileDataException;
import com.sample.Exceptions.ValidationException;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

//see the comment in the OpenCorrectFolder interface for an explanation of what this file does.
public class OpenAddedComponents extends FileOpenerJobj implements Callable<List<? extends ComputerComponent>>{

    @Override
    public List<? extends ComputerComponent> call() throws InvalidFileDataException, IOException, ValidationException {
        return perform();
    }

    public List<? extends ComputerComponent> perform() throws IOException, InvalidFileDataException, ValidationException {
        return this.perform();
    }

}

