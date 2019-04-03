package dev.university.eoapp.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static final String BASE_URL = "http://178.128.149.244/apps/eoapp/";
//    private static final String BASE_URL = "http://192.168.0.104/eoapp/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
              .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
