package logunov.maxim.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import logunov.maxim.domain.entity.Weather;
import logunov.maxim.domain.entity.WeatherRequest;

import static logunov.maxim.domain.entity.WeatherRequest.TABLE_NAME;

/**
 * @see Weather
 */


@Dao
public interface WeatherDAO {

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<WeatherRequest>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherRequest request);

}
