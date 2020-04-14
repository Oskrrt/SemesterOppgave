package com.sample.Models.ComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;

import java.util.regex.Pattern;

public class Motherboard extends ComputerComponent {
    private String CPUSupport; //e.g AMD 1st & 2nd Gen Ryzen Processors
    private String memoryType; //e.g Dual-Channel DDR4;
    private String memoryDIMMs; //e.g 4*DDR4;
    private String graphicInterface; //e.g 1PCIe 3.0 x16
    private String expansionSlots; //e.g 1*PCIe 2.0 x4 / 2*PCIe 2.0 x1
    private String m2Slot; //e.g 2(M.2 Heatsink * 2)
    private String displayInterface; //e.g HDMI, DVI-D
    private String WIFI; //e.g Intel Dual Band 802.11ac WIFI or N/A.
    private String audio; //e.g 8-Channel HD.
    private String formFactor; //e.g ATX (305x244).

    private final String validateCPUSupport = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,50}";
    private final String validateMemoryType = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,50}";
    private final String validateMemoryDIMMS = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,50}";
    private final String validateExpansionSlots = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,50}";
    private final String validateM2Slot = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,50}";
    private final String validateDisplayInterface = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,50}";
    private final String validateWIFI = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,20}";
    private final String validateAudio = "[a-zæøåA-ZÆØÅ ,;.:-_*]{3,50}";
    private final String validateFormFactor = "[a-zæøåA-ZÆØÅ ,;.:-_*]{2,10}";


    public Motherboard(double price, String description, String productName, String productionCompany, String serialNumber, String CPUSupport, String memoryType, String memoryDIMMs, String graphicInterface, String expansionSlots, String m2Slot, String displayInterface, String WIFI, String audio, String formFactor) {
        super(price, description, productName, productionCompany, serialNumber);
        this.CPUSupport = CPUSupport;
        this.memoryType = memoryType;
        this.memoryDIMMs = memoryDIMMs;
        this.graphicInterface = graphicInterface;
        this.expansionSlots = expansionSlots;
        this.m2Slot = m2Slot;
        this.displayInterface = displayInterface;
        this.WIFI = WIFI;
        this.audio = audio;
        this.formFactor = formFactor;
    }

    public String getCPUSupport() {
        return CPUSupport;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public String getMemoryDIMMs() {
        return memoryDIMMs;
    }

    public String getGraphicInterface() {
        return graphicInterface;
    }

    public String getExpansionSlots() {
        return expansionSlots;
    }

    public String getM2Slot() {
        return m2Slot;
    }

    public String getDisplayInterface() {
        return displayInterface;
    }

    public String getWIFI() {
        return WIFI;
    }

    public String getAudio() {
        return audio;
    }

    public String getFormFactor() {
        return formFactor;
    }

    @Override
    public boolean validate() throws ValidationException {
        super.validate();

        if (Pattern.matches(validateCPUSupport, getCPUSupport())){
            if (Pattern.matches(validateMemoryType, getMemoryType())){
                if (Pattern.matches(validateMemoryDIMMS, getMemoryDIMMs())){
                    if (Pattern.matches(validateExpansionSlots, getExpansionSlots())){
                        if (Pattern.matches(validateM2Slot, getM2Slot())){
                            if(Pattern.matches(validateDisplayInterface, getDisplayInterface())){
                                if(Pattern.matches(validateWIFI, getWIFI())){
                                    if (Pattern.matches(validateAudio, getAudio())){
                                        if (Pattern.matches(validateFormFactor, getFormFactor())){
                                            return true;
                                        }else throw new ValidationException("Invalid form factor");
                                    } else throw new ValidationException("Invalid audio");
                                } else throw new ValidationException("Invaldig WIFI");
                            } else throw new ValidationException("Invalid display interface");
                        } else throw new ValidationException("Invalid M2Slots");
                    } else throw new ValidationException("Invalid expansion slots");
                } else throw new ValidationException("Invalid memory DIMMs");
            } else throw new ValidationException("Invalid memory type");
        } else throw new ValidationException("Invalid CPU support");
    }
}
