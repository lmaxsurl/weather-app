package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.usecases.GetRequestsUseCase;
import logunov.maxim.domain.usecases.InsertRequestUseCase;
import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.recycler.ClickedItemModel;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;
import logunov.maxim.weatherapp.presentation.screens.main.recycler.RequestItemAdapter;

public class HistoryViewModel extends BaseViewModel<MainActivityRouter> {

    public RequestItemAdapter adapter = new RequestItemAdapter();

    @Inject
    GetRequestsUseCase getRequestsUseCase;

    @Inject
    InsertRequestUseCase insertRequestUseCase;

    public HistoryViewModel() {
        getData();
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    private void getData() {

        getRequestsUseCase
                .getRequests()
                .subscribe(new Observer<List<WeatherRequest>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<WeatherRequest> weatherRequests) {
                        adapter.setItems(weatherRequests);
                    }

                    @Override
                    public void onError(Throwable e) {
                        WeatherRequest weatherRequest = new WeatherRequest(0.0,
                                0.0, "", e.getMessage(), "!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
