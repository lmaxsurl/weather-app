package logunov.maxim.weatherapp.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public abstract class BaseViewModel<R extends BaseRouter> extends ViewModel {

    private CompositeDisposable compositeDisposable;

    protected R router;

    protected abstract void runInject();

    public ObservableBoolean clickEnable = new ObservableBoolean(true);

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

    protected Consumer<Throwable> doOnError = new Consumer<Throwable>(){

        @Override
        public void accept(Throwable throwable) {
            router.showError(throwable);
            clickEnable.set(true);
        }
    };

    public void onStart(){

    }

    public void onStop(){

    }

    public void onResume(){

    }

    public void onPause(){

    }

}
