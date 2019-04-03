package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import dev.university.eoapp.R;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.LoginResponse;
import dev.university.eoapp.models.data;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Sign Up Activity
 * */

public class updateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword,
            editTextName, editTextfirstname,
            editTextlastname,editTexttype,editTextphone,editTextcity,
            editTextlicense,editTextwork,editTextname;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextfirstname = findViewById(R.id.editTextfirstname);
        editTextlastname = findViewById(R.id.editTexlastname);
        editTexttype = findViewById(R.id.editTexttype);
        editTextphone = findViewById(R.id.editTextphone);
        editTextcity = findViewById(R.id.editTextcity);
        editTextname = findViewById(R.id.editTextname);
        editTextlicense = findViewById(R.id.editTextlicense);
        editTextwork = findViewById(R.id.editTextwork);













        data user = SharedPrefManager.getInstance(updateActivity.this).getUser();

        String type_=user.getType();


         if(type_.equalsIgnoreCase("provider")){

             editTextname.setVisibility(View.GONE);
             editTextlastname.setVisibility(View.GONE);
             editTextlicense.setVisibility(View.VISIBLE);
             editTextwork.setVisibility(View.VISIBLE);

             Bundle extras = getIntent().getExtras();


             editTextEmail.setText(extras.getString("email"));
             editTextphone.setText(extras.getString("phone"));
             editTextfirstname.setText(extras.getString("username"));
             editTextcity.setText(extras.getString("city"));
             editTextlicense.setText(extras.getString("license"));
             editTextwork.setText(extras.getString("work"));

         }
         else{

             editTextname.setVisibility(View.GONE);

             editTextlicense.setVisibility(View.GONE);
             editTextwork.setVisibility(View.GONE);

             Bundle extras = getIntent().getExtras();


             editTextEmail.setText(extras.getString("email"));
             editTextphone.setText(extras.getString("phone"));
             editTextfirstname.setText(extras.getString("first"));
             editTextlastname.setText(extras.getString("last"));
             editTextcity.setText(extras.getString("city"));






         }



        findViewById(R.id.buttonupdate).setOnClickListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
//        if(SharedPrefManager.getInstance(this).isLoggedIn()){
//            Intent intent = new Intent(this, ProfileActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
    }


    private void providerupdate() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextfirstname.getText().toString().trim();
        String phone = editTextphone.getText().toString().trim();
        String city = editTextcity.getText().toString().trim();
        String license = editTextlicense.getText().toString().trim();
        String work = editTextwork.getText().toString().trim();



        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }


        if (name.isEmpty()) {
            editTextfirstname.setError("Name required");
            editTextfirstname.requestFocus();
            return;
        }




        if (phone.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }


        if (city.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }


//        @Field("email") String email,
//        @Field("firstname") String firstname,
//        @Field("lastname") String lastname,
//        @Field("password") String password,
//        @Field("phone_number") String phone_number,
//        @Field("city") String city,
//        @Field("gender_spinner") String gender_spinner


        data user = SharedPrefManager.getInstance(updateActivity.this).getUser();

        String user_id=user.getUserId();


        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .providerupdate(user_id,email, name,password,phone,city,license,work);
        final ProgressDialog progressDialog = new ProgressDialog(updateActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();



        call.enqueue(new Callback<LoginResponse>() {



            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {





                progressDialog.dismiss();
                Toast.makeText(updateActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(updateActivity.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(updateActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }


    private void user_update() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstname = editTextfirstname.getText().toString().trim();
        String lastname = editTextlastname.getText().toString().trim();
        String phone = editTextphone.getText().toString().trim();
        String city = editTextcity.getText().toString().trim();



        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }


        if (firstname.isEmpty()) {
            editTextfirstname.setError("Name required");
            editTextfirstname.requestFocus();
            return;
        }


        if (lastname.isEmpty()) {
            editTextlastname.setError("Name required");
            editTextlastname.requestFocus();
            return;
        }


        if (phone.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }


        if (city.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }





        data user = SharedPrefManager.getInstance(updateActivity.this).getUser();

        String user_id=user.getUserId();


        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .user_update(user_id,email, firstname,lastname,password,phone,city);
        final ProgressDialog progressDialog = new ProgressDialog(updateActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();



        call.enqueue(new Callback<LoginResponse>() {



            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {





                progressDialog.dismiss();
                Toast.makeText(updateActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(updateActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                progressDialog.dismiss();
              //  Log.w("jojo", ""+t.printStackTrace());

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonupdate:

                data user = SharedPrefManager.getInstance(updateActivity.this).getUser();

                String type_=user.getType();



                if(type_.equalsIgnoreCase("client")){
                    user_update();

                }
                else{

                    providerupdate();
                }



                break;

        }
    }
}
