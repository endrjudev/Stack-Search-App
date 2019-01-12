package com.endrjudev.stacksearchapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchAdapter adapter;
    private MainViewModel viewModel;

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem mSearchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (StringUtils.isNotBlank(query)) {
                    viewModel.onSearchButtonClick(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initializeUi() {
        final Activity activity = getActivity();
        if (activity != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
            adapter = new SearchAdapter();
            binding.queryResultRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.queryResultRecycler.setAdapter(adapter);
        }
        setHasOptionsMenu(true);
    }

    private void initializeObservers() {
        viewModel.getQueryLiveData().observe(this, response -> {
            if (CollectionUtils.isNotEmpty(response.getItems())) {
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
        binding.swipeRefreshLayout.setOnRefreshListener(() ->
                viewModel.getSearchResult());
    }
}
