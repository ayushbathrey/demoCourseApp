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

public class LoginActivity extends AppCompatActivity {

   // Toolbar toolbar;
   ProgressBar progressBar;

    EditText userEmail;
    EditText userPass;
    Button loginBtn;
    Button signUpBtn;
    Button forgotPassBtn;

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
        setContentView(R.layout.activity_login);

        //toolbar=findViewById(R.id.toolbar);
        progressBar=findViewById(R.id.progressBar);
        userEmail=findViewById(R.id.userEmail);
        userPass=findViewById(R.id.userPass);
        loginBtn=findViewById(R.id.loginBtn);
        signUpBtn=findViewById(R.id.signUpBtn);
        forgotPassBtn=findViewById(R.id.forgotPassBtn);

        firebaseAuth=FirebaseAuth.getInstance();
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 500);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //toolbar.setTitle("User Login");
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                        userPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                              progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    if(userPass.getText().toString().equals("arpit04")&&
                                    userEmail.getText().toString().equals("arpit040199@gmail.com")){
                                        userEmail.setText("");
                                        userPass.setText("");
                                        startActivity(new Intent(LoginActivity.this,PostListActivity.class));
                                    }
                                    else {
                                        userEmail.setText("");
                                        userPass.setText("");
                                        startActivity(new Intent(LoginActivity.this,
                                                PostListActivity.class));
                                    }
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,
                                            task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

        forgotPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPassActivity.class));
            }
        });

    }
}
/***
 *  <EditText
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
 *         android:textSize="30sp"
 *
 *            <Button
 *         android:id="@+id/loginBtn"
 *         android:layout_width="match_parent"
 *         android:layout_height="72dp"
 *         android:text="@string/login" />
 *
 *     <Button
 *         android:id="@+id/forgotPassBtn"
 *         android:layout_width="match_parent"
 *         android:layout_height="72dp"
 *         android:text="@string/forgot_your_password" />
 *
 *         />
 */
