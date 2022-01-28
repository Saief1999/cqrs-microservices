package com.example.searchmicroservice.pojos;

import com.example.searchmicroservice.entities.Movie;

public class UpdateMessage {

    private Movie movie ;
    private boolean isDeleted = false;


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public UpdateMessage(Movie movie, boolean isDeleted) {
        this.movie = movie;
        this.isDeleted = isDeleted;
    }

    public UpdateMessage() {}

    @Override
    public String toString() {
        return "UpdateMessage{" +
                "movie=" + movie.getId() +
                ", isDeleted=" + isDeleted +
                '}';
    }
}