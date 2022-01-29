package com.example.searchmicroservice.repositories;

import com.example.searchmicroservice.entities.Movie;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface MovieRepository extends ElasticsearchRepository<Movie, String>
{
    @Override
    Iterable<Movie> findAll();

    @Cacheable(cacheNames = "movies", key = "#id")
    @Override
    Optional<Movie> findById(String id);


    @CachePut(cacheNames = "movies", key = "#movie.id")
    @Override
    <S extends Movie> S save(S movie);

    @CacheEvict(cacheNames = "movies", key = "#id")
    @Override
    void deleteById(String id);
}
