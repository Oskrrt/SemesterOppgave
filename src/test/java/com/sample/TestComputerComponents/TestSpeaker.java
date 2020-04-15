package com.sample.TestComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.RAM;
import com.sample.Models.ComputerComponents.Speaker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSpeaker {
    @Test
    public void testValidSpeaker() throws ValidationException {
        Speaker sk = new Speaker(149, "Good quality sound", "Bose supergood", "Bose", "12345678", "USB-C");
        assertTrue(sk.validate());
    }
    @Test
    public void testThrowsValidationSpeaker() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Speaker sk = new Speaker(149, "Good quality sound", "Bose supergood", "Bose", "12345678", "USB-K");
            assertTrue(sk.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Speaker sk = new Speaker(-149, "Good quality sound", "Bose supergood", "Bose", "12345678", "USB-C");
            assertTrue(sk.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Speaker sk = new Speaker(149, "Good quality sound", "Bose supergood", "Bose", "123456789", "USB-C");
            assertTrue(sk.validate());
        });
    }
}
