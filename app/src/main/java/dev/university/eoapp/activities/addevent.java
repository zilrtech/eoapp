package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.LoginResponse;
import dev.university.eoapp.models.data;
import dev.university.eoapp.models.category_response;
import dev.university.eoapp.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Sign Up Activity
 * */

public class addevent extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextname, editTextlocation, editTextidea, editTextguests,editTexttype,editTextbudget,editTextdate,editTextgender;
    Spinner category, gender_spinner;
    private  List<String> cat_names;
    private  List<String> cat_ids;
    private  String cat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_addevent);
        gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        category = (Spinner) findViewById(R.id.category_spinner);

        category = (Spinner) findViewById(R.id.category_spinner);
        editTextname = findViewById(R.id.editTextname);
        editTextlocation = findViewById(R.id.editTextlocation);
        editTextidea = findViewById(R.id.editTextidea);
        editTextguests = findViewById(R.id.editTextguests);
        editTexttype = findViewById(R.id.editTexttype);
        editTextbudget = findViewById(R.id.editTextbudget);
        //editTextgender = findViewById(R.id.editTextgender);
        editTextdate = findViewById(R.id.editTextdate);

        loadSpinnerData();
        findViewById(R.id.buttonSignUp).setOnClickListener(this);

    }


    private void loadSpinnerData() {
        // database handler


        // Spinner Drop down elements



        Call<category_response> call = RetrofitClient.getInstance().getApi().getcategories();

        call.enqueue(new Callback<category_response>() {
            @Override
            public void onResponse(Call<category_response> call, Response<category_response> response) {
             cat_names =new ArrayList<>();
                cat_ids =new ArrayList<>();

                if(response.body().getCategories().size()>=1){
                    for (int i=0;i<response.body().getCategories().size();i++){

                        cat_names.add(response.body().getCategories().get(i).getName().toString());
                        cat_ids.add(response.body().getCategories().get(i).getId().toString());
                        fillspinner();
                    }
                }




            }

            @Override
            public void onFailure(Call<category_response> call, Throwable t) {




            }
        });


    }

    private void fillspinner() {

        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cat_names);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
               cat_id=cat_ids.get(arg2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
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

    private void add_event() {
        String name = editTextname.getText().toString().trim();
        String gender =    gender_spinner.getSelectedItem().toString();
        String idea = editTextidea.getText().toString().trim();
        String guests = editTextguests.getText().toString().trim();
        String type = cat_id;
        String budget = editTextbudget.getText().toString().trim();
        String date = editTextdate.getText().toString().trim();
        String location = editTextlocation.getText().toString().trim();





        if (date.isEmpty()) {
            editTextdate.setError("field required");
            editTextdate.requestFocus();
            return;
        }
        if (budget.isEmpty()) {
            editTextbudget.setError("field required");
            editTextbudget.requestFocus();
            return;
        }
        if (guests.isEmpty()) {
            editTextguests.setError("field required");
            editTextguests.requestFocus();
            return;
        }

//        if (idea.isEmpty()) {
//            editTextidea.setError("field required");
//            editTextidea.requestFocus();
//            return;
//        }

        if (gender.isEmpty()) {
            editTextgender.setError("field required");
            editTextgender.requestFocus();
            return;
        }
//        if (name.isEmpty()) {
//            editTextname.setError("field required");
//            editTextname.requestFocus();
//            return;
//        }





   data user = SharedPrefManager.getInstance(addevent.this).getUser();

        String client_id=user.getUserId();


        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .addevent(client_id, location, idea,guests,type,date,gender,name,budget);
        final ProgressDialog progressDialog = new ProgressDialog(addevent.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();



        call.enqueue(new Callback<LoginResponse>() {



            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                Intent intent = new Intent(addevent.this, ProfileActivity.class);
                startActivity(intent);
                    Toast.makeText(addevent.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();





                Toast.makeText(addevent.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(addevent.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                add_event();
                break;

        }
    }
}
