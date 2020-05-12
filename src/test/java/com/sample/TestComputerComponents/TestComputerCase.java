package com.sample.TestComputerComponents;
import static org.junit.jupiter.api.Assertions.*;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.Case;
import org.junit.jupiter.api.Test;

class TestComputerCase {
    @Test
    public void testValidComputerCase() throws ValidationException {
        Case c = new Case("3", "4", "40", "50", "45", 500.00, "A nice case", "Fractal design s2", "Komplett", "12345678");
        assertTrue(c.validate());
    }

    @Test
    public void testThrowsValidationExceptionComputerCase() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Case c = new Case("3", "3", "40", "50", "45", 500.0, "A nice case", "Nice", "Komplett", "1234567891011");
            assertTrue(c.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Case c = new Case("3", "3", "40", "50", "45", -500.0, "A nice case", "Nice", "Komplett", "12345678");
            assertTrue(c.validate());
        });
        assertThrows(ValidationException.class, () -> {
            Case c = new Case("3", "3", "40", "50", "45", 500.0, "A nice case", "Nice", "K", "12345678");
            assertTrue(c.validate());
        });
    }
}



