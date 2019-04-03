package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.university.eoapp.R;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.LoginResponse;
import dev.university.eoapp.models.addnewoferrmodel;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewOffer extends AppCompatActivity {


    Button sendBtn;
    EditText title,details;
    String cat_id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_offer);



        cat_id = getIntent().getStringExtra("cat_id");

        details = findViewById(R.id.details);
        title = findViewById(R.id.title);
        sendBtn = findViewById(R.id.buttonsend);




        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){



                    sendtoapi();
                }else{

                    Toast.makeText(AddNewOffer.this,"please title,details",Toast.LENGTH_LONG).show();
                }


            }
        });




    }


    private boolean validate(){


        String ti = title.getText().toString().trim();
        String de = details.getText().toString().trim();


        if(ti.isEmpty() || de.isEmpty()){

            return false;
        }


        return  true;

    }






    private  void  sendtoapi(){

        String titleTxt = title.getText().toString().trim();
        String detailsTxt = details.getText().toString().trim();
        String proivder_id = SharedPrefManager.getInstance(AddNewOffer.this).getUser().getUserId();


        final ProgressDialog progressDialog = new ProgressDialog(AddNewOffer.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();
        Call<addnewoferrmodel> call = RetrofitClient.getInstance().getApi().sendofffer(titleTxt,cat_id,proivder_id,detailsTxt);

        call.enqueue(new Callback<addnewoferrmodel>() {
            @Override
            public void onResponse(Call<addnewoferrmodel> call, Response<addnewoferrmodel> response) {
                progressDialog.dismiss();

                Toast.makeText(AddNewOffer.this, ""+response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

                if(response.body().getResponse()){

                    Intent intent = new Intent(AddNewOffer.this , ProfileActivity.class);
                    startActivity(intent);
                }



            }

            @Override
            public void onFailure(Call<addnewoferrmodel> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
