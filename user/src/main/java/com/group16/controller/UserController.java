package com.group16.controller;

import com.group16.model.User;
import com.group16.service.UserService;
import com.group16.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private UserService userService;

    @PostMapping("/kafkaTesting")
    public void registerUser(@RequestBody User user) {
        kafkaProducerService.sendMessage(user);
        // Additional logic for user registration
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//        return userService.findUserById(id)
//                .map(existingUser -> {
//                    existingUser.setFirstNames(userDetails.getFirstNames());
//                    existingUser.setLastName(userDetails.getLastName());
//                    existingUser.setPassword(userDetails.getPassword()); // Consider encryption or hashing
//                    existingUser.setRole(userDetails.getRole());
//                    existingUser.setEmail(userDetails.getEmail());
//                    existingUser.setPhoneNumber(userDetails.getPhoneNumber());
//                    // Token should not be updated casually. Handle with care.
//                    User updatedUser = userService.saveUser(existingUser);
//                    return ResponseEntity.ok(updatedUser);
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(user -> {
                    userService.deleteUser(user.getId());
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
