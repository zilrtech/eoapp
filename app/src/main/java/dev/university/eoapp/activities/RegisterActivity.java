package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.university.eoapp.R;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.LoginResponse;

import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Sign Up Activity
 * */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword,
            editTextName, editTextfirstname,
            editTextlastname,editTexttype,editTextphone,editTextcity,
            editTextlicense,editTextwork,editTextname;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



        Bundle extras = getIntent().getExtras();
         type = extras.getString("type");

         if(type.equalsIgnoreCase("provider")){

             editTextname.setVisibility(View.VISIBLE);
             editTextlicense.setVisibility(View.VISIBLE);
             editTextwork.setVisibility(View.VISIBLE);

         }



        findViewById(R.id.buttonSignUp).setOnClickListener(this);

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

    private void userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstname = editTextfirstname.getText().toString().trim();
        String lasttname = editTextlastname.getText().toString().trim();
        String phone = editTextphone.getText().toString().trim();
        String city = editTextcity.getText().toString().trim();



       // String type = "client";


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

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return;
        }

        if (firstname.isEmpty()) {
            editTextfirstname.setError("Name required");
            editTextfirstname.requestFocus();
            return;
        }

        if (lasttname.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }


        if (phone.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }

        if (type.isEmpty()) {
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
        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .userregister(email, firstname, lasttname,password,phone,city,type);
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();



        call.enqueue(new Callback<LoginResponse>() {



            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {




                if(response.body().getMessage().equalsIgnoreCase("register sccessfuly")){




                    SharedPrefManager.getInstance(RegisterActivity.this)
                            .saveUser(response.body().getData());

                    Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                }

                else{

                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                }


                Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
//
                progressDialog.dismiss();
//                if (response.code() == 201) {
//
//                    LoginResponse dr = response.body();
//                    Toast.makeText(RegisterActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//
//                } else if (response.code() == 422) {
//                    Toast.makeText(RegisterActivity.this, "User already exist", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                progressDialog.dismiss();

            }
        });


    }


    private void providerSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstname = editTextfirstname.getText().toString().trim();
        String lasttname = editTextlastname.getText().toString().trim();
        String phone = editTextphone.getText().toString().trim();
        String city = editTextcity.getText().toString().trim();
        String name = editTextname.getText().toString().trim();
        String license = editTextlicense.getText().toString().trim();
        String work = editTextwork.getText().toString().trim();

        Bundle extras = getIntent().getExtras();
        String type = extras.getString("type");
        // String type = "client";


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

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return;
        }

        if (firstname.isEmpty()) {
            editTextfirstname.setError("Name required");
            editTextfirstname.requestFocus();
            return;
        }

        if (lasttname.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }


        if (phone.isEmpty()) {
            editTextName.setError("field required");
            editTextName.requestFocus();
            return;
        }

        if (type.isEmpty()) {
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
        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .providerregister(email, firstname, lasttname,password,phone,city,type,license,work,name);
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();



        call.enqueue(new Callback<LoginResponse>() {



            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {




                if(response.body().getMessage().equalsIgnoreCase("register sccessfuly")){


                    SharedPrefManager.getInstance(RegisterActivity.this)
                            .saveUser(response.body().getData());

                    Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();




                }

                else{

                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                }


                Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
//
                progressDialog.dismiss();
//                if (response.code() == 201) {
//
//                    LoginResponse dr = response.body();
//                    Toast.makeText(RegisterActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//
//                } else if (response.code() == 422) {
//                    Toast.makeText(RegisterActivity.this, "User already exist", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:

                if(type.equalsIgnoreCase("client")){
                    userSignUp();

                }
                else{

                    providerSignUp();
                }



                break;

        }
    }
}
