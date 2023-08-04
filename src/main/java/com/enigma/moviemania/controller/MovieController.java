package com.enigma.moviemania.controller;

import com.enigma.moviemania.entity.Movie;
import com.enigma.moviemania.entity.Review;
import com.enigma.moviemania.service.MovieManiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieManiaService movieManiaService;

    @Autowired
    public MovieController(MovieManiaService movieManiaService) {
        this.movieManiaService = movieManiaService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieManiaService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieManiaService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie addedMovie = movieManiaService.addMovie(movie);
        return ResponseEntity.ok(addedMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieManiaService.updateMovie(id, movie);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieManiaService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    public ResponseEntity<Movie> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        Movie updatedMovie = movieManiaService.addActorToMovie(movieId, actorId);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<Movie> addReviewToMovie(@PathVariable Long movieId, @RequestBody Review review) {
        Movie updatedMovie = movieManiaService.addReviewToMovie(movieId, review);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
