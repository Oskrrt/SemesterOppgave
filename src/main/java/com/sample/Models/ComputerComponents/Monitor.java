package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Monitor extends ComputerComponent {
    private transient SimpleStringProperty displayType; //e.g LCD, OLED...
    private transient SimpleStringProperty inches; //e.g 16"
    private transient SimpleStringProperty resolution; //e.g 4k
    private transient SimpleStringProperty connector; //e.g HDMI

    private final String validateDisplayType = "LCD|CRT|LED|OLED|Plasma";
    private final String validateInches = "[0-9]{1,2}";
    private final String validateResolution = "[a-zæøåA-ZÆØÅ0-9]{2,6}";
    private final String validateConnector = "SCART|VGA|DVI|SDI|HDMI|DisplayPort|Mini-DVI|RCA|";

    public Monitor(double price, String description, String productName, String productionCompany, String serialNumber, String displayType, String inches, String resolution, String connector) {
        super(price, description, productName, productionCompany, serialNumber);
        this.displayType = new SimpleStringProperty(displayType);
        this.inches = new SimpleStringProperty(inches);
        this.resolution = new SimpleStringProperty(resolution);
        this.connector = new SimpleStringProperty(connector);
    }

    public String getDisplayType() {
        return displayType.get();
    }

    public String getInches() {
        return inches.get();
    }

    public String getResolution() {
        return resolution.get();
    }

    public String getConnector() {
        return connector.get();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        super.write(s);
        s.defaultWriteObject();

        s.writeUTF(displayType.getValue());
        s.writeUTF(inches.getValue());
        s.writeUTF(resolution.getValue());
        s.writeUTF(connector.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        super.read(s);
        String displayType = s.readUTF();
        String inches = s.readUTF();
        String resolution = s.readUTF();
        String connector = s.readUTF();

        this.displayType = new SimpleStringProperty(displayType);
        this.inches = new SimpleStringProperty(inches);
        this.resolution = new SimpleStringProperty(resolution);
        this.connector = new SimpleStringProperty(connector);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateDisplayType, getDisplayType())){
            if (Pattern.matches(validateInches, getInches())){
                if (Pattern.matches(validateResolution, getResolution())){
                    if (Pattern.matches(validateConnector, getConnector())){
                        return true;
                    } else throw new ValidationException("Only SCART/VGA/DVI/SDI/HDMI/DisplayPort/Mini-DVI and RCA are currently allowed");
                } else throw new ValidationException("Invalid resolution");
            } else throw new ValidationException("Invalid inches");
        } else throw new ValidationException("Only LCD/CRT/LED/OLED and Plasma are cuurently allowed");

    }
}
