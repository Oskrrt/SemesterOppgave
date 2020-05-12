package com.sample.DAL.OpenFile.Subtypes;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.interfaces.OpenCorrectFolder;
import com.sample.Exceptions.InvalidFileDataException;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;

public class OpenSpeakers extends OpenAddedComponents implements OpenCorrectFolder {

    @Override
    public List<? extends ComputerComponent> perform() throws IOException, InvalidFileDataException, ValidationException {
        return ComponentFactory.createSpeakersFromFile();
    }
}
