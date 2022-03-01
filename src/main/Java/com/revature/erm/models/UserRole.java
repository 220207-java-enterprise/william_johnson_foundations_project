package com.revature.erm.models;

public class UserRole {
    private String id;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRole(String id, String role){
        this.id = id;
        this.role = role;
    }
}
