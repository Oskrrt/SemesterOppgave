package com.sample.Exceptions;

import java.io.IOException;

public class InvalidFileDataException extends IOException {
    public InvalidFileDataException(String msg){
        super(msg + "please reinstall the program or contact support help desk");
    }
}
