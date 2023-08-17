package com.wanted.onboarding.controller;

import com.wanted.onboarding.dto.SignRequestDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Successful Signup")
    void create_success() throws Exception {
        this.mockMvc.perform(post("/v1/signup")
                        .content("{\"email\": \"randommail@google.com\"," +
                                " \n\"password\": \"long_enough_password\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Get all the users")
    void getAllUsers() throws Exception {
        this.mockMvc.perform(
        get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Invalid Signup")
    void create_fail() throws Exception {
        this.mockMvc.perform(post("/v1/signup")
                        .content("{\"email\": \"randommail@google.com\"," +
        " \n\"password\": \"short\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
