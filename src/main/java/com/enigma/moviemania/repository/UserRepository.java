package com.enigma.moviemania.repository;

import com.enigma.moviemania.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM m_user WHERE username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);}
