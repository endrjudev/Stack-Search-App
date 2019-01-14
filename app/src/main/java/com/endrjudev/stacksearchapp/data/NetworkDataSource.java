package com.endrjudev.stacksearchapp.data;

import com.endrjudev.stacksearchapp.model.StackRequest;
import com.endrjudev.stacksearchapp.model.StackResponse;
import com.endrjudev.stacksearchapp.network.RetrofitClient;
import com.endrjudev.stacksearchapp.network.StackOverflowService;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class NetworkDataSource implements Repository {

    private static final String URL = "http://api.stackexchange.com/2.2/";
    private static final String STACK_OVERFLOW = "stackoverflow";

    private static NetworkDataSource instance;

    private StackOverflowService stackOverflowService;

    public static NetworkDataSource getInstance() {
        if (instance == null) {
            instance = new NetworkDataSource();
        }
        instance.buildNetworkDataSource();
        return instance;
    }

    private void buildNetworkDataSource() {
        final Retrofit retrofit = RetrofitClient.getInstance(URL).getClient();
        stackOverflowService = retrofit.create(StackOverflowService.class);
    }

    private NetworkDataSource() {
    }

    @Override
    public void getSearchResult(StackRequest request, final MutableLiveData<StackResponse> liveData) {
        final Call<StackResponse> call = stackOverflowService.getSearchResults(
                request.getInTitle(),
                STACK_OVERFLOW);
        call.enqueue(new Callback<StackResponse>() {
            @Override
            public void onResponse(@NonNull Call<StackResponse> call,@NonNull Response<StackResponse> response) {
                if (response.isSuccessful() && liveData != null) {
                    liveData.setValue(response.body());
                } else {
                    Timber.e(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<StackResponse> call,@NonNull Throwable t) {
                Timber.e(t);
            }
        });
    }
}
