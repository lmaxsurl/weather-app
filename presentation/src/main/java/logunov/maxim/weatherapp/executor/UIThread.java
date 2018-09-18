package logunov.maxim.weatherapp.executor;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import logunov.maxim.domain.executors.PostExecutionThread;

public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread(){

    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
