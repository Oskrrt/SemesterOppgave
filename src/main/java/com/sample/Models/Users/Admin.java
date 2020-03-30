package com.sample.Models.Users;

public class Admin extends User {
    private User decorator;

    public Admin(User decorator){
        this.decorator = decorator;
    }

    @Override
    public String getMail() {
        return decorator.getMail();
    }

    @Override
    public String getPassword() {
        return decorator.getPassword();
    }
}
