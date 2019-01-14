package com.endrjudev.stacksearchapp.viewmodel;

import com.endrjudev.stacksearchapp.data.NetworkDataSource;
import com.endrjudev.stacksearchapp.data.Repository;
import com.endrjudev.stacksearchapp.model.StackRequest;
import com.endrjudev.stacksearchapp.model.StackResponse;
import com.endrjudev.stacksearchapp.utils.NetworkWorker;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainViewModel extends ViewModel {

    private static final int REPEAT_INTERVAL = 15; //must be greater than 15 minutes
    private static final String QUERY = "query";

    private ObservableBoolean progressBarVisible;
    private MutableLiveData<StackResponse> queryLiveData;
    private String lastInputQuery;
    private Repository dataSource;
    private PeriodicWorkRequest networkWork;
    private PeriodicWorkRequest.Builder sendQueryBuilder;

    public MainViewModel() {
        this.dataSource = NetworkDataSource.getInstance();
        this.queryLiveData = new MutableLiveData<>();
        this.progressBarVisible = new ObservableBoolean(false);
        this.sendQueryBuilder = new PeriodicWorkRequest.Builder(
                NetworkWorker.class,
                REPEAT_INTERVAL,
                TimeUnit.MINUTES);
        this.networkWork = sendQueryBuilder.build();
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
            WorkManager.getInstance().cancelAllWork();
            sendQueryBuilder.setInputData(createInputData(lastInputQuery));
            WorkManager.getInstance().enqueue(networkWork);
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

    public ObservableBoolean getProgressBarVisible() {
        return progressBarVisible;
    }

    public void setProgressBarVisible(boolean progressBarVisible) {
        this.progressBarVisible.set(progressBarVisible);
    }

    public PeriodicWorkRequest getNetworkWork() {
        return networkWork;
    }

    private Data createInputData(String query) {
        return new Data.Builder().putString(QUERY, query).build();
    }

}
