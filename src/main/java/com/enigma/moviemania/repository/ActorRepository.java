package com.enigma.moviemania.repository;

import com.enigma.moviemania.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query(value = "SELECT * FROM m_actor WHERE name = ?1", nativeQuery = true)
    Actor findByName(String name);
}
