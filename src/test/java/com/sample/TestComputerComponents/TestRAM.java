package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRAM {
    @Test
    public void testValidRam() throws ValidationException {
        RAM ram = new RAM(1099, "Fast and big storage", "HyperX Predator", "HyperX", "12345678", "16", "3200");
        assertTrue(ram.validate());
    }
    @Test
    public void testThrowsValidationExceptionRam() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            RAM ram = new RAM(1099, "Fast and big storage", "HyperX Predator", "HyperX", "12345678", "16", "3200mhz");
            assertTrue(ram.validate());
        });
        assertThrows(ValidationException.class, () -> {
            RAM ram = new RAM(1099, "Fast and big storage", "HyperX Predator", "HyperX", "12345678", "16gb", "3200");
            assertTrue(ram.validate());
        });
        assertThrows(ValidationException.class, () -> {
            RAM ram = new RAM(-1099, "Fast and big storage", "HyperX Predator", "HyperX", "12345678", "16", "3200");
            assertTrue(ram.validate());
        });
    }
}
