package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.adapters.myeventsAdapter;
import dev.university.eoapp.adapters.offersAdapter;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.Event;
import dev.university.eoapp.models.Offer;
import dev.university.eoapp.models.data;
import dev.university.eoapp.models.myevents_response;
import dev.university.eoapp.models.offers_response;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class category_offers extends AppCompatActivity {




    private RecyclerView recyclerView;
    private offersAdapter offersAdapter;
    private myeventsAdapter myeventsAdapter;
    private List<Offer> offers_list;
    private List<Event> events_list;
    public  String cat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_offers);


        Bundle extras = getIntent().getExtras();
       cat_id= extras.getString("cat_id");
        data user = SharedPrefManager.getInstance(category_offers.this).getUser();

        String type=user.getType();

        recyclerView = findViewById(R.id.recyclerView);

        if(type.equalsIgnoreCase("provider"))
            recyclerView.setLayoutManager(new GridLayoutManager(category_offers.this,2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(category_offers.this,1));





        if(type.equalsIgnoreCase("provider"))
            get_category_events();
        else
            get_category_offers();

    }


   private void  get_category_offers(){

        final ProgressDialog progressDialog = new ProgressDialog(category_offers.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();
        Call<offers_response> call = RetrofitClient.getInstance().getApi().getoffes(cat_id);

        call.enqueue(new Callback<offers_response>() {
            @Override
            public void onResponse(Call<offers_response> call, Response<offers_response> response) {
                progressDialog.dismiss();



                offers_list = response.body().getOffers();
                offersAdapter = new offersAdapter(category_offers.this, offers_list);
                recyclerView.setAdapter(offersAdapter);
            }

            @Override
            public void onFailure(Call<offers_response> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }

    private void  get_category_events(){


        final ProgressDialog progressDialog = new ProgressDialog(category_offers.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();
        Call<myevents_response> call = RetrofitClient.getInstance().getApi().get_cat_events(cat_id);

        call.enqueue(new Callback<myevents_response>() {
            @Override
            public void onResponse(Call<myevents_response> call, Response<myevents_response> response) {
                progressDialog.dismiss();



                events_list = response.body().getEvents();
                myeventsAdapter = new myeventsAdapter(category_offers.this, events_list);
                recyclerView.setAdapter(myeventsAdapter);
            }

            @Override
            public void onFailure(Call<myevents_response> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }
}
