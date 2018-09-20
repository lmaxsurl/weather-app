package logunov.maxim.weatherapp.presentation.screens.main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.presentation.base.BaseRouter;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    public MainActivityRouter(MainActivity activity) {
        super(activity);
    }


    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
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
                        activity.deleteRequest(weatherRequest);
                    }
                })
                .setNegativeButton("Back", null)
                .create()
                .show();
    }
}
