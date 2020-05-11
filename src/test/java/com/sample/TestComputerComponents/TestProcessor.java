package com.sample.TestComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.PowerSupply;
import com.sample.Models.ComputerComponents.Processor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProcessor {
    @Test
    public void testValidProcessor() throws ValidationException {
        Processor cpu = new Processor(3499, "Intelcore good processor", "Intel Core i7 9700K", "Intelcore", "12345678", "8", "12", "4.9");
        assertTrue(cpu.validate());
    }
    @Test
    public void testThrowsValidationExceptionProcessor() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Processor cpu = new Processor(-3499, "Intelcore good processor", "Intel Core i7-9700K", "Intelcore", "12345678", "8", "12", "4.9");
            assertTrue(cpu.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Processor cpu = new Processor(3499, "Intelcore good processor", "Intel Core i7 9700K", "Intelcore", "12345678", "8", "12", "4.9ghz");
            assertTrue(cpu.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Processor cpu = new Processor(3499, "Intelcore good processor", "Intel Core i7 9700K", "Intelcore", "12345678", "8core", "12", "4.9");
            assertTrue(cpu.validate());
        });
    }
}
