package me.bituco.soccernewsdio.ui.news;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.bituco.soccernewsdio.data.remote.SoccerNewsApi;
import me.bituco.soccernewsdio.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://digitalinnovationone.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SoccerNewsApi.class);

        this.findNews();
    }

    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                } else {
                    Log.d("DEBUG", "Response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                Log.d("DEBUG", t.toString());
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}