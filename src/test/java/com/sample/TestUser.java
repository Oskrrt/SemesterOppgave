package com.sample;

import static org.junit.jupiter.api.Assertions.*;

import com.sample.Models.Users.User;
import org.junit.jupiter.api.Test;

public class TestUser {

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

    @Test
    public void testValidMail() {
        assertTrue(User.validateMail("ole.Pettersen@hotmail.com"));
        assertTrue(User.validateMail("s333993@oslomet.no"));
        assertTrue(User.validateMail("Per_Hansen@Outlook.com"));
    }

    @Test
    public void testInvalidMail() {
        assertFalse(User.validateMail("#¤%&/()@hotmail.com"));
        assertFalse(User.validateMail("oskar_ruyter@kdkd337373.37ö"));
        assertFalse(User.validateMail("grek.fedro@/(.ë"));
    }

    @Test
    public void testValidPassword() {
        assertTrue(User.validatePassword("HeiHei45"));
        assertTrue(User.validatePassword("DetteErEtPassord00"));
        assertTrue(User.validatePassword("PasswordValid50"));
    }

    @Test
    public void testInvalidAPassword() {
        assertFalse(User.validatePassword("heihei"));
        assertFalse(User.validatePassword("PassordManglerTall"));
        assertFalse(User.validatePassword("passordmanglerstorbokstav400"));
    }
}
