package com.sample.DAL.OpenFile.OpenAddedComponents;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.File;
import java.util.List;

public class OpenStorageComponents extends FileOpenerJobj {
    @Override
    protected List<? extends ComputerComponent> call() throws Exception {
        return ComponentFactory.createStorageComponentsFromFile();
    }
}
