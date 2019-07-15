package com.example.experiment6.ModelClass;

import com.google.firebase.database.IgnoreExtraProperties;

public class User {
    public String name;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)

    public User(String name, String url) {
        this.name = name;
    }

    public String getName() {
        return name;

    }
}

