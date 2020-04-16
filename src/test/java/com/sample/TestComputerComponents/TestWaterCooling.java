package com.sample.TestComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.StorageComponent;
import com.sample.Models.ComputerComponents.WaterCooling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWaterCooling {
    @Test
    public void testValidWaterCooling() throws ValidationException {
        WaterCooling wc = new WaterCooling("3", "10", 499.99, "RGB water cooling", "WC komplett edition","Komplett", "12345678");
        assertTrue(wc.validate());
    }
    @Test
    public void testThrowsValidationWaterCooling() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            WaterCooling wc = new WaterCooling("3", "10", -499.99, "RGB water cooling", "WC komplett edition","Komplett", "12345678");
            assertTrue(wc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            WaterCooling wc = new WaterCooling("3", "10", 499.99, "RGB", "WC komplett edition","Komplett", "12345678");
            assertTrue(wc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            WaterCooling wc = new WaterCooling("3", "10", 499.99, "RGB water cooling", "WC komplett edition","Komplett", "123456789");
            assertTrue(wc.validate());
        });
    }
}
