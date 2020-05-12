package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.Mouse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMouse {
    @Test
    public void testValidMouse() throws ValidationException {
        Mouse mouse = new Mouse(899, "18000 dpi hardcore mouse", "Glorious Model O", "Glorious", "12345678", true);
        assertTrue(mouse.validate());

    }
    @Test
    public void testThrowsValidationExceptionMouse() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Mouse mouse = new Mouse(899, "18000 dpi hardcore mouse", "Glorious Model O", "Glorious", "123456789", true);
            assertTrue(mouse.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Mouse mouse = new Mouse(-899, "18000 dpi hardcore mouse", "Glorious Model O", "Glorious", "12345678", true);
            assertTrue(mouse.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Mouse mouse = new Mouse(899, "180", "Glorious Model O", "Glorious", "12345678", true);
            assertTrue(mouse.validate());
        });
    }
}

