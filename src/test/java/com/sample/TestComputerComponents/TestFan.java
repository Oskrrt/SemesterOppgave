package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.Fan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFan {
    @Test
    public void testValidFan() throws ValidationException{
        Fan f = new Fan("20", "20", 350, "Very cool Cooling", "Asus ROG Strix LC 360 RGB Cooler", "Asus", "12345678");
        assertTrue(f.validate());
    }

    @Test
    public void testThrowsValidationExceptionFan() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Fan f = new Fan("20", "20", -350, "Very cool Cooling,", "Asus ROG Strix LC 360 RGB Cooler", "Asus", "12345678");
            assertTrue(f.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Fan f = new Fan("20", "20", 350, "Very cool Cooling,", "A", "Asus", "12345678");
            assertTrue(f.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Fan f = new Fan("20", "20", 350, "Very", "Asus ROG Strix LC 360 RGB Cooler", "Asus", "12345678");
            assertTrue(f.validate());
        });
    }
}

