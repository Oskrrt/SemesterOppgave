package com.sample.Models.ComputerComponents;

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
}
