package com.sample.Models.Users;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private String mail;
    private String password;
    private boolean isLoggedIn;
    private boolean isAdmin;

    /*public User(String mail, String password) {
        this.mail = mail.toLowerCase();
        this.password = password;
    }*/
    public String hashPassword(String password) {
        return DigestUtils.shaHex(password);
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLoggedIn() {return isLoggedIn;}

    public boolean getAdmin() {return isAdmin;}

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {this.isLoggedIn = loggedIn;}

    public void setAdmin(boolean isAdmin) {this.isAdmin = isAdmin;}

    public boolean validateUser(String mail, String password, String confirmPassword) {
        boolean invalidMail = mail.isBlank() || mail.isEmpty();
        boolean invalidPassword = password.isBlank() || password.isEmpty();
        boolean invalidConfirmPassword = confirmPassword.isBlank() || confirmPassword.isEmpty();
        if (invalidMail || invalidPassword || invalidConfirmPassword) {
            return false;
        } else if(invalidMail && invalidMail && invalidConfirmPassword) {
            return false;
        } else {
            if (password.equals(confirmPassword)) {
                this.mail = mail.toLowerCase();
                this.password = hashPassword(password);
                this.isAdmin = false;
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean validateMail(String mail) {

        if (mail.length() < 1 || mail.trim().isEmpty() || mail.isBlank()) {
            return false;
        }
        else if (!mail.matches("(?:[a-zæøåA-ZÆØÅ0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zæøåA-ZÆØÅ0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zæøåA-ZÆØÅ0-9](?:[a-zæøåA-ZÆØÅ0-9-]*[a-zæøåA-ZÆØÅ0-9])?\\.)+[a-zæøåA-ZÆØÅ0-9](?:[a-zæøåA-ZÆØÅ0-9-]*[a-zæøåA-ZÆØÅ0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zæøåA-ZÆØÅ0-9-]*[a-zæøåA-ZÆØÅ0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            return false;
        } else {
            return true;
        }
    }
}
