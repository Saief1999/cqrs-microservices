package com.example.upsertmicroservice.repositories;

import com.example.upsertmicroservice.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
}
