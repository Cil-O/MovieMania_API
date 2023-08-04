package com.enigma.moviemania.repository;

import com.enigma.moviemania.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(value = "SELECT * FROM m_admin WHERE username = ?1", nativeQuery = true)
    Admin findByUsername(String username);
}
