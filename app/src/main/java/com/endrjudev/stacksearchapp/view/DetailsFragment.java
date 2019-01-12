package com.endrjudev.stacksearchapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private void initializeUi() {
        final Activity activity = getActivity();
        if (activity != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        }
    }
}
