package com.endrjudev.stacksearchapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.endrjudev.stacksearchapp.R;
import com.endrjudev.stacksearchapp.databinding.FragmentStartBinding;
import com.endrjudev.stacksearchapp.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
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
                R.layout.fragment_start,
                container,
                false);

        initializeUi();
        initializeObservers();
        setOnQuerySearchListener();
        setOnSearchClickListener();

        return binding.getRoot();
    }

    private void initializeUi() {
        final Activity tempActivity = getActivity();
        if (tempActivity instanceof MainActivity) {
            activity = (MainActivity) tempActivity;
            viewModel = ViewModelProviders.of(activity).get(MainViewModel.class);

            binding.setViewModel(viewModel);
            binding.searchView.setQueryHint(getString(R.string.empty_query));
            binding.searchView.requestFocus();
            binding.searchCard.setOnClickListener(v -> {
                binding.searchView.setIconified(false);
                binding.searchView.requestFocus();
            });

            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().hide();
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
                activity.getSupportActionBar().setTitle(getString(R.string.search));
            }
        }
    }

    private void initializeObservers() {
        viewModel.getQueryLiveData().observe(this, response -> {
            if (response != null) {
                if (CollectionUtils.isNotEmpty(response.getItems())) {
//                    showProgressBar(false);
                    viewModel.setProgressBarVisible(false);
                    final String query = viewModel.getLastInputQuery();
                    if (StringUtils.isNotBlank(query)) {
                        final StartFragmentDirections.ActionStartFragmentToSearchFragment action =
                                StartFragmentDirections.actionStartFragmentToSearchFragment();
                        action.setQuery(query);
                        if (getView() != null
                                && Navigation.findNavController(getView()).getCurrentDestination() != null
                                && Navigation.findNavController(getView()).getCurrentDestination().getId() == R.id.startFragment) {
                            Navigation.findNavController(getView()).navigate(action);
                        }
                    }
                } else if (response.getItems().isEmpty()) {
//                    showProgressBar(false);
                    viewModel.setProgressBarVisible(false);
                    if (getView() != null) {
                        Snackbar.make(getView(), getString(R.string.empty_list_error), Snackbar.LENGTH_SHORT).show();
                    }
                }
            } else {
//                showProgressBar(false);
                viewModel.setProgressBarVisible(false);
                if (getView() != null) {
                    Snackbar.make(getView(), getString(R.string.network_error), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setOnQuerySearchListener() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (StringUtils.isNotBlank(query)) {
//                    showProgressBar(true);
                    viewModel.setProgressBarVisible(true);
                    viewModel.onSearchButtonClick(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setOnSearchClickListener() {
        binding.searchButton.setOnClickListener(v -> {
            final CharSequence query = binding.searchView.getQuery();
            if (StringUtils.isNotBlank(query.toString())) {
//                showProgressBar(true);
                viewModel.setProgressBarVisible(true);
                binding.searchView.clearFocus();
                viewModel.onSearchButtonClick(query.toString());
            } else {
                if (getView() != null) {
                    Snackbar.make(getView(), getString(R.string.empty_query), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showProgressBar(boolean isVisible) {
        binding.searchButtonText.setVisibility(isVisible ? View.GONE : View.VISIBLE);
        binding.progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
