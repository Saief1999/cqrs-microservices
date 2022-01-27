package com.example.searchmicroservice.repositories;

import com.example.searchmicroservice.entities.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
}
