package me.bituco.soccernewsdio.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.bituco.soccernewsdio.domain.News;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news WHERE favourite = 1")
    List<News> loadFavouriteNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(News news);
}
