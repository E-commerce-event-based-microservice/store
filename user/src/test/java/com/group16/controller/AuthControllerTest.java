package com.group16.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group16.dto.UserLoginDto;
import com.group16.model.User;
import com.group16.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        // Setup your mock behavior here
    }

    @Test
    void testUserRegistration() throws Exception {
        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setPassword("password");
        // Assume userService.save returns the saved user
        when(userService.saveUser(any(User.class))).thenReturn(newUser);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk());
    }

    @Test
    void testUserLogin() throws Exception {
        // Setup mock authenticationManager.authenticate to succeed

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserLoginDto("test@example.com", "password"))))
                .andExpect(status().isOk());
    }
}