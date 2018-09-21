package logunov.maxim.weatherapp.presentation.base;

import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment{

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable getCompositeDisposable() {
        if(compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

}
