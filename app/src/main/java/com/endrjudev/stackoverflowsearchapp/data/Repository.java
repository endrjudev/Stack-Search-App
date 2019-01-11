package com.endrjudev.stackoverflowsearchapp.data;

import com.endrjudev.stackoverflowsearchapp.model.StackRequest;
import com.endrjudev.stackoverflowsearchapp.model.StackResponse;

import androidx.lifecycle.MutableLiveData;

public interface Repository {
    void getSearchResult(StackRequest request, final MutableLiveData<StackResponse> liveData);
}
