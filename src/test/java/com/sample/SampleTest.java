package com.sample;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SampleTest {

    // Simple assertion
    @Test
    public void MyVarIsAlwaysTrue() {
        var myVar = true;
        assertTrue(myVar);
    }

    // Assert that an exception is thrown
    @Test
    public void ThrowsOnNullString() {
        String name = null;

        assertThrows(NullPointerException.class, () -> {
            // Will throw a nullpointer exception because 'name' is null
            int nameLength = name.length();

            System.out.println(nameLength);
        });
    }
}
