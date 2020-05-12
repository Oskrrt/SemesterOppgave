package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.HDD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHDD {
    @Test
    public void testValidHDD() throws ValidationException {
        HDD hdd = new HDD(900, "1TB storage", "Seagate SkyHawk", "Seagate", "12345678", "500");
        assertTrue(hdd.validate());
    }
    @Test
    public void testThrowsValidationExceptionHDD() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            HDD hdd = new HDD(-900, "1TB storage", "Seagate SkyHawk", "Seagate", "12345678", "500");
            assertTrue(hdd.validate());
        });
        assertThrows(ValidationException.class, () -> {
            HDD hdd = new HDD(900, "1TB storage", "Seagate SkyHawk", "Seagate", "12345678", "500GB");
            assertTrue(hdd.validate());
        });
        assertThrows(ValidationException.class, () -> {
            HDD hdd = new HDD(900, "1TB storage", "Seagate SkyHawk", "S", "12345678", "500");
            assertTrue(hdd.validate());
        });
    }
}

