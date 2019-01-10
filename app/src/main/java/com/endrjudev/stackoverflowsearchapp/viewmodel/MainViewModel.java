package com.endrjudev.stackoverflowsearchapp.viewmodel;

import com.endrjudev.stackoverflowsearchapp.data.NetworkDataSource;
import com.endrjudev.stackoverflowsearchapp.data.Repository;
import com.endrjudev.stackoverflowsearchapp.model.BaseRequest;
import com.endrjudev.stackoverflowsearchapp.model.BaseResponse;
import com.endrjudev.stackoverflowsearchapp.model.StackResponse;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private Repository dataSource;
    private MutableLiveData<BaseResponse<StackResponse>> responseLiveData;
    private MutableLiveData<String> searchQuery;

    public MainViewModel() {
        this.dataSource = new NetworkDataSource();
        this.responseLiveData = new MutableLiveData<>();
        this.searchQuery = new MutableLiveData<>();
    }

    public void getSearchResultOnClick() {
        getSearchResult();
    }

    private void getSearchResult() {
        final BaseRequest request = new BaseRequest(searchQuery.getValue());
        dataSource.getSearchResult(request, responseLiveData);
    }

    public MutableLiveData<BaseResponse<StackResponse>> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<String> getSearchQuery() {
        return searchQuery;
    }
}
