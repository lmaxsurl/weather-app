package logunov.maxim.weatherapp.injection;

import javax.inject.Singleton;

import dagger.Component;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.HistoryViewModel;
import logunov.maxim.weatherapp.presentation.screens.main.fragments.LocationViewModel;
import logunov.maxim.weatherapp.presentation.screens.main.MainActivityViewModel;

@Component(modules = {AppModule.class, LocationModule.class, WeatherModule.class})
@Singleton
public interface AppComponent {
    void runInject(MainActivityViewModel mainActivityViewModel);
    void runInject(LocationViewModel locationViewModel);
    void runInject(HistoryViewModel historyViewModel);
}
