package dev.university.eoapp.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.Event;
import dev.university.eoapp.models.LoginResponse;
import dev.university.eoapp.models.data;
import dev.university.eoapp.models.myevents_response;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class event_details extends AppCompatActivity {


    private List<Event> events_list;

    private String event_id;

    TextView  name,budget,type,number_of_guest;
    Button buttondelete, button_offer_count,buttonaddoffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);

        name=findViewById(R.id.name);
        budget=findViewById(R.id.budget);
        type=findViewById(R.id.type);
        buttonaddoffer=findViewById(R.id.buttonaddoffer);
        number_of_guest=findViewById(R.id.number_of_guest);

        buttondelete =findViewById(R.id.buttondelete);
        button_offer_count =findViewById(R.id.offer_count);

         Bundle extras = getIntent().getExtras();
        event_id= extras.getString("event_id");


        data user = SharedPrefManager.getInstance(event_details.this).getUser();

        String user_type=user.getType();


        if(user_type.equalsIgnoreCase("provider"))
        {

            buttonaddoffer.setVisibility(View.VISIBLE);


        }

        else{

            button_offer_count.setVisibility(View.VISIBLE);
            buttondelete.setVisibility(View.VISIBLE);

        }





        buttonaddoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LayoutInflater li = LayoutInflater.from(event_details.this);
                View promptsView = li.inflate(R.layout.pop_up_add_offer, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(event_details.this);
                alertDialogBuilder.setView(promptsView);

                final EditText price = (EditText) promptsView.findViewById(R.id.editTextprice);


//                title.setText(request.getTitle());
//                provider_id.setText(request.getProviderId());
//                comments.setText(request.getComments());


                data user = SharedPrefManager.getInstance(event_details.this).getUser();

                final String provider_id=user.getUserId();





                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Send",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        final String price_ =price.getText().toString();







                                        final ProgressDialog progressDialog = new ProgressDialog(event_details.this);
                                        progressDialog.setMessage("Loading ..");
                                        progressDialog.show();
                                        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().addtoffer(event_id,provider_id,price_);

                                        call.enqueue(new Callback<LoginResponse>() {
                                            @Override
                                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                                progressDialog.dismiss();


                                                Toast.makeText(event_details.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                                progressDialog.dismiss();

                                            }
                                        });

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



            }
        });


        final ProgressDialog progressDialog = new ProgressDialog(event_details.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();






        Call<myevents_response> call = RetrofitClient.getInstance().getApi().get_event_details(event_id
        );

        call.enqueue(new Callback<myevents_response>() {
            @Override
            public void onResponse(Call<myevents_response> call, final Response<myevents_response> response) {
                progressDialog.dismiss();


                  name.setText("Name :"+response.body().getEvent().getName());
                type.setText("Party Type :"+response.body().getEvent().getPartyType());
                number_of_guest.setText("Number of Guest :"+response.body().getEvent().getNumberOfGuest());
                budget.setText("Budget :"+response.body().getEvent().getBudget());

                button_offer_count.setText("You have "+response.body().getEvent().getOffer_count()+" Offer");

                button_offer_count.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity( new Intent(event_details.this,event_offersActivity.class).putExtra("event_id",  response.body().getEvent().getId()));



                    }
                });

                buttondelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                         deleteoffer(response.body().getEvent().getId());


                    }
                });

            }

            @Override
            public void onFailure(Call<myevents_response> call, Throwable t) {
                progressDialog.dismiss();




            }
        });
    }

    private void deleteoffer(String id) {

        final ProgressDialog progressDialog = new ProgressDialog(event_details.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();






        Call<myevents_response> call = RetrofitClient.getInstance().getApi().delete_offer(id
        );

        call.enqueue(new Callback<myevents_response>() {
            @Override
            public void onResponse(Call<myevents_response> call, final Response<myevents_response> response) {
                progressDialog.dismiss();

                Toast.makeText(event_details.this, "Deleted ", Toast.LENGTH_SHORT).show();


                startActivity( new Intent(event_details.this,ProfileActivity.class));



            }

            @Override
            public void onFailure(Call<myevents_response> call, Throwable t) {



                progressDialog.dismiss();

                Toast.makeText(event_details.this, "Deleted ", Toast.LENGTH_SHORT).show();


                startActivity( new Intent(event_details.this,ProfileActivity.class));

            }
        });
    }


}