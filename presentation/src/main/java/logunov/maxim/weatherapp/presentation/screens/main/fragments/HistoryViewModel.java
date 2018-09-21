package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.domain.usecases.DeleteRequestUseCase;
import logunov.maxim.domain.usecases.GetRequestsUseCase;
import logunov.maxim.domain.usecases.AddRequestUseCase;
import logunov.maxim.weatherapp.app.App;
import logunov.maxim.weatherapp.presentation.base.BaseViewModel;
import logunov.maxim.weatherapp.presentation.recycler.ClickedItemModel;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;
import logunov.maxim.weatherapp.presentation.screens.main.recycler.RequestItemAdapter;

public class HistoryViewModel extends BaseViewModel<MainActivityRouter> {

    public RequestItemAdapter adapter = new RequestItemAdapter();

    @Inject
    public GetRequestsUseCase getRequestsUseCase;

    @Inject
    public AddRequestUseCase insertRequestUseCase;

    @Inject
    public DeleteRequestUseCase deleteRequestUseCase;

    private Consumer<List<WeatherRequest>> doOnNext = new Consumer<List<WeatherRequest>>() {
        @Override
        public void accept(List<WeatherRequest> weatherRequests) throws Exception {
            adapter.setItems(weatherRequests);
        }
    };

    private Consumer<ClickedItemModel> doOnClick = new Consumer<ClickedItemModel>() {
        @Override
        public void accept(ClickedItemModel clickedItemModel) {
            WeatherRequest weatherRequest =
                    (WeatherRequest) clickedItemModel.getEntity();
            router.showDialog(weatherRequest.getWeather());
        }
    };

    private Consumer<ClickedItemModel> doOnLongClick = new Consumer<ClickedItemModel>() {
        @Override
        public void accept(ClickedItemModel clickedItemModel) {
            router.showDeleteDialog((WeatherRequest) clickedItemModel.getEntity());
        }
    };

    public HistoryViewModel() {
        getData();
        observeClicks();
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    private void observeClicks() {
        getCompositeDisposable()
                .add(adapter
                        .observeItemClick()
                        .subscribe(doOnClick, doOnError));
        getCompositeDisposable()
                .add(adapter
                        .observeLongItemClick()
                        .subscribe(doOnLongClick, doOnError));
    }

    private void getData() {
        getCompositeDisposable().add(
                getRequestsUseCase
                        .getRequests()
                        .subscribe(doOnNext, doOnError));

    }

    public void deleteRequest(WeatherRequest request) {
        deleteRequestUseCase.delete(request);
        adapter.removeItem(request);
    }

}
