package com.sample.Models.ComputerComponents;

public class Processor extends ComputerComponent {
    private String coreCount; //e.g 6 cores
    private String threadCount; //e.g 12 Threads
    private String maxFrequency; //e.g 4.2Hz

    public Processor(double price, String description, String productName, String productionCompany, String serialNumber, String coreCount, String threadCount, String maxFrequency) {
        super(price, description, productName, productionCompany, serialNumber);
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.maxFrequency = maxFrequency;
    }

    public String getCoreCount() {
        return coreCount;
    }

    public String getThreadCount() {
        return threadCount;
    }

    public String getMaxFrequency() {
        return maxFrequency;
    }
}
