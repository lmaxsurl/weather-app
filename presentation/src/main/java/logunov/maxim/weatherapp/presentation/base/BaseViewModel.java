package logunov.maxim.weatherapp.presentation.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<R extends BaseRouter> extends ViewModel {

    private CompositeDisposable compositeDisposable;

    protected R router;

    protected abstract void runInject();

    public BaseViewModel() {
        runInject();
    }

    public void addRouter(R router) {
        this.router = router;
    }

    public void removeRouter() {
        router = null;
    }

    protected CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

}
