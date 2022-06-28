package me.bituco.soccernewsdio.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.bituco.soccernewsdio.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class NewsDb extends RoomDatabase {
    public abstract NewsDao newsDao();
}
