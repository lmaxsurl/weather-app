package logunov.maxim.weatherapp.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import logunov.maxim.weatherapp.injection.AppComponent;
import logunov.maxim.weatherapp.injection.AppModule;
import logunov.maxim.weatherapp.injection.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
}
