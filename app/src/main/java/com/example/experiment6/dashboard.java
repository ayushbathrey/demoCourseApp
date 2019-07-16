package com.example.experiment6;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.experiment6.ModelClass.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity {
    ImageButton imageButton,image;
    Button playerbtn;
    FirebaseAuth mAuth;

    ListView listView;

    DatabaseReference mDatabaseReference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //list to store uploads data
    List<Upload> uploadList;
    String userId;
    //ActionBar actionBar = getSupportActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );
        mAuth = FirebaseAuth.getInstance();
        userId = user.getUid();
        Toast.makeText( this, "user: "+userId, Toast.LENGTH_SHORT ).show();
        imageButton = (ImageButton) findViewById( R.id.signOutButtton );
        image =(ImageButton)findViewById(R.id.profile);
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

        Picasso.get().load(user.getPhotoUrl()).fit().into(image);

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //handle other action bar item clicks here
        if (id == R.id.profileId) {
            //display alert dialog to choose sorting
            profile();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it present
        getMenuInflater().inflate(R.menu.profilemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void profile(){
//        Toast.makeText(this, "touched", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(),profile.class);
        Toast.makeText(dashboard.this, "profile", Toast.LENGTH_SHORT).show();
        startActivity(intent);


    }

}