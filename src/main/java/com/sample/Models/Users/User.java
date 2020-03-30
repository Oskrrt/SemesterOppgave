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
}
