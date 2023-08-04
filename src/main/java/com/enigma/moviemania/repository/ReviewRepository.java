package com.enigma.moviemania.repository;

import com.enigma.moviemania.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT * FROM m_revie WHERE movie_id = ?1", nativeQuery = true)
    List<Review> findByMovieId(Long movieId);
}
