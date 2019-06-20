package com.example.experiment6;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;

    EditText userEmail;
    Button forgotPassBtn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        toolbar=findViewById(R.id.toolbar);
        progressBar=findViewById(R.id.progressBar);
        userEmail=findViewById(R.id.userEmail);
        forgotPassBtn=findViewById(R.id.forgotPassBtn);

        toolbar.setTitle("Forgot My Password");

        firebaseAuth=FirebaseAuth.getInstance();

        forgotPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            userEmail.setText("");
                            Toast.makeText(ForgotPassActivity.this,"A verification link is sent to your email address",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(ForgotPassActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
