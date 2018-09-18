package logunov.maxim.weatherapp.presentation.screens.main.recycler;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.presentation.recycler.BaseItemViewHolder;
import logunov.maxim.weatherapp.presentation.recycler.BaseRecyclerViewAdapter;

public class RequestItemAdapter extends BaseRecyclerViewAdapter<WeatherRequest, RequestItemViewModel> {
    @NonNull
    @Override
    public BaseItemViewHolder<WeatherRequest, RequestItemViewModel, ?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RequestItemViewHolder.create(parent, new RequestItemViewModel());
    }
}
