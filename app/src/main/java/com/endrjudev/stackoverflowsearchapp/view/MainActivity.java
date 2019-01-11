package com.endrjudev.stackoverflowsearchapp.view;

import android.os.Bundle;

import com.endrjudev.stackoverflowsearchapp.R;
import com.endrjudev.stackoverflowsearchapp.databinding.ActivityMainBinding;
import com.endrjudev.stackoverflowsearchapp.view.adapter.SearchAdapter;
import com.endrjudev.stackoverflowsearchapp.viewmodel.MainViewModel;

import org.apache.commons.collections4.CollectionUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initializeObservers();
        initializeUi();
    }

    private void initializeObservers() {
        //observe
    }

    private void initializeUi() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SearchFragment())
                .commit();
    }
}
