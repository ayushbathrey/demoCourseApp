package com.example.experiment6;
import  android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Experiment6 extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
