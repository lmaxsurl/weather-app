package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.recycler.ClickedItemModel;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;
import logunov.maxim.weatherapp.presentation.screens.main.recycler.RequestItemAdapter;

public class HistoryViewModel extends BaseViewModel<MainActivityRouter> {

    public RequestItemAdapter adapter = new RequestItemAdapter();

    public HistoryViewModel() {
        getData();
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void getData() {
        List<WeatherRequest> data = new ArrayList<>();
        data.add(new WeatherRequest(53, 24, "Minsk", "+24", new Date()));
        data.add(new WeatherRequest(25, 17, "NY", "+24", new Date()));
        data.add(new WeatherRequest(16, -9, "Sydney", "+24", new Date()));
        data.add(new WeatherRequest(-212, 120, "Minsk", "+24", new Date()));
        data.add(new WeatherRequest(32, 54, "Minsk", "+24", new Date()));
        adapter.setItems(data);
    }
}
