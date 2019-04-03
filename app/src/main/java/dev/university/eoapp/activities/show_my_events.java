package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.adapters.myeventsAdapter;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.Event;
import dev.university.eoapp.models.data;
import dev.university.eoapp.models.myevents_response;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class show_my_events extends AppCompatActivity {

    private RecyclerView recyclerView;
    private myeventsAdapter adapter;
    private List<Event> events_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_events);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(show_my_events.this,2));
        final ProgressDialog progressDialog = new ProgressDialog(show_my_events.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();


        data user = SharedPrefManager.getInstance(show_my_events.this).getUser();

        String client_id=user.getUserId();
        Call<myevents_response> call = RetrofitClient.getInstance().getApi().get_my_events(client_id);

        call.enqueue(new Callback<myevents_response>() {
            @Override
            public void onResponse(Call<myevents_response> call, Response<myevents_response> response) {
                progressDialog.dismiss();



                events_list = response.body().getEvents();
                adapter = new myeventsAdapter(show_my_events.this, events_list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<myevents_response> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
