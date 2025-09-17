package com.easywedding.application.services;

import com.easywedding.application.dtos.GuestDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GuestService {
    private static final String BASE_URL = "http://localhost:8080/api/guests";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<GuestDto> getAllGuests() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), new TypeReference<List<GuestDto>>() {});
            } else {
                throw new RuntimeException("Failed to fetch guests: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching guests", e);
        }
    }
}
