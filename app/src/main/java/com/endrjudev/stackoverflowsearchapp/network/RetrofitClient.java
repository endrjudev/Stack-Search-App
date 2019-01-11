package com.endrjudev.stackoverflowsearchapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {

    private static RetrofitClient ourInstance;

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (ourInstance == null) {
            ourInstance  = new RetrofitClient();
        }
        return ourInstance;
    }

    public Retrofit buildRetrofit(String url) {
        final LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(loggingInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
