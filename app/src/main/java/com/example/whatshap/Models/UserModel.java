package com.example.whatshap.Models;

public class UserModel {
    String Name,Uid,Email;

    public UserModel(String name, String uid, String email) {
        Name = name;
        Uid = uid;
        Email = email;
    }

    public UserModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
