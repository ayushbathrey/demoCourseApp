package com.example.experiment6;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

   // Toolbar toolbar;
    ProgressBar progressBar;

    EditText userEmail;
    EditText userPass;
    Button registerBtn;
    Button loginBtn;

    FirebaseAuth firebaseAuth;
    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar=findViewById(R.id.toolbar);
        progressBar=findViewById(R.id.progressBar);
        userEmail=findViewById(R.id.userEmail);
        userPass=findViewById(R.id.userPass);
        registerBtn=findViewById(R.id.registerBtn);
        loginBtn=findViewById(R.id.loginBtn);

        firebaseAuth=FirebaseAuth.getInstance();

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 1000);


        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
//            Intent intenter=new Intent(MainActivity.this,PostListActivity.class);
//            intenter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intenter);
////            finish();
            if(firebaseUser.getEmail().equals("arpit04199@gmail.com")){
                Intent intent=new Intent(MainActivity.this,PostListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent=new Intent(MainActivity.this,PostListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           // toolbar.setTitle("Register as New User");
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(userEmail.getText().toString(),
                        userPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Registered Successfully",
                                    Toast.LENGTH_SHORT).show();
                            userEmail.setText("");
                            userPass.setText("");
                        }
                        else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}
/***
 *<?xml version="1.0" encoding="utf-8"?>
 * <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 *     xmlns:tools="http://schemas.android.com/tools"
 *     android:layout_width="match_parent"
 *     android:layout_height="match_parent"
 *     android:animateLayoutChanges="true"
 *     android:background="@drawable/grad_bg"
 *     android:orientation="vertical"
 *     tools:context=".MainActivity">
 *
 *     <android.support.v7.widget.Toolbar
 *         android:id="@+id/toolbar"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:background="?attr/colorPrimary"
 *         android:minHeight="?attr/actionBarSize"
 *         android:theme="@style/ThemeOverlay.AppCompat.Dark">
 *
 *         <ProgressBar
 *             android:id="@+id/progressBar"
 *             style="?android:attr/progressBarStyle"
 *             android:layout_width="wrap_content"
 *             android:layout_height="wrap_content"
 *             android:layout_gravity="end"
 *             android:visibility="gone"/>
 *
 *     </android.support.v7.widget.Toolbar>
 *
 *     <EditText
 *         android:id="@+id/userEmail"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:ems="10"
 *         android:hint="@string/Email"
 *         android:inputType="textEmailAddress"
 *         android:textSize="30sp" />
 *
 *     <EditText
 *         android:id="@+id/userPass"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:ems="10"
 *         android:hint="@string/password"
 *         android:inputType="textPassword"
 *         android:textSize="30sp" />
 *
 *     <Button
 *         android:id="@+id/registerBtn"
 *         android:layout_width="match_parent"
 *         android:layout_height="72dp"
 *         android:text="@string/register" />
 *
 *     <TextView
 *         android:id="@+id/textView"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:text="@string/already_have_an_account"
 *         android:textColor="#363636"
 *         android:textSize="18sp" />
 *
 *     <Button
 *         android:id="@+id/loginBtn"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         android:text="@string/login" />
 * </LinearLayout>
 *
 */