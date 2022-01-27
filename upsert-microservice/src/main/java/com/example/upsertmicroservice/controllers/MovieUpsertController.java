package com.example.upsertmicroservice.controllers;

import com.example.upsertmicroservice.entities.Movie;
import com.example.upsertmicroservice.exceptions.BadRequestException;
import com.example.upsertmicroservice.producers.Producer;
import com.example.upsertmicroservice.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class MovieUpsertController
{
    private MovieRepository movieRepository;
    private Producer producer;

    public MovieUpsertController(MovieRepository movieRepository,Producer producer) {
        this.movieRepository=movieRepository;
        this.producer=producer;
    }



    @PostMapping("/publish")
    public String publish()
    {
        String message = "Hello there today";
        producer.sendMessage(message);
        return "Published " + message;
    }


    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable("id") String id) {


        Movie movie = movieRepository.findById(id).orElseThrow(()-> {
            String errMsg = "Movie Not found with id " + id ;
            return new BadRequestException(BadRequestException.ID_NOT_FOUND, errMsg);
        });
        return movie;
    }

    @GetMapping("/movies")
    public List<Movie> listMovies()
    {
        return movieRepository.findAll();
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie)
    {
        return movieRepository.save(movie);
    }


    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable("id") String id, @RequestBody Movie movie)
    {
        movie.setId(id);
        if(!movieRepository.existsById(movie.getId()))
            throw new BadRequestException(BadRequestException.ID_NOT_FOUND,"Movie Not found with id " + movie.getId());

        return movieRepository.save(movie);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> removeMovie(@PathVariable("id") String id) {

        if(!movieRepository.existsById(id))
            throw new BadRequestException(BadRequestException.ID_NOT_FOUND,"Movie Not found with id " + id);

        movieRepository.deleteById(id);
        return new ResponseEntity<String>("Movie deleted",HttpStatus.OK);
    }

    @ExceptionHandler(BadRequestException.class)
    void handleBadRequests(BadRequestException bre, HttpServletResponse response) throws IOException {

        int respCode = (bre.errCode == BadRequestException.ID_NOT_FOUND) ?
                HttpStatus.NOT_FOUND.value() : HttpStatus.BAD_REQUEST.value() ;
        response.sendError(respCode, bre.errCode + ":" + bre.getMessage());
    }

}
