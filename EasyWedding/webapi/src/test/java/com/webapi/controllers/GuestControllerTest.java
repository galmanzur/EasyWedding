/*package com.easywedding.controllers;

import application.dtos.GuestDto;
import application.services.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnGuestsByWeddingId() throws Exception {
        GuestDto guest = new GuestDto();
        guest.setId(1L);
        guest.setName("John Doe");
        guest.setWeddingId(100L);

        Mockito.when(guestService.getGuestsByWedding(100L))
                .thenReturn(Collections.singletonList(guest));

        mockMvc.perform(get("/api/guests/wedding/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void shouldCreateGuest() throws Exception {
        GuestDto guest = new GuestDto();
        guest.setName("Jane");
        guest.setPhoneNumber("050-1234567");
        guest.setStatus("COMING");
        guest.setWeddingId(100L);

        Mockito.when(guestService.createGuest(Mockito.any()))
                .thenAnswer(invocation -> {
                    GuestDto g = invocation.getArgument(0);
                    g.setId(1L);
                    return g;
                });

        mockMvc.perform(post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Jane"));
    }
}
*/