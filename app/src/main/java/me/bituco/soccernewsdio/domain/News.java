package me.bituco.soccernewsdio.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.material.textview.MaterialTextView;

@Entity
public class News {

    @PrimaryKey
    public int id;
    public String title;
    public String description;
    public String image;
    public String link;
    public boolean favourite;
}
