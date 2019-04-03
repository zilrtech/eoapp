package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.adapters.chatlistAdapter;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.Event;
import dev.university.eoapp.models.data;
import dev.university.eoapp.models.myevents_response;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private chatlistAdapter adapter;
    private List<Event> events_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        data user = SharedPrefManager.getInstance(ChatActivity.this).getUser();

        SharedPreferences sharedPreferences = getSharedPreferences("my_shared_preff",MODE_PRIVATE);


        String client_id=user.getUserId();

        Log.i("userssss ",sharedPreferences.getString("id","0"));
        Log.i("Tset ","Tost");


        //Toast.makeText(this,client_id,Toast.LENGTH_LONG).show();



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        final ProgressDialog progressDialog = new ProgressDialog(ChatActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();




        Call<myevents_response> call = RetrofitClient.getInstance().getApi().get_chat(client_id);

        call.enqueue(new Callback<myevents_response>() {
            @Override
            public void onResponse(Call<myevents_response> call, Response<myevents_response> response) {
                progressDialog.dismiss();



                events_list = response.body().getEvents();
                adapter = new chatlistAdapter(ChatActivity.this, events_list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<myevents_response> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
