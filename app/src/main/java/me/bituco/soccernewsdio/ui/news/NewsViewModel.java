package me.bituco.soccernewsdio.ui.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.bituco.soccernewsdio.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("jogo decisivo", "jogo muito importante"));
        news.add(new News("jogo não decisivo", "jogo nem tão importante"));
        news.add(new News("jogo nada decisivo", "jogo nem um pouco importante"));

        this.news.setValue(news);
    }

    public MutableLiveData<List<News>> getNews() {
        return news;
    }
}