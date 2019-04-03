package dev.university.eoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dev.university.eoapp.R;
import dev.university.eoapp.storage.SharedPrefManager;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.openreg).setOnClickListener(this);
        findViewById(R.id.openregprovider).setOnClickListener(this);


         }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.openreg:
                startActivity(new Intent(this, RegisterActivity.class).putExtra("type","client"));
                break;

            case R.id.openregprovider:
                startActivity(new Intent(this, RegisterActivity.class).putExtra("type","provider"));
                break;
        }
    }
}
