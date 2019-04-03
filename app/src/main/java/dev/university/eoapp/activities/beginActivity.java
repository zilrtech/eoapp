package dev.university.eoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dev.university.eoapp.R;
import dev.university.eoapp.storage.SharedPrefManager;

public class beginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        findViewById(R.id.buttonopenlogin).setOnClickListener(this);
        findViewById(R.id.buttonopenreg).setOnClickListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();


        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonopenlogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.buttonopenreg:
                startActivity(new Intent(this, HomeActivity.class));
                break;


        }
    }
}
