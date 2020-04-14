package com.sample.DAL.OpenFile.Subtypes;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.interfaces.OpenCorrectFolder;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;

public class OpenCPUs extends OpenAddedComponents implements OpenCorrectFolder {
    @Override
    public List<? extends ComputerComponent> perform() throws IOException {
        return ComponentFactory.createProcessorsFromFile();
    }
}
