package logunov.maxim.weatherapp.injection;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import logunov.maxim.domain.executors.PostExecutionThread;
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
    public static PostExecutionThread provideUIThread(UIThread uiThread){
        return uiThread;
    }

}
