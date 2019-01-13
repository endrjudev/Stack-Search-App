package com.endrjudev.stacksearchapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endrjudev.stacksearchapp.R;
import com.endrjudev.stacksearchapp.databinding.FragmentSearchBinding;
import com.endrjudev.stacksearchapp.view.adapter.SearchAdapter;
import com.endrjudev.stacksearchapp.viewmodel.MainViewModel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchAdapter adapter;
    private MainViewModel viewModel;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_search,
                container,
                false);

        initializeUi();
        initializeObservers();
        initializeSwipeToRefreshListener();

        return binding.getRoot();
    }

    private void initializeUi() {
        final Activity tempActivity = getActivity();
        if (tempActivity instanceof MainActivity) {
            activity = (MainActivity) tempActivity;
        }

        viewModel = ViewModelProviders.of(activity).get(MainViewModel.class);
        adapter = new SearchAdapter();
        binding.queryResultRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.queryResultRecycler.setAdapter(adapter);

        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            activity.getSupportActionBar().setTitle(getString(R.string.query_result, viewModel.getLastInputQuery()));
        }
    }

    private void initializeObservers() {
        viewModel.getQueryLiveData().observe(this, response -> {
            if (response != null && CollectionUtils.isNotEmpty(response.getItems())) {
                adapter.submitList(response.getItems());
                binding.swipeRefreshLayout.setEnabled(true);
                binding.swipeRefreshLayout.setRefreshing(false);
            } else {
                binding.swipeRefreshLayout.setEnabled(false);
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initializeSwipeToRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            if (StringUtils.isNotBlank(viewModel.getLastInputQuery())) {
                viewModel.getSearchResult();
            }
        });
    }
}
