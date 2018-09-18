package logunov.maxim.weatherapp.presentation.base;

public class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }


}
