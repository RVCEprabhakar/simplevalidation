package com.example.validation_demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repo;

    // REGISTER
    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) {
        repo.save(user);
        return "User registered successfully";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = repo.findByEmail(user.getEmail());

        if (dbUser == null) {
            return "Email not found";
        }

        if (!dbUser.getPassword().equals(user.getPassword())) {
            return "Wrong password";
        }

        return "Login successful";
    }
}