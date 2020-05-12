package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.Keyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKeyboard {
    @Test
    public void testValidKeyboard() throws ValidationException {
        Keyboard kb = new Keyboard(999, "Full size keyboard with rgb", "Ducky One 2 Mini", "Ducky", "12345678", "Norwegian", false);
        assertTrue(kb.validate());

    }
    @Test
    public void testThrowsValidationExceptionKeyboard() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Keyboard kb = new Keyboard(-999, "Full size keyboard with rgb", "Ducky One 2 Mini", "Ducky", "12345678", "Norwegian", false);
            assertTrue(kb.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Keyboard kb = new Keyboard(999, "Full size keyboard with rgb", "Ducky One 2 Mini", "Ducky", "123456789", "Norwegian", false);
            assertTrue(kb.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Keyboard kb = new Keyboard(999, "Full size keyboard with rgb", "Ducky One 2 Mini", "D", "12345678", "Norwegian", false);
            assertTrue(kb.validate());
        });
    }
}

