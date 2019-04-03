package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.adapters.event_offersAdapter;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.Offer;
import dev.university.eoapp.models.offers_response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class event_offersActivity extends AppCompatActivity {




    private RecyclerView recyclerView;
    private event_offersAdapter adapter;
    private List<Offer> offers_list;
    public  String event_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_offers);


       Bundle extras = getIntent().getExtras();
        event_id = extras.getString("event_id");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(event_offersActivity.this));
        final ProgressDialog progressDialog = new ProgressDialog(event_offersActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();
        Call<offers_response> call = RetrofitClient.getInstance().getApi().get_event_offes(event_id);

        call.enqueue(new Callback<offers_response>() {
            @Override
            public void onResponse(Call<offers_response> call, Response<offers_response> response) {
                progressDialog.dismiss();



                offers_list = response.body().getOffers();
                adapter = new event_offersAdapter(event_offersActivity.this, offers_list,event_id);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<offers_response> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
