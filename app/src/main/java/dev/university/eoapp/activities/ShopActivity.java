package dev.university.eoapp.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.adapters.providersAdapter;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.Provider;
import dev.university.eoapp.models.Provider_response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private providersAdapter adapter;
    private List<Provider> providers_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(ShopActivity.this));
        final ProgressDialog progressDialog = new ProgressDialog(ShopActivity.this);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();
        Call<Provider_response> call = RetrofitClient.getInstance().getApi().getproviders();

        call.enqueue(new Callback<Provider_response>() {
            @Override
            public void onResponse(Call<Provider_response> call, Response<Provider_response> response) {
                progressDialog.dismiss();



                providers_list = response.body().getProviders();
                adapter = new providersAdapter(ShopActivity.this, providers_list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Provider_response> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
