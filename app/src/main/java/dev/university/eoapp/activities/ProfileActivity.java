package dev.university.eoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import dev.university.eoapp.R;
import dev.university.eoapp.models.data;
import dev.university.eoapp.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {



    Button buttonshop,buttonoffer,buttonaddevent,buttonshow,buttonchat, buttonprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonshop=findViewById(R.id.buttonshop);
        buttonoffer=findViewById(R.id.buttonoffer);
        buttonaddevent=findViewById(R.id.buttonaddevent);
        ImageView imageView =findViewById(R.id.addeventimag);
        buttonshow=findViewById(R.id.buttonshow);
        buttonchat=findViewById(R.id.buttonchat);
        buttonprofile =findViewById(R.id.buttonprofile);

        data user = SharedPrefManager.getInstance(ProfileActivity.this).getUser();

        String type=user.getType();


        if(type.equalsIgnoreCase("provider")) {
            buttonoffer.setText("Add Offer");
            imageView.setVisibility(View.INVISIBLE);
            buttonaddevent.setVisibility(View.INVISIBLE);
        }






        buttonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(ProfileActivity.this,user_profile.class));

            }
        });




        buttonshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity( new Intent(ProfileActivity.this,ShopActivity.class));


            }
        });

        buttonoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                startActivity( new Intent(ProfileActivity.this,OffersActivity.class));


            }
        });



        buttonaddevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity( new Intent(ProfileActivity.this,addevent.class));


            }
        });


        buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity( new Intent(ProfileActivity.this,show_my_events.class));


            }
        });



        buttonchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity( new Intent(ProfileActivity.this,ChatActivity.class));


            }
        });


    }






    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }



}
