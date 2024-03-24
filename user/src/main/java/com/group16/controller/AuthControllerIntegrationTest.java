package com.group16.controller;

import com.group16.UserApplication;
import com.group16.dto.ReqRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private ReqRes signUpRequest;

    @BeforeEach
    void setUp() {
        signUpRequest = new ReqRes();
        signUpRequest.setEmail("test@example.com");
        signUpRequest.setPassword("password");
        // Add any other necessary setup for your ReqRes object
    }

    @Test
    void testUserRegistration() {
        HttpEntity<ReqRes> request = new HttpEntity<>(signUpRequest);
        ResponseEntity<ReqRes> response = restTemplate.exchange("/auth/signup", HttpMethod.POST, request, ReqRes.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        // Add more assertions based on your application's requirements
    }

    @Test
    void testUserSignin() {
        HttpEntity<ReqRes> request = new HttpEntity<>(signUpRequest);
        ResponseEntity<ReqRes> response = restTemplate.exchange("/auth/signin", HttpMethod.POST, request, ReqRes.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        // Add more assertions based on your application's requirements
    }
}