package com.example.assignment_1;

public class MovieDetails {
    private String name;
    private String trailerUrl;
    private int imageResId; // Drawable resource for poster
    private boolean comingSoon; // new field
    public MovieDetails(String name, String trailerUrl, int imageResId,boolean comingSoon) {
        this.name = name;
        this.trailerUrl = trailerUrl;
        this.imageResId = imageResId;
        this.comingSoon = comingSoon;
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
    public boolean isComingSoon() {
        return comingSoon;
    }
}