package logunov.maxim.weatherapp.presentation.screens.main.recycler;

import android.databinding.ObservableField;

import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.presentation.recycler.BaseItemViewModel;

public class RequestItemViewModel extends BaseItemViewModel<WeatherRequest> {

    public ObservableField<String> request = new ObservableField<>("");

    @Override
    public void setItem(WeatherRequest weatherRequest, int position) {
        request.set(weatherRequest.toString());
    }
}
