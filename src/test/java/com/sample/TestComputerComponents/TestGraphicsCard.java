package com.sample.TestComputerComponents;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.Fan;
import com.sample.Models.ComputerComponents.GraphicsCard;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGraphicsCard {
    @Test
    public void testValidGraphicsCard() throws ValidationException {
        GraphicsCard gc = new GraphicsCard(8499.69, "High quality gaming big doinks 300fps", "Asus", "Komplett", "12345678", "8", "GDDR6");
        assertTrue(gc.validate());
    }

    @Test
    public void testThrowsValidationExceptionGraphicsCard() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            GraphicsCard gc = new GraphicsCard(-8499.69, "High quality gaming big doinks 300fps", "Asus", "Komplett", "12345678", "8", "GDDR6");
            assertTrue(gc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            GraphicsCard gc = new GraphicsCard(8499.69, "High quality gaming big doinks 300fps", "Asus", "K", "12345678", "8", "GDDR6");
            assertTrue(gc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            GraphicsCard gc = new GraphicsCard(8499.69, "High quality gaming big doinks 300fps", "Asus", "Komplett", "12345678", "8GB", "GDDR6");
            assertTrue(gc.validate());
        });
    }
}

