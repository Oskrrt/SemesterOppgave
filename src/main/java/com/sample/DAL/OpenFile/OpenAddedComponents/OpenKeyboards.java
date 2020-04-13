package com.sample.DAL.OpenFile.OpenAddedComponents;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.util.List;

public class OpenKeyboards extends FileOpenerJobj {
    @Override
    protected List<? extends ComputerComponent> call() throws Exception {
        return ComponentFactory.createKeyboardsFromFile();
    }
}
