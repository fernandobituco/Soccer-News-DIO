package me.bituco.soccernewsdio.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import me.bituco.soccernewsdio.MainActivity;
import me.bituco.soccernewsdio.databinding.FragmentFavouritesBinding;
import me.bituco.soccernewsdio.domain.News;
import me.bituco.soccernewsdio.ui.adapter.NewsAdapter;

public class FavouritesFragment extends Fragment {

    private FragmentFavouritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FavouritesViewModel FavouritesViewModel = new ViewModelProvider(this).get(me.bituco.soccernewsdio.ui.favourites.FavouritesViewModel.class);

        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        loadFavouriteNews();

        return root;
    }

    private void loadFavouriteNews() {
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        List<News> favouriteNews = mainActivity.getDb().newsDao().loadFavouriteNews();
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNews.setAdapter(new NewsAdapter(favouriteNews, favouriteListener -> {
            mainActivity.getDb().newsDao().save(favouriteListener);
            loadFavouriteNews();
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}