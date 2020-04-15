package com.sample.TestComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.Keyboard;
import com.sample.Models.ComputerComponents.Monitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMonitor {
    @Test
    public void testValidMonitor() throws ValidationException {
        Monitor m = new Monitor(1500, "144hz monitor with tilt", "MSI Optix 24", "MSI", "12345678", "LED", "24", "1080p", "HDMI");
        assertTrue(m.validate());

    }
    @Test
    public void testThrowsValidationExceptionMonitor() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Monitor m = new Monitor(1500, "144hz monitor with tilt", "MSI Optix 24", "MSI", "12345678", "QLED", "24", "1080p", "HDMI");
            assertTrue(m.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Monitor m = new Monitor(1500, "144hz monitor with tilt", "MSI Optix 24", "MSI", "12345678", "LED", "24", "1080p", "USB");
            assertTrue(m.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Monitor m = new Monitor(-1500, "144hz monitor with tilt", "MSI Optix 24", "MSI", "12345678", "LED", "24", "1080p", "HDMI");
            assertTrue(m.validate());
        });
    }
}

