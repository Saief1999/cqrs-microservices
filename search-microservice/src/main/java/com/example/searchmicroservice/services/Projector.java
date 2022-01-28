package com.example.searchmicroservice.services;

import com.example.searchmicroservice.entities.Movie;
import com.example.searchmicroservice.pojos.UpdateMessage;
import com.example.searchmicroservice.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class Projector {

    private MovieRepository movieRepository;

    public Projector(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void removeMovie(Movie movie) {
        String id = movie.getId();
        movieRepository.deleteById(id);
        System.out.println(String.format("#### -> removed Movie with id -> %s", id));

    }

    public void upsertMovie(Movie movie) {
        movieRepository.save(movie);
        System.out.println(String.format("#### -> inserted/updated Movie with id -> %s", movie.getId()));
    }
}
