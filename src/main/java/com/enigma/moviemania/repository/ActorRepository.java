package com.enigma.moviemania.repository;

import com.enigma.moviemania.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByName(String name);
}
