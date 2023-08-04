package com.enigma.moviemania.service;

import com.enigma.moviemania.entity.Actor;
import com.enigma.moviemania.entity.Movie;
import com.enigma.moviemania.entity.Review;

import java.util.List;

public interface MovieManiaService {

    Movie getMovieById(Long id);
    List<Movie> getMoviesByGenre(String genre);
    Actor getActorByName(String name);
    Movie addActorToMovie(Long movieId, Long actorId);
    List<Review> getReviewsForMovie(Long movieId);

    Movie addReviewToMovie(Long movieId, Review review);

    List<Movie> getAllMovies();

    Movie addMovie(Movie movie);

    boolean deleteMovie(Long id);

    Movie updateMovie(Long movieId, Movie updatedMovie);
}
