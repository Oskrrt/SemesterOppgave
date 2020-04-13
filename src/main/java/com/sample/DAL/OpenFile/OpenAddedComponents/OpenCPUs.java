package com.sample.DAL.OpenFile.OpenAddedComponents;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;

public class OpenCPUs extends FileOpenerJobj {
    @Override
    protected List<? extends ComputerComponent> call() throws IOException {
        return ComponentFactory.createProcessorsFromFile();
    }
}
