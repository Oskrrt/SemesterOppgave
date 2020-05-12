package com.sample.Models.Users;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.Computer.Computer;
import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private String mail;
    private String password;
    private boolean isLoggedIn;
    private boolean isAdmin;
    private Computer computerInProduction;

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

    public Computer getComputerInProduction() {return computerInProduction;}

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {this.isLoggedIn = loggedIn;}

    public void setAdmin(boolean isAdmin) {this.isAdmin = isAdmin;}

    public void setComputerInProduction(Computer computerInProduction) {this.computerInProduction = computerInProduction;}

    public boolean validateUser(String mail, String password, String confirmPassword) throws ValidationException {
        boolean invalidMail = mail.isBlank() || mail.isEmpty() || !validateMail(mail);
        boolean invalidPassword = password.isBlank() || password.isEmpty() || !validatePassword(password);
        boolean invalidConfirmPassword = confirmPassword.isBlank() || confirmPassword.isEmpty() || !validatePassword(password);

        if(invalidMail && invalidMail && invalidConfirmPassword) {
            throw new ValidationException("All of the fields are invalid");
        } else if (invalidMail || invalidPassword || invalidConfirmPassword) {
            throw new ValidationException("One of the fields is invalid");
        } else {
            if (password.equals(confirmPassword)) {
                this.mail = mail.toLowerCase();
                this.password = hashPassword(password);
                this.isAdmin = false;
                return true;
            } else {
                throw new ValidationException("The passwords do not match");
            }
        }
    }

    public static boolean validateMail(String mail) {
        if (mail.length() < 1 || mail.trim().isEmpty() || mail.isBlank()) {
            return false;
        }
        // regex retrieved from https://emailregex.com/
        // added ÆØÅ for norwegian emails
        else return mail.matches("(?:[a-zæøåA-ZÆØÅ0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zæøåA-ZÆØÅ0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zæøåA-ZÆØÅ0-9](?:[a-zæøåA-ZÆØÅ0-9-]*[a-zæøåA-ZÆØÅ0-9])?\\.)+[a-zæøåA-ZÆØÅ0-9](?:[a-zæøåA-ZÆØÅ0-9-]*[a-zæøåA-ZÆØÅ0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zæøåA-ZÆØÅ0-9-]*[a-zæøåA-ZÆØÅ0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public static boolean validatePassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    }
}
