package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Motherboard extends ComputerComponent {
    private transient SimpleStringProperty CPUSupport; //e.g AMD 1st & 2nd Gen Ryzen Processors
    private transient SimpleStringProperty memoryType; //e.g Dual-Channel DDR4;
    private transient SimpleStringProperty memoryDIMMs; //e.g 4*DDR4;
    private transient SimpleStringProperty graphicInterface; //e.g 1PCIe 3.0 x16
    private transient SimpleStringProperty expansionSlots; //e.g 1*PCIe 2.0 x4 / 2*PCIe 2.0 x1
    private transient SimpleStringProperty m2Slot; //e.g 2(M.2 Heatsink * 2)
    private transient SimpleStringProperty displayInterface; //e.g HDMI, DVI-D
    private transient SimpleStringProperty WIFI; //e.g Intel Dual Band 802.11ac WIFI or N/A.
    private transient SimpleStringProperty audio; //e.g 8-Channel HD.
    private transient SimpleStringProperty formFactor; //e.g ATX (305x244).

    private final String validateCPUSupport = "[a-zæøåA-ZÆØÅ0-9\\-._@*¨^`=)(/&%$#\"! ]+";
    private final String validateMemoryType = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
    private final String validateMemoryDIMMS = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
    private final String validateGraphicInterface = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
    private final String validateExpansionSlots = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
    private final String validateM2Slot = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
    private final String validateDisplayInterface = "SCART|VGA|DVI|SDI|HDMI|DisplayPort|Mini-DVI|RCA";
    private final String validateWIFI = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+|N/A]";
    private final String validateAudio = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
    private final String validateFormFactor = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";


    public Motherboard(double price, String description, String productName, String productionCompany, String serialNumber, String CPUSupport, String memoryType, String memoryDIMMs, String graphicInterface, String expansionSlots, String m2Slot, String displayInterface, String WIFI, String audio, String formFactor) {
        super(price, description, productName, productionCompany, serialNumber);
        this.CPUSupport = new SimpleStringProperty(CPUSupport);
        this.memoryType = new SimpleStringProperty(memoryType);
        this.memoryDIMMs = new SimpleStringProperty(memoryDIMMs);
        this.graphicInterface = new SimpleStringProperty(graphicInterface);
        this.expansionSlots = new SimpleStringProperty(expansionSlots);
        this.m2Slot = new SimpleStringProperty(m2Slot);
        this.displayInterface = new SimpleStringProperty(displayInterface);
        this.WIFI = new SimpleStringProperty(WIFI);
        this.audio = new SimpleStringProperty(audio);
        this.formFactor = new SimpleStringProperty(formFactor);
    }

    public String getCPUSupport() {
        return CPUSupport.get();
    }

    public String getMemoryType() {
        return memoryType.get();
    }

    public String getMemoryDIMMs() {
        return memoryDIMMs.get();
    }

    public String getGraphicInterface() {
        return graphicInterface.get();
    }

    public String getExpansionSlots() {
        return expansionSlots.get();
    }

    public String getM2Slot() {
        return m2Slot.get();
    }

    public String getDisplayInterface() {
        return displayInterface.get();
    }

    public String getWIFI() {
        return WIFI.get();
    }

    public String getAudio() {
        return audio.get();
    }

    public String getFormFactor() {
        return formFactor.get();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        super.write(s);
        s.defaultWriteObject();

        s.writeUTF(CPUSupport.getValue());
        s.writeUTF(memoryType.getValue());
        s.writeUTF(memoryDIMMs.getValue());
        s.writeUTF(graphicInterface.getValue());
        s.writeUTF(expansionSlots.getValue());
        s.writeUTF(m2Slot.getValue());
        s.writeUTF(displayInterface.getValue());
        s.writeUTF(WIFI.getValue());
        s.writeUTF(audio.getValue());
        s.writeUTF(formFactor.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        super.read(s);
        String CPUSupport = s.readUTF();
        String memoryType = s.readUTF();
        String memoryDIMMs = s.readUTF();
        String graphicInterface = s.readUTF();
        String expansionSlots = s.readUTF();
        String m2Slot = s.readUTF();
        String displayInterface = s.readUTF();
        String WIFI = s.readUTF();
        String audio = s.readUTF();
        String formFactor = s.readUTF();

        this.CPUSupport = new SimpleStringProperty(CPUSupport);
        this.memoryType = new SimpleStringProperty(memoryType);
        this.memoryDIMMs = new SimpleStringProperty(memoryDIMMs);
        this.graphicInterface = new SimpleStringProperty(graphicInterface);
        this.expansionSlots = new SimpleStringProperty(expansionSlots);
        this.m2Slot = new SimpleStringProperty(m2Slot);
        this.displayInterface = new SimpleStringProperty(displayInterface);
        this.WIFI = new SimpleStringProperty(WIFI);
        this.audio = new SimpleStringProperty(audio);
        this.formFactor = new SimpleStringProperty(formFactor);

    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if (Pattern.matches(validateCPUSupport, getCPUSupport())){
            if (Pattern.matches(validateMemoryType, getMemoryType())){
                if (Pattern.matches(validateMemoryDIMMS, getMemoryDIMMs())){
                    if (Pattern.matches(validateGraphicInterface, getGraphicInterface())){
                        if (Pattern.matches(validateExpansionSlots, getExpansionSlots())){
                            if (Pattern.matches(validateM2Slot, getM2Slot())){
                                if(Pattern.matches(validateDisplayInterface, getDisplayInterface())){
                                    if(Pattern.matches(validateWIFI, getWIFI())){
                                        if (Pattern.matches(validateAudio, getAudio())){
                                            if (Pattern.matches(validateFormFactor, getFormFactor())){
                                                return true;
                                            }else throw new ValidationException("Invalid form factor");
                                        } else throw new ValidationException("Invalid audio");
                                    } else throw new ValidationException("Invalid WIFI or not N/A");
                                } else throw new ValidationException("Only SCART/VGA/DVI/SDI/HDMI/DisplayPort/Mini-DVI and RCA are currently allowed.");
                            } else throw new ValidationException("Invalid M2Slots");
                        } else throw new ValidationException("Invalid expansion slots");
                    } else throw new ValidationException("Invalid graphic interface");
                } else throw new ValidationException("Invalid memory DIMMs");
            } else throw new ValidationException("Invalid memory type");
        } else throw new ValidationException("Invalid CPU support");
    }
}
