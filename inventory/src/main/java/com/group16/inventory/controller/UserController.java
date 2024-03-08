package com.group16.inventory.controller;

import com.group16.inventory.domain.po.User;
import com.group16.inventory.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

// REST controller for handling user-related requests
@Tag(name = "User Management Interfaces") // Swagger tag for user management
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    // Constructor-based DI for service layer
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // Endpoint for creating a new user
    @Operation(summary = "Create a new user")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    // Endpoint for retrieving a user by ID
    @Operation(summary = "Retrieve a user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@Parameter(description = "User ID") @PathVariable Long id) {
        User user = userService.getById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Endpoint for retrieving all users
    @Operation(summary = "Retrieve all users")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.list();
        return ResponseEntity.ok(users);
    }

    // Endpoint for updating an existing user
    @Operation(summary = "Update an existing user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Parameter(description = "User ID") @PathVariable Long id, @RequestBody User userDetails) {
        userDetails.setUserId(id);
        boolean updated = userService.updateById(userDetails);
        return updated ? ResponseEntity.ok(userDetails) : ResponseEntity.notFound().build();
    }

    // Endpoint for deleting a user by ID
    @Operation(summary = "Delete a user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "User ID") @PathVariable Long id) {
        boolean removed = userService.removeById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

