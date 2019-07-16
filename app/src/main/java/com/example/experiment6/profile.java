package com.example.experiment6;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    TextView name;
    TextView course;
    TextView email;
    TextView contact;
    TextView occupation;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        name = findViewById( R.id.name );
        course = findViewById( R.id.course );
        email = findViewById( R.id.email);
        contact= findViewById( R.id.contact);
        occupation = findViewById( R.id.occupation );
        image = findViewById(R.id.profile_image);
        Picasso.get().load(user.getPhotoUrl()).fit().into(image, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap imageBitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                imageDrawable.setCircular(true);
                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                image.setImageDrawable(imageDrawable);
            }
            @Override
            public void onError(Exception e) {
                image.setImageResource(R.drawable.common_google_signin_btn_icon_dark_focused);

            }
        });
        name.setText( user.getDisplayName() );
        course.setText( (user.getPhoneNumber()));
        email.setText( user.getEmail() );
        contact.setText(user.getPhoneNumber() );
        Log.d( "url", String.valueOf( user.getPhotoUrl() ) );
        Log.d( "no", String.valueOf( user.getPhoneNumber() ) );
        occupation.setText( user.getProviderId());
    }
}
