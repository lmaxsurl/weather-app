package logunov.maxim.weatherapp.presentation.screens.main.recycler;

import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.usecases.DeleteRequestUseCase;
import logunov.maxim.weatherapp.presentation.recycler.BaseItemViewModel;

public class RequestItemViewModel extends BaseItemViewModel<WeatherRequest> {

    public ObservableField<String> request = new ObservableField<>("");

    @Override
    public void setItem(WeatherRequest weatherRequest, int position) {
        request.set(weatherRequest.toString());
    }

}
