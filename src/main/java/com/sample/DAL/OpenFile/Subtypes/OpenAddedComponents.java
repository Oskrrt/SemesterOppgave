package com.sample.DAL.OpenFile.Subtypes;

import com.sample.BLL.ComponentFactory;
import com.sample.BLL.InputValidation.ValidationException;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.DAL.OpenFile.interfaces.OpenCorrectFolder;
import com.sample.Models.Computer.Computer;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;

//se the comment in the OpenCorrectFolder interface.
public class OpenAddedComponents extends FileOpenerJobj {

    @Override
    protected List<? extends ComputerComponent> call() throws Exception {
        return perform();
    }



    public List<? extends ComputerComponent> perform() throws IOException, ValidationException, ClassNotFoundException {
        return this.perform();
    }

}

