package com.sample.DAL.OpenFile.interfaces;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.util.List;


//using subtype polimorfism we can use multithreading with only one call-method in the super-class (OpenAddedComponents).
//all subclasses (like OpenCases, OpenCoolingSystems) implement this interface to ensure
//the right files are being read when trying to initiate their respective tableview. Because of this interface
//we can simply call this.perform() in OpenAddedComponents, instead of having one read-method in every single class.
public interface OpenCorrectFolder {
    List<? extends ComputerComponent> perform() throws IOException, ValidationException, ClassNotFoundException;
}
