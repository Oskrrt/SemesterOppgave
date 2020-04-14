package com.sample.DAL.OpenFile.interfaces;

import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;

public interface OpenCorrectFolder {
    List<? extends ComputerComponent> perform() throws IOException;
}
