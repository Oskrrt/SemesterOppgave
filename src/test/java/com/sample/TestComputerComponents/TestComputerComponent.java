package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.ComputerComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestComputerComponent {
    @Test
    public void testValidComputerComponent() throws ValidationException {
        ComputerComponent cc = new ComputerComponent(14999, "High quality", "PCMR", "Komplett", "12345678");
        assertTrue(cc.validate());
    }
    @Test
    public void testThrowsValidationComputerComponent() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            ComputerComponent cc = new ComputerComponent(-14999, "High quality", "PCMR", "Komplett", "12345678");
            assertTrue(cc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            ComputerComponent cc = new ComputerComponent(14999, "High quality", "PCMR", "Komplett", "123456789");
            assertTrue(cc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            ComputerComponent cc = new ComputerComponent(14999, "High quality", "P", "Komplett", "12345678");
            assertTrue(cc.validate());
        });
    }
}
