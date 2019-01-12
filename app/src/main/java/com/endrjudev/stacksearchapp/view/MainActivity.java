package com.endrjudev.stacksearchapp.view;

import android.os.Bundle;

import com.endrjudev.stacksearchapp.R;
import com.endrjudev.stacksearchapp.databinding.ActivityMainBinding;
import com.endrjudev.stacksearchapp.viewmodel.MainViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


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
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.searchFragment);
        setOnQueryTextListener();
    }

    private void setOnQueryTextListener() {
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.onSearchButtonClick(query);
                binding.search.onWindowFocusChanged(false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
