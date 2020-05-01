package com.sample.DAL.OpenFile.Subtypes;

import com.sample.BLL.ComponentFactory;
import com.sample.BLL.InputValidation.ValidationException;
import com.sample.DAL.OpenFile.interfaces.OpenCorrectFolder;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;
public class OpenCases extends OpenAddedComponents implements OpenCorrectFolder {
    @Override
    public List<? extends ComputerComponent> perform() throws IOException, ValidationException, ClassNotFoundException {
        return ComponentFactory.createCasesFromFile();
    }
}
