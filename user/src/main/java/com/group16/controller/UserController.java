package com.group16.controller;

import com.group16.dto.UserResponseDto;
import com.group16.model.User;
import com.group16.service.UserService;
import com.group16.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/public/api/users")
    public User createUser(@RequestBody User user) {
        return userService.saveNewUser(user);
    }
    @PostMapping("/public/kafkaTesting")
    public void registerUser(@RequestBody User user) {
        kafkaProducerService.sendMessage(user);
        // Additional logic for user registration
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/api/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/api/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody User userDetails) {
        return userService.findUserById(id)
                .map(existingUser -> {
                    if (userDetails.getPassword() != null) {
                        existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                    }
                    if (userDetails.getRole() != null) {
                        existingUser.setRole(userDetails.getRole());
                    }
                    if (userDetails.getEmail() != null) {
                        existingUser.setEmail(userDetails.getEmail());
                    }
                    User updatedUser = userService.saveUser(existingUser);

                    UserResponseDto userDTO = new UserResponseDto();
                    userDTO.setId(updatedUser.getId());
                    userDTO.setEmail(updatedUser.getEmail());
                    userDTO.setRole(updatedUser.getRole());
                    return ResponseEntity.ok(userDTO);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(user -> {
                    userService.deleteUser(user.getId());
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
