package com.endrjudev.stacksearchapp.view;

import android.os.Bundle;

import com.endrjudev.stacksearchapp.R;
import com.endrjudev.stacksearchapp.databinding.ActivityMainBinding;
import com.endrjudev.stacksearchapp.viewmodel.MainViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.work.WorkManager;


public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "QueryChannel";
    private ActivityMainBinding binding;
    private NavController navController;
    private MainViewModel viewModel;
    private NotificationCompat.Builder notificationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_so_logo)
                .setContentTitle("HELLO THERE")
                .setContentText("Notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        binding.setViewModel(viewModel);
        initializeUi();
        observeWorkManager();
    }

    @Override
    public boolean onSupportNavigateUp() {
        prepareBackToStart();
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        final NavDestination currentDestination = navController.getCurrentDestination();
        if (currentDestination != null) {
            final int destinationId = currentDestination.getId();
            switch (destinationId) {
                case R.id.detailsFragment:
                    super.onBackPressed();
                    break;
                case R.id.searchFragment:
                    clearViewModelData();
                    super.onBackPressed();
                    break;
                case R.id.startFragment:
                default:
                    finish();
            }
        }
    }

    private void initializeUi() {
        setSupportActionBar(binding.toolbar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    private void observeWorkManager() {
        // Experimental - first time with WorkManager :)
        WorkManager.getInstance().getWorkInfoByIdLiveData(viewModel.getNetworkWork().getId()).observe(this, workInfo -> {
            if (workInfo != null) {
                final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(1, notificationBuilder.build());
            }
        });
    }

    private void prepareBackToStart() {
        final NavDestination currentDestination = navController.getCurrentDestination();
        if (currentDestination != null) {
            final int destinationId = currentDestination.getId();
            switch (destinationId) {
                case R.id.detailsFragment:
                    break;
                case R.id.searchFragment:
                    clearViewModelData();
                    break;
                case R.id.startFragment:
                default:
                    finish();
            }
        }
    }

    private void clearViewModelData() {
        viewModel.setLastInputQuery(null);
    }
}
