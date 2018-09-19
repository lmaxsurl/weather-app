package logunov.maxim.weatherapp.injection;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import logunov.maxim.device.location.LocationService;
import logunov.maxim.device.repositories.LocationRepositoryImpl;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.LocationRepository;
import logunov.maxim.weatherapp.executor.UIThread;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public PostExecutionThread provideUIThread(UIThread uiThread){
        return uiThread;
    }

}
