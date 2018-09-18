package logunov.maxim.weatherapp.presentation.screens.main.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.databinding.ItemRequestBinding;
import logunov.maxim.weatherapp.presentation.recycler.BaseItemViewHolder;

public class RequestItemViewHolder extends BaseItemViewHolder<WeatherRequest,
        RequestItemViewModel,
        ItemRequestBinding> {

    public RequestItemViewHolder(RequestItemViewModel viewModel, ItemRequestBinding binding) {
        super(viewModel, binding);
    }

    public static RequestItemViewHolder create(ViewGroup parent, RequestItemViewModel viewModel) {
        ItemRequestBinding binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new RequestItemViewHolder(viewModel, binding);
    }
}
