package me.bituco.soccernewsdio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.bituco.soccernewsdio.R;
import me.bituco.soccernewsdio.databinding.NewsItemBinding;
import me.bituco.soccernewsdio.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {

    private final List<News> news;
    private final FavouriteListener favouriteListener;

    public NewsAdapter(List<News> news, FavouriteListener favouriteListener) {
        this.news = news;
        this.favouriteListener = favouriteListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.title);
        holder.binding.tvDescription.setText(news.description);

        Picasso.get().load(news.image).into(holder.binding.ivNews);

        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            context.startActivity(i);
        });

        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent((Intent.ACTION_SEND));
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, news.title);
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            context.startActivity(Intent.createChooser(i, "share via"));
        });

        holder.binding.ivFavourites.setOnClickListener(view -> {
            news.favourite = !news.favourite;
            this.favouriteListener.onFavourite(news);
            notifyItemChanged(position);
        });
        int favouriteColor = news.favourite? R.color.purple_500 : R.color.black;
        holder.binding.ivFavourites.setColorFilter(context.getResources().getColor(favouriteColor));
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public viewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavouriteListener {
        void onFavourite(News news);
    }
}
