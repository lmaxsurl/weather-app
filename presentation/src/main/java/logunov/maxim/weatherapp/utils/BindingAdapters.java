package logunov.maxim.weatherapp.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.Button;

public class BindingAdapters {

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean isVisible){
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("android:enabled")
    public static void setEnabled(Button button, Boolean isEnabled){
        button.setEnabled(isEnabled);
    }

}
