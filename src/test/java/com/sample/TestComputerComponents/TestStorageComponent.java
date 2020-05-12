package com.sample.TestComputerComponents;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.StorageComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStorageComponent {
    @Test
    public void testValidStorageComponent() throws ValidationException {
        StorageComponent sc = new StorageComponent(999, "Big storage", "Kingston", "Samsung", "12345678", "1000");
        assertTrue(sc.validate());
    }
    @Test
    public void testThrowsValidationStorageComponent() throws ValidationException {
        assertThrows(ValidationException.class, () -> {
            StorageComponent sc = new StorageComponent(999, "Big storage", "Kingston", "Samsung", "123456789", "1000");
            assertTrue(sc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            StorageComponent sc = new StorageComponent(999, "Big storage", "Kingston", "Samsung", "12345678", "1000GB");
            assertTrue(sc.validate());
        });
        assertThrows(ValidationException.class, () -> {
            StorageComponent sc = new StorageComponent(999, "Big storage", "K", "Samsung", "12345678", "1000");
            assertTrue(sc.validate());
        });
    }
}
