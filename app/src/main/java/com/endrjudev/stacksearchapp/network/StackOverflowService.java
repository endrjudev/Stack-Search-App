package com.endrjudev.stacksearchapp.network;

import com.endrjudev.stacksearchapp.model.StackResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackOverflowService {
    @GET("search/")
    Call<StackResponse> getSearchResults(@Query("intitle") String inTitle,
                                         @Query("site") String site);
}
