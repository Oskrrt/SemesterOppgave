package com.sample.Models.Users;

public class Admin extends User {
    private boolean hasAdminPowers;

    public Admin(boolean hasAdminPowers){
        this.hasAdminPowers = hasAdminPowers;
    }
}
