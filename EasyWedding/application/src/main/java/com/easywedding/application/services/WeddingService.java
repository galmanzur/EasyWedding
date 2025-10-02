package com.easywedding.application.services;

import com.easywedding.application.dtos.WeddingDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpStatus;

import java.io.InputStream;
import java.util.List;

public class WeddingService {

    private static final String BASE_URL = "http://localhost:8080/api/weddings";
    private final ObjectMapper objectMapper;

    public WeddingService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Enable Java 8 time support
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public List<WeddingDto> getAllWeddings() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URL);
            request.setHeader("Accept", "application/json");

            try (ClassicHttpResponse response = client.executeOpen(null, request, null)) {
                int status = response.getCode();
                if (status == HttpStatus.SC_OK) {
                    InputStream is = response.getEntity().getContent();
                    return objectMapper.readValue(is, new TypeReference<>() {});
                } else {
                    throw new RuntimeException("Failed to fetch weddings: " + status);
                }
            }
        }
    }

    public WeddingDto createWedding(WeddingDto dto) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(BASE_URL);
            request.setHeader("Content-Type", "application/json");

            String json = objectMapper.writeValueAsString(dto);
            request.setEntity(new StringEntity(json));

            try (ClassicHttpResponse response = client.executeOpen(null, request, null)) {
                if (response.getCode() == HttpStatus.SC_OK || response.getCode() == HttpStatus.SC_CREATED) {
                    InputStream is = response.getEntity().getContent();
                    return objectMapper.readValue(is, WeddingDto.class);
                } else {
                    throw new RuntimeException("Failed to create wedding: " + response.getCode());
                }
            }
        }
    }
}
