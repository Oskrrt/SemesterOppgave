package com.sample.BLL;

import com.sample.Models.ComputerComponents.*;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.DrawMode;
import kong.unirest.JsonPatchItem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EditTable {



    public static List<String> caseJobjToForm(Case componentToChange) {
        ArrayList<String> caseFormFields = new ArrayList();

        caseFormFields.add(componentToChange.getDescription());
        caseFormFields.add(String.valueOf(componentToChange.getPrice()));
        caseFormFields.add(componentToChange.getProductName());
        caseFormFields.add(componentToChange.getProductionCompany());
        caseFormFields.add(componentToChange.getSerialNumber());
        caseFormFields.add(componentToChange.getNumberOfUSBPorts());
        caseFormFields.add(componentToChange.getHDAudioJacks());
        caseFormFields.add(componentToChange.getWidthCM());
        caseFormFields.add(componentToChange.getHeightCM());
        caseFormFields.add(componentToChange.getDepthCM());

        return caseFormFields;
    }

    public static List<String> coolingSystemJobjToForm(ComputerComponent componentToChange){
        ArrayList<String> coolingSystemFormFields = new ArrayList<>();

        coolingSystemFormFields.add(componentToChange.getDescription());
        coolingSystemFormFields.add(String.valueOf(componentToChange.getPrice()));
        coolingSystemFormFields.add(componentToChange.getProductName());
        coolingSystemFormFields.add(componentToChange.getProductionCompany());
        coolingSystemFormFields.add(componentToChange.getSerialNumber());

        return coolingSystemFormFields;
    }
//
//    public static List<TextField> CPUJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> GPUJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> keyboardJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> monitorJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> motherboardJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> mouseJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> powerSupplyJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> RAMJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> speakerJobjToForm(ComputerComponent componentToChange){
//
//    }
//
//    public static List<TextField> storageComponentJobjToForm(ComputerComponent componentToChange){
//
//    }

}
