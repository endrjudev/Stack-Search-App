package com.endrjudev.stackoverflowsearchapp.view;

import android.os.Bundle;
import android.widget.Toast;

import com.endrjudev.stackoverflowsearchapp.R;
import com.endrjudev.stackoverflowsearchapp.databinding.ActivityMainBinding;
import com.endrjudev.stackoverflowsearchapp.viewmodel.MainViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;


public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initializeUi();
        initializeObservers();
    }

    private void initializeObservers() {
        viewModel.getResponseLiveData()
                .observe(this, stackResponseBaseResponse -> {
                    if (stackResponseBaseResponse != null) {
                        //TODO 10.01.2019 Implement navigation
                    }
                });
    }

    private void initializeUi() {
        binding.searchButton.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Search button click", Toast.LENGTH_SHORT).show());
//                viewModel.getSearchResultOnClick());
    }
}
