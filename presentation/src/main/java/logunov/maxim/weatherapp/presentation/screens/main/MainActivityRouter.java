package logunov.maxim.weatherapp.presentation.screens.main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.presentation.base.BaseRouter;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.HistoryFragment;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.LocationFragment;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    private FragNavController fragNavController;
    private LocationFragment locationFragment;
    private HistoryFragment historyFragment;
    private List<Fragment> fragments = new ArrayList<>(2);


    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            fragNavController.switchTab(FragNavController.TAB1);
                            return true;
                        case R.id.navigation_history:
                            fragNavController.switchTab(FragNavController.TAB2);
                            return true;
                    }
                    return false;
                }
            };

    public MainActivityRouter(MainActivity activity) {
        super(activity);
        locationFragment = new LocationFragment();
        locationFragment.setRouter(this);
        historyFragment = new HistoryFragment();
        historyFragment.setRouter(this);
    }

    public void showDialog(String message){
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("ОК", null)
                .create()
                .show();
    }

    public void showDeleteDialog(final WeatherRequest weatherRequest){
        new AlertDialog.Builder(activity)
                .setMessage("Delete this request?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteRequest(weatherRequest);
                    }
                })
                .setNegativeButton("Back", null)
                .create()
                .show();
    }

    public void setBuilder(FragNavController.Builder builder){
        fragments.add(locationFragment);
        fragments.add(historyFragment);
        builder.rootFragments(fragments);
        fragNavController = builder.build();
    }

    private void deleteRequest(WeatherRequest request){
        historyFragment.deleteRequest(request);
    }
}
