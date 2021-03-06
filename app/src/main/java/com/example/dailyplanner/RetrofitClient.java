package com.example.dailyplanner;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://192.168.2.74:4000";
    public static retrofit2.Retrofit retrofit = null;

    /**
     * @return retrofit instance with an connection else return the error if the connection is unsuccessful.
     */
    public static retrofit2.Retrofit getApiClient() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }
}
