package com.example.experiment6;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewPdfActivity extends AppCompatActivity {

    TextView pdfName;
    PDFView pdfView;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=database.getReference("upload/url");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfName = (TextView) findViewById(R.id.pdfName);
        Intent intent=getIntent();
        Toast.makeText(this, intent.getStringExtra("url"), Toast.LENGTH_LONG).show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(ViewPdfActivity.this, "Updated:"+value, Toast.LENGTH_LONG).show();
                Log.d("GetingUrlFrmFirebseDB",value);
                String url = pdfName.getText().toString();
                String sessionURL= getIntent().getStringExtra("URL");
                pdfName.setText(sessionURL);
                Log.d("url",url);
                Log.d("sessionURL",sessionURL);
                new RetrivePdfStream().execute(sessionURL);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewPdfActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class RetrivePdfStream extends AsyncTask<String,Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try{
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream=new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }
}
