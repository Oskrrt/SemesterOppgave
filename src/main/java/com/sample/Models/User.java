package com.sample.Models;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private String mail;
    private String password;
    private boolean adminUser;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = hashPassword(password);
    }
    public String hashPassword(String password) {
        String sha256 = DigestUtils.shaHex(password);
        return sha256;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
