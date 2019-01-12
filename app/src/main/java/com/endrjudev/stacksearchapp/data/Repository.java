package com.endrjudev.stacksearchapp.data;

import com.endrjudev.stacksearchapp.model.StackRequest;
import com.endrjudev.stacksearchapp.model.StackResponse;

import androidx.lifecycle.MutableLiveData;

public interface Repository {
    void getSearchResult(StackRequest request, final MutableLiveData<StackResponse> liveData);
}
