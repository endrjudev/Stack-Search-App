package com.endrjudev.stackoverflowsearchapp.data;

import com.endrjudev.stackoverflowsearchapp.model.BaseRequest;
import com.endrjudev.stackoverflowsearchapp.model.BaseResponse;
import com.endrjudev.stackoverflowsearchapp.model.StackResponse;

import androidx.lifecycle.MutableLiveData;

public interface Repository {
    void getSearchResult(BaseRequest request, final MutableLiveData<BaseResponse<StackResponse>> liveData);
}
