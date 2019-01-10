package com.endrjudev.stackoverflowsearchapp.data;

import com.endrjudev.stackoverflowsearchapp.model.BaseRequest;
import com.endrjudev.stackoverflowsearchapp.model.BaseResponse;
import com.endrjudev.stackoverflowsearchapp.model.StackResponse;
import com.endrjudev.stackoverflowsearchapp.network.RetrofitClient;
import com.endrjudev.stackoverflowsearchapp.network.StackOverflowService;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class NetworkDataSource implements Repository {

    private static final String URL = "https://stackoverflow.com/";
    private static final String NETWORK_ERROR = "Network Error";

    private Retrofit retrofit = RetrofitClient.getInstance()
            .buildRetrofit(URL);
    private StackOverflowService stackOverflowService = retrofit.create(StackOverflowService.class);

    @Override
    public void getSearchResult(BaseRequest request, final MutableLiveData<BaseResponse<StackResponse>> liveData) {
        final Call<BaseResponse<StackResponse>> call = stackOverflowService.getSearchResults(request.getInTitle());
        call.enqueue(new Callback<BaseResponse<StackResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<StackResponse>> call, Response<BaseResponse<StackResponse>> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    Timber.e(NETWORK_ERROR);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<StackResponse>> call, Throwable t) {
                Timber.e(t);
            }
        });
    }
}
