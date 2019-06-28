package com.example.experiment6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {
    ImageButton imageButton;
    Button playerbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth=FirebaseAuth.getInstance();
        imageButton= (ImageButton)findViewById(R.id.signOutButtton);
        playerbtn= (Button)findViewById(R.id.playerbtn);
//        imageButton =(ImageButton)findViewById(R.id.signOutButtton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                Toast.makeText(dashboard.this, "Sign-out!!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        playerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),VideoActivity.class);
                Toast.makeText(dashboard.this, "Youtube!!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

//    public void signOut(){
//        Intent intent = new Intent(getBaseContext(),LoginActivity.class);
//        Toast.makeText(dashboard.this, "Dashboard", Toast.LENGTH_SHORT).show();
//        startActivity(intent);

}
