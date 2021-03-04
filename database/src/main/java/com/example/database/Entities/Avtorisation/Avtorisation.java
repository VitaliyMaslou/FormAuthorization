package com.example.database.Entities.Avtorisation;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Avtorisation {
    @PrimaryKey
    @NonNull
    String Login;
    String Password;
    Integer Role;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {return Password;}

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer role) {
        Role = role;
    }
}
