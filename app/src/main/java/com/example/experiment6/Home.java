package com.example.experiment6;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Home extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            Intent intenter=new Intent(Home.this,PostListActivity.class);
            intenter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intenter);
//            finish();
            if(firebaseUser.getEmail().equals("arpit04199@gmail.com")){
                Intent intent=new Intent(Home.this,PostListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            else {
                Intent intent=new Intent(Home.this,PostListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }

    }
}
