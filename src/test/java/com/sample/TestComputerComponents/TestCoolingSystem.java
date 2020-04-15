package com.sample.TestComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.CoolingSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCoolingSystem {
    @Test
    public void testValidCoolingSystem() throws ValidationException {
        CoolingSystem cs = new CoolingSystem("9", "9", 250, "Very cool Cooling", "Asus ROG Strix LC 360 RGB Cooler", "Asus", "12345678");
        assertTrue(cs.validate());
    }

    @Test
    public void testThrowsValidationExceptionCoolingSystem() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            CoolingSystem cs = new CoolingSystem("9", "9", -250, "Very cool Cooling,", "Asus ROG Strix LC 360 RGB Cooler", "Asus", "12345678");
            assertTrue(cs.validate());
        });
        assertThrows(ValidationException.class, () -> {
            CoolingSystem cs = new CoolingSystem("9", "9", 250, "Very cool Cooling,", "A", "Asus", "12345678");
            assertTrue(cs.validate());
        });
        assertThrows(ValidationException.class, () -> {
            CoolingSystem cs = new CoolingSystem("9", "9", 250, "Very", "Asus ROG Strix LC 360 RGB Cooler", "Asus", "12345678");
            assertTrue(cs.validate());
        });
    }
}

