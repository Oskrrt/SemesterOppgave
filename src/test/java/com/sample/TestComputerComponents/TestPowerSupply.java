package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.PowerSupply;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPowerSupply {
    @Test
    public void testValidPowerSupply() throws ValidationException {
        PowerSupply ps = new PowerSupply(199, "650w psu", "Corsair TX650M", "Corsair", "12345678", "Modular", "230", "650");
        assertTrue(ps.validate());
    }
    @Test
    public void testThrowsValidationExceptionPowerSupply() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            PowerSupply ps = new PowerSupply(199, "650w psu", "Corsair TX650M", "Corsair", "12345678", "Modular", "230V", "650");
            assertTrue(ps.validate());
        });
        assertThrows(ValidationException.class, () -> {
            PowerSupply ps = new PowerSupply(199, "650w psu", "Corsair TX650M", "Corsair", "12345678", "Modular", "230", "650W");
            assertTrue(ps.validate());
        });
        assertThrows(ValidationException.class, () -> {
            PowerSupply ps = new PowerSupply(199, "650w psu", "Corsair TX650M", "Corsair", "123456789", "Modular", "230", "650");
            assertTrue(ps.validate());
        });
    }
}

