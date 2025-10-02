package com.easywedding.application.services;

import com.easywedding.application.dtos.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpStatus;

import java.io.InputStream;

public class AuthService {

    private static final String BASE_URL = "http://localhost:8080/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserDto login(String username, String password) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(BASE_URL + "/login");

            String json = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
            request.setEntity(new StringEntity(json));
            request.setHeader("Content-Type", "application/json");

            try (ClassicHttpResponse response = client.executeOpen(null, request, null)) {
                int status = response.getCode();
                if (status == HttpStatus.SC_OK) {
                    InputStream is = response.getEntity().getContent();
                    return objectMapper.readValue(is, UserDto.class);
                } else {
                    throw new RuntimeException("Login failed with status code: " + status);
                }
            }
        }
    }

    public UserDto register(UserDto userDto) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(BASE_URL);

            String json = objectMapper.writeValueAsString(userDto);
            request.setEntity(new StringEntity(json));
            request.setHeader("Content-Type", "application/json");

            try (ClassicHttpResponse response = client.executeOpen(null, request, null)) {
                int status = response.getCode();
                if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {
                    InputStream is = response.getEntity().getContent();
                    return objectMapper.readValue(is, UserDto.class);
                } else {
                    throw new RuntimeException("Registration failed with status code: " + status);
                }
            }
        }
    }
}
