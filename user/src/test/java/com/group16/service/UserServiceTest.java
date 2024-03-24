package com.group16.service;

import com.group16.model.User;
import com.group16.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveNewUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("TestPassword");
        user.setPasswordResetQuestion("What is your favorite color?");
        user.setPasswordResetAnswer("Blue");
        // Assuming the role is set within the service as "USER"

        when(passwordEncoder.encode(user.getPassword())).thenReturn("EncodedPassword");
        when(repository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveNewUser(user);

        verify(repository, times(1)).save(any(User.class)); // Use any(User.class) due to password encoding
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPasswordResetQuestion(), savedUser.getPasswordResetQuestion());
        assertEquals(user.getPasswordResetAnswer(), savedUser.getPasswordResetAnswer());
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("TestPassword");
        user.setRole("USER");
        user.setPasswordResetQuestion("What is your favorite color?");
        user.setPasswordResetAnswer("Blue");

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findUserById(1L);

        verify(repository, times(1)).findById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getPasswordResetQuestion(), foundUser.get().getPasswordResetQuestion());
        assertEquals(user.getPasswordResetAnswer(), foundUser.get().getPasswordResetAnswer());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(repository).deleteById(1L);

        userService.deleteUser(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
