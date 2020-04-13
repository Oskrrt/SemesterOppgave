package com.sample.DAL.OpenFile.OpenAddedComponents;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.util.List;

public class OpenGPUs extends FileOpenerJobj {
    @Override
    protected List<? extends ComputerComponent> call() throws Exception {
        return ComponentFactory.createGraphicsCardsFromFile();
    }
}
