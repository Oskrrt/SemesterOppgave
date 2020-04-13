package com.sample.DAL.OpenFile.OpenAddedComponents;

import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class OpenCases extends FileOpenerJobj {
    @Override
    protected List<? extends ComputerComponent> call() throws IOException {
        return ComponentFactory.createCasesFromFile();
    }
}
