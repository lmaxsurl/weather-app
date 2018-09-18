package logunov.maxim.weatherapp.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import logunov.maxim.weatherapp.BR;

public abstract class BaseMvvmFragment<
        VM extends BaseViewModel,
        B extends ViewDataBinding,
        R extends BaseRouter>
        extends BaseFragment {

    protected VM viewModel;
    protected B binding;
    protected R router;

    protected abstract VM provideViewModel();

    protected abstract int provideLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewModel = provideViewModel();

        binding = DataBindingUtil.inflate(getLayoutInflater(), provideLayoutId(), container, false);
        binding.setVariable(BR.viewModel, viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setRouter(R router){
        this.router = router;
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.addRouter(router);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.removeRouter();
    }

}
