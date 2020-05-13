package com.sample.Models.ComputerComponents;

import com.sample.Exceptions.ValidationException;
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

    public String toString() {
        return super.toString()+CPUSupport.getValueSafe()+";"+memoryType.getValueSafe()+";"+memoryDIMMs.getValueSafe()+";"+graphicInterface.getValueSafe()+";"+expansionSlots.getValueSafe()+";"+m2Slot.getValueSafe()+";"+displayInterface.getValueSafe()+";"+WIFI.getValueSafe()+";"+audio.getValueSafe()+";"+formFactor.getValueSafe();
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

    public void setCPUSupport(String CPUSupport) {
        this.CPUSupport.set(CPUSupport);
    }

    public void setMemoryType(String memoryType) {
        this.memoryType.set(memoryType);
    }

    public void setMemoryDIMMs(String memoryDIMMs) {
        this.memoryDIMMs.set(memoryDIMMs);
    }

    public void setGraphicInterface(String graphicInterface) {
        this.graphicInterface.set(graphicInterface);
    }

    public void setExpansionSlots(String expansionSlots) {
        this.expansionSlots.set(expansionSlots);
    }

    public void setM2Slot(String m2Slot) {
        this.m2Slot.set(m2Slot);
    }

    public void setDisplayInterface(String displayInterface) {
        this.displayInterface.set(displayInterface);
    }

    public void setWIFI(String WIFI) {
        this.WIFI.set(WIFI);
    }

    public void setAudio(String audio) {
        this.audio.set(audio);
    }

    public void setFormFactor(String formFactor) {
        this.formFactor.set(formFactor);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
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

        String validateCPUSupport = "[a-zæøåA-ZÆØÅ0-9\\-._@*¨^`=)(/&%$#\"! ]+";
        if (Pattern.matches(validateCPUSupport, getCPUSupport()) && !getCPUSupport().isBlank()){
            String validateMemoryType = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
            if (Pattern.matches(validateMemoryType, getMemoryType()) && !getMemoryType().isBlank()){
                String validateMemoryDIMMS = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
                if (Pattern.matches(validateMemoryDIMMS, getMemoryDIMMs()) && !getMemoryDIMMs().isBlank()){
                    String validateGraphicInterface = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
                    if (Pattern.matches(validateGraphicInterface, getGraphicInterface()) && !getGraphicInterface().isBlank()){
                        String validateExpansionSlots = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
                        if (Pattern.matches(validateExpansionSlots, getExpansionSlots()) && !getExpansionSlots().isBlank()){
                            String validateM2Slot = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
                            if (Pattern.matches(validateM2Slot, getM2Slot()) && !getM2Slot().isBlank()){
                                String validateDisplayInterface = "SCART|VGA|DVI|SDI|HDMI|DisplayPort|Mini-DVI|RCA";
                                if(Pattern.matches(validateDisplayInterface, getDisplayInterface()) && !getDisplayInterface().isBlank()){
                                    String validateWIFI = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+|N/A]";
                                    if(Pattern.matches(validateWIFI, getWIFI()) && !getWIFI().isBlank()){
                                        String validateAudio = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
                                        if (Pattern.matches(validateAudio, getAudio()) && !getAudio().isBlank()){
                                            String validateFormFactor = "[a-zæøåA-ZÆØÅ0-9\\-_.@*¨^`=)(/&%$#\"! ]+";
                                            if (Pattern.matches(validateFormFactor, getFormFactor()) && !getFormFactor().isBlank()){
                                                return true;
                                            }else throw new ValidationException("Invalid form factor");
                                        } else throw new ValidationException("Invalid audio");
                                    } else throw new ValidationException("Invalid WIFI. If there is no WIFI, write 'N/A'.");
                                } else throw new ValidationException("Only SCART/VGA/DVI/SDI/HDMI/DisplayPort/Mini-DVI and RCA are currently allowed.");
                            } else throw new ValidationException("Invalid M2Slots");
                        } else throw new ValidationException("Invalid expansion slots");
                    } else throw new ValidationException("Invalid graphic interface");
                } else throw new ValidationException("Invalid memory DIMMs");
            } else throw new ValidationException("Invalid memory type");
        } else throw new ValidationException("Invalid CPU support");
    }
}
