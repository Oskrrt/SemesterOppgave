package com.sample.BLL;

import com.sample.Exceptions.InvalidFileDataException;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Computer.ComputerWithAccessories;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.lang.reflect.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class UserLogic {

    private static List<ComputerComponent> getAllComponents(String type) throws IOException, InvalidFileDataException, ValidationException {
        List<? extends ComputerComponent> list = new ArrayList<>();
        switch (type) {
            case "Case":
                list = ComponentFactory.createCasesFromFile();
                break;
            case "graphicsCard":
                list = ComponentFactory.createGraphicsCardsFromFile();
                break;
            case "coolingSystem":

                list = ComponentFactory.createCoolingSystemFromFile();
                break;
            case "CPU":

                list = ComponentFactory.createProcessorsFromFile();
                break;
            case "keyboard":

                list = ComponentFactory.createKeyboardsFromFile();
                break;
            case "monitor":
                list = ComponentFactory.createMonitorsFromFile();
                break;
            case "motherBoard":
                list = ComponentFactory.createMotherboardsFromFile();
                break;
            case "mouse":
                list = ComponentFactory.createMiceFromFile();
                break;
            case "powerSupply":
                list = ComponentFactory.createPowerSuppliesFromFile();
                break;
            case "RAM":
                list = ComponentFactory.createRAMsFromFile();
                break;
            case "speaker":
                list = ComponentFactory.createSpeakersFromFile();
                break;
            case "storageComponent":
                list = ComponentFactory.createStorageComponentsFromFile();
                break;
        }
            return (List<ComputerComponent>) list;
    }

    public static List<ComputerComponent> getCurrentlyChosenComponents(Computer computerBeingBuilt) {
        List<ComputerComponent> products = new ArrayList<>();
        Field[] fields = Computer.class.getDeclaredFields();
        // loops over all the attributes in the computer class and checks if the computerBeingBuilt object has values assigned to them
        // if they do have values, add the components to the arraylist.
        for (Field field: fields) {
            String methodName = "get"+field.getName();
            try {
                // We only want the computer components for now, so we leave the price and the creator out.
                if((!methodName.equals("getPrice")) && (!methodName.equals("getCreator"))) {
                    ComputerComponent product = (ComputerComponent) computerBeingBuilt.getClass().getMethod(methodName).invoke(computerBeingBuilt);
                    if (product != null) {
                        products.add(product);
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public static List<ComputerComponent> getCurrentlyChosenComponentsForAccessorisedComputer(ComputerWithAccessories computerBeingBuilt) {
        List<ComputerComponent> products = new ArrayList<>();
        Field[] fields = ComputerWithAccessories.class.getDeclaredFields();

        // loops over all the attributes in the computer class and checks if the computerBeingBuilt object has values assigned to them
        // if they do have values, add the products to the arraylist.
        for (Field field: fields) {
            String methodName = "get"+field.getName();
            try {
                // We only want the accessories objects, so we leave the Computer out for now
                if(!methodName.equals("getComputer")) {
                    ComputerComponent product = (ComputerComponent) computerBeingBuilt.getClass().getMethod(methodName).invoke(computerBeingBuilt);
                    //System.out.println(product);
                    if (product != null) {
                        products.add(product);
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getCause());
            }
        }
        return products;
    }

    public static double calculatePriceOfComputer(List<ComputerComponent> chosenComponents) {
        double price = 0;
        for (ComputerComponent component : chosenComponents) {
            price += component.getPrice();
        }
        return price;
    }


}


