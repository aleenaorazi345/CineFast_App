package com.example.assignment_1;

public class MovieDetails {
    private String name;
    private String trailerUrl;
    private int imageResId; // Drawable resource for poster

    public MovieDetails(String name, String trailerUrl, int imageResId) {
        this.name = name;
        this.trailerUrl = trailerUrl;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public int getImageResId() {
        return imageResId;
    }
}