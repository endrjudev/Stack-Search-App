package com.endrjudev.stackoverflowsearchapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endrjudev.stackoverflowsearchapp.R;
import com.endrjudev.stackoverflowsearchapp.databinding.FragmentSearchBinding;
import com.endrjudev.stackoverflowsearchapp.utils.SystemUtils;
import com.endrjudev.stackoverflowsearchapp.view.adapter.SearchAdapter;
import com.endrjudev.stackoverflowsearchapp.viewmodel.MainViewModel;

import org.apache.commons.collections4.CollectionUtils;

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

        return binding.getRoot();
    }

    private void initializeUi() {
        final Activity activity = getActivity();
        if (activity != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
            adapter = new SearchAdapter();
            binding.queryResultRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.queryResultRecycler.setAdapter(adapter);
        }
    }

    private void initializeObservers(){
        viewModel.getResponseLiveData().observe(this, response -> {
            if (SystemUtils.isResponseOK(response)
                    && CollectionUtils.isNotEmpty(response.getResponse().getItems())) {
                adapter.setItems(response.getResponse().getItems());
            }
        });
    }
}
