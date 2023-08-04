package com.enigma.moviemania.repository;

import com.enigma.moviemania.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT * FROM m_movie WHERE genre = ?1", nativeQuery = true)
    List<Movie> findByGenre(String genre);}
