package logunov.maxim.weatherapp.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import logunov.maxim.weatherapp.BR;

public abstract class BaseMvvmActivity<
        VM extends BaseViewModel,
        B extends ViewDataBinding,
        R extends BaseRouter>
        extends BaseActivity {

    protected VM viewModel;
    protected B binding;
    protected R router;

    protected abstract VM provideViewModel();

    protected abstract int provideLayoutId();

    protected abstract R provideRouter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = provideViewModel();

        binding = DataBindingUtil.setContentView(this, provideLayoutId());
        binding.setVariable(BR.viewModel, viewModel);

        router = provideRouter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.addRouter(router);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.removeRouter();
    }
}
