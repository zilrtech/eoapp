package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dev.university.eoapp.R;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.data;
import dev.university.eoapp.models.user_info_response;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class user_profile extends AppCompatActivity {
    TextView first,last,email,phone_number,city,lastname;

    Button logout,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

       first= findViewById(R.id.first);
        last= findViewById(R.id.last);
        email= findViewById(R.id.email);
        phone_number= findViewById(R.id.phone_number);
        city= findViewById(R.id.city);
        lastname= findViewById(R.id.lastname);



        logout= findViewById(R.id.logout);
        update= findViewById(R.id.update);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();

            }
        });





        final ProgressDialog progressDialog = new ProgressDialog(user_profile.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();


        data user = SharedPrefManager.getInstance(user_profile.this).getUser();

        String id=user.getUserId();





        Call<user_info_response> call = RetrofitClient.getInstance().getApi().get_user_info(id
        );

        call.enqueue(new Callback<user_info_response>() {
            @Override
            public void onResponse(Call<user_info_response> call, final Response<user_info_response> response) {
                progressDialog.dismiss();










                data user = SharedPrefManager.getInstance(user_profile.this).getUser();

                String type_=user.getType();


               if(type_.equalsIgnoreCase("provider")){
                   email.setText(response.body().getUser().getEmail());
                   phone_number.setText(response.body().getUser().getPhoneNumber());
                   city.setText(response.body().getUser().getCity());
                   first.setText(response.body().getUser().getName());




                   lastname.setVisibility(View.GONE);
                   last.setVisibility(View.GONE);


                   update.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                           Intent intent = new Intent(user_profile.this, updateActivity.class);

                           intent.putExtra("email",response.body().getUser().getEmail() );
                           intent.putExtra("phone",response.body().getUser().getPhoneNumber() );
                           intent.putExtra("city",response.body().getUser().getCity() );
                           intent.putExtra("username",response.body().getUser().getName() );
                           intent.putExtra("license",response.body().getUser().getLicense() );
                           intent.putExtra("work",response.body().getUser().getWork() );
                           intent.putExtra("city",response.body().getUser().getCity() );

                           startActivity(intent);

                       }
                   });

               }

               else{


                   email.setText(response.body().getUser().getEmail());
                   phone_number.setText(response.body().getUser().getPhoneNumber());
                   city.setText(response.body().getUser().getCity());



                   first.setText(response.body().getUser().getFirstname()+"");
                   last.setText(response.body().getUser().getLasttname()+"");



                   update.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                           Intent intent = new Intent(user_profile.this, updateActivity.class);

                           intent.putExtra("email",response.body().getUser().getEmail() );
                           intent.putExtra("phone",response.body().getUser().getPhoneNumber() );
                           intent.putExtra("city",response.body().getUser().getCity() );
                           intent.putExtra("first",response.body().getUser().getFirstname() );
                           intent.putExtra("last",response.body().getUser().getLasttname() );

                           startActivity(intent);

                       }
                   });






               }










            }

            @Override
            public void onFailure(Call<user_info_response> call, Throwable t) {



                progressDialog.dismiss();

                Log.w("jojo", t.getMessage());



                startActivity( new Intent(user_profile.this,ProfileActivity.class));

            }
        });
    }

    private void logout() {



            SharedPrefManager.getInstance(user_profile.this).clear();
            Intent intent = new Intent(this, beginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

    }
}
