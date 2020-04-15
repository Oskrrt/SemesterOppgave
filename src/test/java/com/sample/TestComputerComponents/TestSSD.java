package com.sample.TestComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.SSD;
import com.sample.Models.ComputerComponents.Speaker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSSD {
    @Test
    public void testValidSSD() throws ValidationException {
        SSD ssd = new SSD(1199, "Fast and big storage", "Samsung big", "Samsung", "12345678", "1000");
        assertTrue(ssd.validate());
    }
    @Test
    public void testThrowsValidationSSD() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            SSD ssd = new SSD(1199, "Fast and big storage", "Samsung big", "Samsung", "12345678", "1000GB");
            assertTrue(ssd.validate());
        });
        assertThrows(ValidationException.class, () -> {
            SSD ssd = new SSD(-1199, "Fast and big storage", "Samsung big", "Samsung", "12345678", "1000");
            assertTrue(ssd.validate());
        });
        assertThrows(ValidationException.class, () -> {
            SSD ssd = new SSD(1199, "Fast and big storage", "Samsung big", "Samsung", "123456789", "1000");
            assertTrue(ssd.validate());
        });
    }
}
