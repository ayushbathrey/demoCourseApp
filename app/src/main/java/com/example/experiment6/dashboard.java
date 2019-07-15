package com.example.experiment6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.experiment6.ModelClass.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity {
    ImageButton imageButton;
    Button playerbtn;
    FirebaseAuth mAuth;

    ListView listView;

    DatabaseReference mDatabaseReference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //list to store uploads data
    List<Upload> uploadList;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );
        mAuth = FirebaseAuth.getInstance();
        userId = user.getUid();
        Toast.makeText( this, "user: "+userId, Toast.LENGTH_SHORT ).show();
        imageButton = (ImageButton) findViewById( R.id.signOutButtton );
        playerbtn = (Button) findViewById( R.id.playerbtn );
//        imageButton =(ImageButton)findViewById(R.id.signOutButtton);
        listView = (ListView) findViewById( R.id.listView );
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent( getBaseContext(), LoginActivity.class );
                Toast.makeText( dashboard.this, "Sign-out!!", Toast.LENGTH_SHORT ).show();
//                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory( Intent.CATEGORY_HOME );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                startActivity(homeIntent);
                startActivity( intent );

            }
        } );

        playerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getBaseContext(), VideoActivity.class );
                Toast.makeText( dashboard.this, "Youtube!!", Toast.LENGTH_SHORT ).show();
                startActivity( intent );
            }
        } );

        uploadList = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference( (Constants.DATABASE_USERS_COURSE)+ userId);
        Log.d( "info",mDatabaseReference.toString() );

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue( Upload.class );
                    uploadList.add( upload );

                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get( i ).getName();
                    Log.d( "Link", uploads[i] );
                }


                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>( getApplicationContext(), android.R.layout.simple_list_item_1, uploads );
                listView.setAdapter( adapter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        } );

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setTitle("Really Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        dashboard.super.onBackPressed();
//                    }
//                }).create().show();
//    }


        //    public void signOut(){
//        Intent intent = new Intent(getBaseContext(),LoginActivity.class);
//        Toast.makeText(dashboard.this, "Dashboard", Toast.LENGTH_SHORT).show();
//        startActivity(intent);


    }
}