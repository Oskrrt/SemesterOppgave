package com.sample.BLL.InputValidation;

import com.sample.Exceptions.ValidationException;

public interface ValidateForm {
    boolean validate() throws ValidationException;
}
