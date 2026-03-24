package com.example.assignment_1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    ArrayList<MovieDetails> list;

    public MovieAdapter(Context context, ArrayList<MovieDetails> movie) {
        this.context = context;
        this.list = movie;
    }

    // 🔹 ViewHolder class (VERY IMPORTANT)
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        ImageButton btnTrailer;
        Button btnBook;
        ImageView movieImage; // poster

        public ViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            btnTrailer = itemView.findViewById(R.id.btnTrailer);
            btnBook = itemView.findViewById(R.id.btnBook);
            movieImage = itemView.findViewById(R.id.movieImage); // new
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single movie item
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieDetails movie = list.get(position);
        holder.movieName.setText(movie.getName());
        holder.movieImage.setImageResource(movie.getImageResId());

        holder.btnTrailer.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(movie.getTrailerUrl()));
            context.startActivity(intent);
        });

        holder.btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(context, SeatSelection.class);
            intent.putExtra("name_key", movie.getName());
            context.startActivity(intent);
        });
    }

    // 🔹 Number of items
    @Override
    public int getItemCount() {
        return list.size();
    }
}