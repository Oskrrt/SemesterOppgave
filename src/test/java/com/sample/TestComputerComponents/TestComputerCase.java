package com.sample.TestComputerComponents;
import static org.junit.jupiter.api.Assertions.*;

import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.Case;
import org.junit.jupiter.api.Test;

public class TestComputerCase {
    @Test
    public void testValidComputerCase() throws ValidationException {
        Case c = new Case("3", "4", "40", "50", "45", 500.00, "A nice case", "Nice", "Komplett", "12334567");
        assertTrue(c.validate());
    }

    @Test
    public void testThrowsValidationExceptionComputerCase() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            Case c = new Case("3", "3", "40", "50", "45", 500.0, "A nice case", "Nice", "Komplett", "1233333334567");
            assertTrue(c.validate());
        });
    }
}
