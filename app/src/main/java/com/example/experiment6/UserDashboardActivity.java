package com.example.experiment6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.experiment6.ModelClass.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment6.ModelClass.Upload;
import com.example.experiment6.ModelClass.Upload;

public class UserDashboardActivity extends AppCompatActivity {
    //the listview
        ListView listView;

        //database reference to get uploads data
        DatabaseReference mDatabaseReference;

        //list to store uploads data
        List<Upload> uploadList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_uploads);
            uploadList = new ArrayList<>();
            listView = (ListView) findViewById(R.id.listView);


            //adding a clicklistener on listview
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //getting the upload
                    Upload upload = uploadList.get(i);
                    Log.d("info",upload.getUrl());
                    Toast.makeText(com.example.experiment6.UserDashboardActivity.this, "button clicked: "+upload.getUrl(), Toast.LENGTH_SHORT).show();

                    //Opening the upload file in browser using the upload url
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(upload.getUrl()));
//                startActivity(intent);

//                Intent homeIntent = new Intent(UserDashboardActivity.this,ViewPdfActivity.class);
//////                homeIntent.setData(Uri.parse(upload.getUrl()));
//////                String url="url";
//////                homeIntent.putExtra(url,upload);
//////                startActivity(homeIntent);
//////                finish();
                Intent intent = new Intent(getBaseContext(), ViewPdfActivity.class);
                intent.putExtra("URL",upload.getUrl());
                startActivity(intent);
                }
            });
            //getting the database reference
            mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

            //retrieving upload data from firebase database
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }

                    //displaying it to list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(com.example.experiment6.UserDashboardActivity.this, "Upload Cancelled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
