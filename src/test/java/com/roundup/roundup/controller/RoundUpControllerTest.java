package com.roundup.roundup.controller;

import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import com.roundup.roundup.service.impl.RoundUpServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static  org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RoundUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoundUpServiceImpl roundUpServiceImpl;

    @Test
    public void roundUpTest() throws Exception {

        when(roundUpServiceImpl.roundUp("Bearer ")).thenReturn(new SavingsGoalResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/account/roundup")
                .header("Authorization","Bearer "))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }




}