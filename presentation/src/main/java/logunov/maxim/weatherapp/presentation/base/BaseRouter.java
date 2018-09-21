package logunov.maxim.weatherapp.presentation.base;

import android.app.AlertDialog;
import android.content.DialogInterface;

import logunov.maxim.domain.entity.AppError;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.R;

import static logunov.maxim.domain.entity.ErrorType.INTERNET_IS_NOT_AVAILABLE;

public abstract class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    private String getErrorMessage(Throwable throwable) {
        if (throwable instanceof AppError) {
            AppError error = (AppError) throwable;
            switch (error.getType()) {
                case INTERNET_IS_NOT_AVAILABLE:
                    return activity.getResources().getString(R.string.error_internet_not_available);
                case SERVER_IS_NOT_AVAILABLE:
                    return activity.getResources().getString(R.string.error_server_not_available);
                case SERVER_ERROR:
                    return activity.getResources().getString(R.string.error_server);
                case UNEXPECTED_ERROR:
                    return activity.getResources().getString(R.string.error);
                case GPS_NOT_AVAILABLE_ERROR:
                    return activity.getResources().getString(R.string.gps_not_available);
                case NO_GPS_PERMISSION_ERROR:
                    return activity.getResources().getString(R.string.no_gps_permission);
                default: {
                    return activity.getResources().getString(R.string.something_wrong);
                }
            }
        } else {
            return activity.getResources().getString(R.string.something_wrong);
        }
    }

    public void showDialog(String message){
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("ОК", null)
                .create()
                .show();
    }

    public void showDialog(int messageId){
        new AlertDialog.Builder(activity)
                .setMessage(messageId)
                .setPositiveButton("ОК", null)
                .create()
                .show();
    }

    public void showError(Throwable throwable){
        new AlertDialog.Builder(activity)
                .setMessage(getErrorMessage(throwable))
                .setPositiveButton("ОК", null)
                .create()
                .show();
    }


}
