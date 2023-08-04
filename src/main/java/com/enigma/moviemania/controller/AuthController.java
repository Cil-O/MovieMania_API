package com.enigma.moviemania.controller;

import com.enigma.moviemania.LoginRequest;
import com.enigma.moviemania.entity.Admin;
import com.enigma.moviemania.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@RequestBody Admin admin) {
        admin.setRoles(Collections.singleton("ADMIN"));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return "redirect:/login?registered";
    }
}
