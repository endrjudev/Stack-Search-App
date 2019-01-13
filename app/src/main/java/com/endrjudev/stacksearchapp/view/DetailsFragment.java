package com.endrjudev.stacksearchapp.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.endrjudev.stacksearchapp.R;
import com.endrjudev.stacksearchapp.databinding.FragmentDetailBinding;
import com.endrjudev.stacksearchapp.viewmodel.MainViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class DetailsFragment extends Fragment {

    private FragmentDetailBinding binding;
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
                R.layout.fragment_detail,
                container,
                false);

        initializeUi();

        return binding.getRoot();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initializeUi() {
        final Activity tempActivity = getActivity();
        if (tempActivity instanceof MainActivity) {
            activity = (MainActivity) tempActivity;
        }
        if (getArguments() != null) {
            viewModel = ViewModelProviders.of(activity).get(MainViewModel.class);
            final String targetUrl = DetailsFragmentArgs.fromBundle(getArguments()).getUrl();
            binding.detailWebView.loadUrl(targetUrl);
        }
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            activity.getSupportActionBar().setTitle(getString(R.string.answer_details));
        }
        final WebSettings webSettings = binding.detailWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
