package com.endrjudev.stacksearchapp.viewmodel;

import com.endrjudev.stacksearchapp.data.NetworkDataSource;
import com.endrjudev.stacksearchapp.data.Repository;
import com.endrjudev.stacksearchapp.model.StackRequest;
import com.endrjudev.stacksearchapp.model.StackResponse;

import org.apache.commons.lang3.StringUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<StackResponse> queryLiveData;
    private String lastInputQuery;
    private Repository dataSource;

    public MainViewModel() {
        this.dataSource = new NetworkDataSource();
        this.queryLiveData = new MutableLiveData<>();
    }

    public void onSearchButtonClick(String query) {
        if (StringUtils.isNotBlank(query)) {
            lastInputQuery = query;
            getSearchResult();
        }
    }

    public void getSearchResult() {
        final StackRequest request = new StackRequest(lastInputQuery);
        dataSource.getSearchResult(request, this.queryLiveData);
    }

    public MutableLiveData<StackResponse> getQueryLiveData() {
        return queryLiveData;
    }
}
