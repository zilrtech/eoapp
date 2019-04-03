package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.adapters.chatmessageAdapter;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.LoginResponse;
import dev.university.eoapp.models.Message;
import dev.university.eoapp.models.chat_mesage_response;
import dev.university.eoapp.models.data;
import dev.university.eoapp.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatlistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private chatmessageAdapter adapter;
    private List<Message> events_list;
    String event_id;
    Button buttonsend;
    EditText editTextmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);

        recyclerView = findViewById(R.id.recyclerView);
        buttonsend = findViewById(R.id.buttonsend);
        editTextmessage = findViewById(R.id.editTextmessage);
        final  data user = SharedPrefManager.getInstance(ChatlistActivity.this).getUser();
        getchat();

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isCli;
                if(user.getType().matches("provider")){
                    isCli= "no";

                }else{
                     isCli = "yes";
                }

                sendmessage(editTextmessage.getText().toString(),event_id,isCli);

            }
        });




    }



    private void getchat(){

        recyclerView.setLayoutManager(new LinearLayoutManager(ChatlistActivity.this));
        final ProgressDialog progressDialog = new ProgressDialog(ChatlistActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();


        Bundle extras = getIntent().getExtras();


        event_id = extras.getString("event_id");


        Call<chat_mesage_response> call = RetrofitClient.getInstance().getApi().get_chat_message(event_id);

        call.enqueue(new Callback<chat_mesage_response>() {
            @Override
            public void onResponse(Call<chat_mesage_response> call, Response<chat_mesage_response> response) {
                progressDialog.dismiss();



                events_list = response.body().getMessages();
                adapter = new chatmessageAdapter(ChatlistActivity.this, events_list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<chat_mesage_response> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }


    private void sendmessage(String message, String event__id, String isclient) {

        final ProgressDialog progressDialog = new ProgressDialog(ChatlistActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().sendmessage(message,event__id,isclient);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();


                Toast.makeText(ChatlistActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                getchat();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

}
