package com.enigma.moviemania.service.impl;

import com.enigma.moviemania.entity.Actor;
import com.enigma.moviemania.entity.Movie;
import com.enigma.moviemania.entity.Review;
import com.enigma.moviemania.repository.ActorRepository;
import com.enigma.moviemania.repository.MovieRepository;
import com.enigma.moviemania.repository.ReviewRepository;
import com.enigma.moviemania.service.MovieManiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieManiaServiceImpl implements MovieManiaService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public MovieManiaServiceImpl(MovieRepository movieRepository, ActorRepository actorRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }
    public Movie addActorToMovie(Long movieId, Long actorId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Optional<Actor> optionalActor = actorRepository.findById(actorId);

        if (optionalMovie.isPresent() && optionalActor.isPresent()) {
            Movie movie = optionalMovie.get();
            Actor actor = optionalActor.get();

            movie.addActor(actor); // Memanggil method addActor untuk menambahkan aktor ke film
            movieRepository.save(movie);
            return movie;
        } else {
            return null;
        }
    }


    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public Actor getActorByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public List<Review> getReviewsForMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    @Override
    public Movie addReviewToMovie(Long movieId, Review review) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            review.setMovie(movie);
            movie.getReviews().add(review);
            movie.setRating(calculateAverageRating(movie.getReviews()));
            movieRepository.save(movie);
        }
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public boolean deleteMovie(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Movie updateMovie(Long movieId, Movie updatedMovie) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setSynopsis(updatedMovie.getSynopsis());
            movie.setGenre(updatedMovie.getGenre());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setRating(calculateAverageRating(movie.getReviews()));
            movieRepository.save(movie);
        }
        return movie;
    }

    private double calculateAverageRating(List<Review> reviews) {
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
