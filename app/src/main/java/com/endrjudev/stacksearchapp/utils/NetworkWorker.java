package com.endrjudev.stacksearchapp.utils;

import android.content.Context;

import com.endrjudev.stacksearchapp.data.NetworkDataSource;
import com.endrjudev.stacksearchapp.data.Repository;
import com.endrjudev.stacksearchapp.model.StackRequest;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NetworkWorker extends Worker {

    private static final String QUERY = "query";

    public NetworkWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        final Repository dataSource = NetworkDataSource.getInstance();
        final String lastQuery = getInputData().getString(QUERY);
        dataSource.getSearchResult(new StackRequest(lastQuery), null);
        return Result.success();
    }
}
