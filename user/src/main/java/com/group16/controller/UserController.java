package com.group16.controller;

import com.group16.dto.UserResponseDto;
import com.group16.model.User;
import com.group16.repository.IUserRepository;
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
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository repository;

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

    @PatchMapping("/public/api/users/{id}")
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

    //Should be stored in cookie for authorization
    @PostMapping("/api/userId/{email}/")
    public long getUserById(@PathVariable("email") String email) {
        return userService.getIdByEmail(email);
    }

    //If it returns 0, it means that the security answer is incorrect
    //if it returns -1, the user does not exist in the user database
    @PostMapping("/public/users/checkAnswer")
    public long checkSecurityAnswer(@RequestBody User userDetails) {
        long correctAnswer = 0;
        Optional<User> optional = repository.findByEmail(userDetails.getEmail());
        if (optional.isEmpty()) {
            correctAnswer = -1;
        }
        User existingUser = optional.get();
        if (userDetails.getPasswordResetAnswer().equalsIgnoreCase(existingUser.getPasswordResetAnswer())) {
            correctAnswer = existingUser.getId();
        }
        return correctAnswer;
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
