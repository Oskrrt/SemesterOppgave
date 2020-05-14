package com.sample.DAL.OpenFile.Subtypes;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.interfaces.OpenCorrectFolder;
import com.sample.Exceptions.InvalidFileDataException;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;

public class OpenSpeakers extends OpenAddedComponents implements OpenCorrectFolder {

    public OpenSpeakers(boolean adminThread) {
        super(adminThread);
    }

    @Override
    public List<? extends ComputerComponent> perform() throws IOException, InvalidFileDataException, ValidationException {
        if (super.adminThread){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ComponentFactory.createSpeakersFromFile();
    }
}
