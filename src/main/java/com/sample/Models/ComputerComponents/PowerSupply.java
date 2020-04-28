package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class PowerSupply extends ComputerComponent {
    private transient SimpleStringProperty powerSource;//e.g corded-electric
    private transient SimpleStringProperty voltage; //e.g 230 volts
    private transient SimpleStringProperty watts; //e.g 600 watts

    public PowerSupply(double price, String description, String productName, String productionCompany, String serialNumber, String powerSource, String voltage, String watts) {
        super(price, description, productName, productionCompany, serialNumber);
        this.powerSource = new SimpleStringProperty(powerSource);
        this.voltage = new SimpleStringProperty(voltage);
        this.watts = new SimpleStringProperty(watts);
    }

    public String getPowerSource() {
        return powerSource.get();
    }

    public String getVoltage() {
        return voltage.get();
    }

    public String getWatts() {
        return watts.get();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeUTF(powerSource.getValue());
        s.writeUTF(voltage.getValue());
        s.writeUTF(watts.getValue());

    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String powersrc = s.readUTF();
        String volt = s.readUTF();
        String watt = s.readUTF();

        this.powerSource = new SimpleStringProperty(powersrc);
        this.voltage = new SimpleStringProperty(volt);
        this.watts = new SimpleStringProperty(watt);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        String validatePowerSource = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,30}";
        if (Pattern.matches(validatePowerSource, getPowerSource())){
            String validateVoltage = "[0-9]{3,4}";
            if(Pattern.matches(validateVoltage, getVoltage())){
                String validateWatts = "[0-9]{3,4}";
                if (Pattern.matches(validateWatts, getWatts())){
                    return true;
                } else throw new ValidationException("Invalid watts");
            } else throw new ValidationException("Invalid voltage");
        } else throw new ValidationException("Invalid power source. (Most are corded-electric)");
    }
}
