package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Keyboard extends ComputerComponent {
    private transient SimpleStringProperty language; //e.g American, French, Norwegian...
    private transient SimpleStringProperty isWireless;
    private transient SimpleBooleanProperty wireless;

    private final String validateLanguage = "Norwegian|English"; //an API with all keyboard languages would be nice

    public Keyboard(double price, String description, String productName, String productionCompany, String serialNumber, String language, boolean wireless) {
        super(price, description, productName, productionCompany, serialNumber);
        this.language = new SimpleStringProperty(language);
        this.wireless = new SimpleBooleanProperty(wireless);
        this.isWireless = getIsWireless();
    }

    public String getLanguage() {
        return language.get();
    }

    //For a more user-friendly tableview. Yes/No instead of true/false.
    public SimpleStringProperty getIsWireless() {
        if (wireless.get()){
            this.isWireless = new SimpleStringProperty("Yes");
        } else {
            this.isWireless = new SimpleStringProperty("No");
        }
        return isWireless;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        super.write(s);
        s.defaultWriteObject();

        s.writeUTF(language.getValue());
        s.writeUTF(isWireless.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        super.read(s);
        String language = s.readUTF();
        String isWireless = s.readUTF();

        this.language = new SimpleStringProperty(language);
        this.isWireless = new SimpleStringProperty(isWireless);
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if(Pattern.matches(validateLanguage, getLanguage())){
            return true;
        } else throw new ValidationException("Only Norwegian and English keyboards are supported currently");
    }
}
