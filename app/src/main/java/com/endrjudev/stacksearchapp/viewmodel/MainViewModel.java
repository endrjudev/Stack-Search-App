package com.endrjudev.stacksearchapp.viewmodel;

import com.endrjudev.stacksearchapp.data.NetworkDataSource;
import com.endrjudev.stacksearchapp.data.Repository;
import com.endrjudev.stacksearchapp.model.StackRequest;
import com.endrjudev.stacksearchapp.model.StackResponse;

import org.apache.commons.lang3.StringUtils;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private ObservableBoolean progressBarVisible;
    private MutableLiveData<StackResponse> queryLiveData;
    private String lastInputQuery;
    //TODO 16.01.2019 Use Dagger to DI
    private Repository dataSource;

    public MainViewModel() {
        this.dataSource = new NetworkDataSource();
        this.queryLiveData = new MutableLiveData<>();
        this.progressBarVisible = new ObservableBoolean(false);
    }

    public void onSearchButtonClick(String query) {
        if (StringUtils.isNotBlank(query)) {
            setProgressBarVisible(true);
            setLastInputQuery(query);
            getSearchResult();
        }
    }

    public void getSearchResult() {
        if (StringUtils.isNotBlank(lastInputQuery) && queryLiveData != null) {
            final StackRequest request = new StackRequest(lastInputQuery);
            dataSource.getSearchResult(request, this.queryLiveData);
        }
    }

    public MutableLiveData<StackResponse> getQueryLiveData() {
        return queryLiveData;
    }

    public String getLastInputQuery() {
        return lastInputQuery;
    }

    public void setLastInputQuery(String lastInputQuery) {
        this.lastInputQuery = lastInputQuery;
    }

    public void clearQueryLiveData() {
        //queryLiveData.setValue(null);
    }

    public ObservableBoolean getProgressBarVisible() {
        return progressBarVisible;
    }

    public void setProgressBarVisible(boolean progressBarVisible) {
        this.progressBarVisible.set(progressBarVisible);
    }
}
