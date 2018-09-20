package logunov.maxim.weatherapp.presentation.screens.main.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.Locale;

import logunov.maxim.domain.entity.WeatherRequest;
import logunov.maxim.weatherapp.R;
import logunov.maxim.weatherapp.databinding.HistoryFragmentBinding;
import logunov.maxim.weatherapp.presentation.base.BaseMvvmFragment;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityRouter;
import logunov.maxim.weatherapp.presentation.screens.main.recycler.SimpleDividerItemDecoration;

public class HistoryFragment extends BaseMvvmFragment<
        HistoryViewModel,
        HistoryFragmentBinding,
        MainActivityRouter> {

    @Override
    protected HistoryViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(HistoryViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.history_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        initRecycler();
    }

    private void initRecycler() {
        binding.historyRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.historyRv.setAdapter(viewModel.adapter);
        binding.historyRv.setHasFixedSize(true);
        binding.historyRv.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
    }

    public void deleteRequest(WeatherRequest request){
        viewModel.deleteRequest(request);
    }

}
