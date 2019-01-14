package com.endrjudev.stacksearchapp.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
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
    private int id = 1;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final int importance = NotificationManager.IMPORTANCE_DEFAULT;
            final NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance);
            channel.setDescription(CHANNEL_ID);

            final NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

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
                id++;
                notificationManager.notify(id, notificationBuilder.build());
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
