package com.sample.DAL.SaveFile;

public class SaveTxt extends FileSaver {
    private Object dataToSave;
    public SaveTxt(Object dataToSave) {
        super(dataToSave);
        this.dataToSave = dataToSave;
    }

    @Override
    protected Boolean call() throws Exception {
        return super.writeToFile(dataToSave);
    }
}
