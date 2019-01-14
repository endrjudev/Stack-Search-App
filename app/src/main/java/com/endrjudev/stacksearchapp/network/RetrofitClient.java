package com.endrjudev.stacksearchapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {

    private static RetrofitClient retrofitClient;

    private Retrofit retrofit;

    private RetrofitClient() {
    }

    public static synchronized RetrofitClient getInstance(String baseUrl) {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        retrofitClient.initRetrofit(baseUrl);
        return retrofitClient;
    }

    private void initRetrofit(String baseUrl) {
        //final LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                //.addNetworkInterceptor(loggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public Retrofit getClient() {
        return retrofit;
    }
}
