package com.easywedding.application.services;

import com.easywedding.application.dtos.GuestDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.List;

/**
 * Client-side service that talks to the WebAPI GuestController.
 *
 * Endpoints (as in your server code):
 *  - POST   /api/guests
 *  - PUT    /api/guests/{id}
 *  - DELETE /api/guests/{id}
 *  - GET    /api/guests/{id}
 *  - GET    /api/guests/wedding/{weddingId}
 *  - GET    /api/guests/search?name=...
 *  - GET    /api/guests/table/{tableNumber}
 *  - GET    /api/guests/table/{tableNumber}/count
 *
 * Base URL is configurable via -Deasywedding.apiBase (default http://localhost:8080).
 */
public class GuestService {

    private static final String API_BASE =
            System.getProperty("easywedding.apiBase", "http://localhost:8080");

    private long weddingId;

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public GuestService(long weddingId) { this.weddingId = weddingId; }
    public GuestService() { this.weddingId = Long.getLong("easywedding.weddingId", 1L); }

    public void setWeddingId(long weddingId) { this.weddingId = weddingId; }

    // ---------- READ ----------

    /** GET /api/guests/wedding/{weddingId} */
    public List<GuestDto> getByWeddingId() throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests/wedding/" + weddingId;
        HttpResponse<String> res = sendGet(url);
        ensure200(res, url);
        return mapper.readValue(res.body(), new TypeReference<>() {});
    }

    /** GET /api/guests/{id} */
    public GuestDto getById(long id) throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests/" + id;
        HttpResponse<String> res = sendGet(url);
        ensure200(res, url);
        return mapper.readValue(res.body(), GuestDto.class);
    }

    /** GET /api/guests/search?name=... */
    public List<GuestDto> searchByName(String name) throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests/search?name=" + encode(name);
        HttpResponse<String> res = sendGet(url);
        ensure200(res, url);
        return mapper.readValue(res.body(), new TypeReference<>() {});
    }

    /** GET /api/guests/table/{tableNumber} */
    public List<GuestDto> getByTableNumber(long tableNumber) throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests/table/" + tableNumber;
        HttpResponse<String> res = sendGet(url);
        ensure200(res, url);
        return mapper.readValue(res.body(), new TypeReference<>() {});
    }

    /** GET /api/guests/table/{tableNumber}/count */
    public long countByTableNumber(long tableNumber) throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests/table/" + tableNumber + "/count";
        HttpResponse<String> res = sendGet(url);
        ensure200(res, url);
        return mapper.readValue(res.body(), Long.class);
    }

    // ---------- WRITE ----------

    /** POST /api/guests */
    public GuestDto createGuest(GuestDto dto) throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests";
        String json = mapper.writeValueAsString(dto);
        HttpResponse<String> res = sendJson(url, "POST", json);
        ensure200(res, url);
        return mapper.readValue(res.body(), GuestDto.class);
    }

    /** PUT /api/guests/{id} */
    public GuestDto updateGuest(long id, GuestDto dto) throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests/" + id;
        String json = mapper.writeValueAsString(dto);
        HttpResponse<String> res = sendJson(url, "PUT", json);
        ensure200(res, url);
        return mapper.readValue(res.body(), GuestDto.class);
    }

    /** DELETE /api/guests/{id} */
    public void deleteGuest(long id) throws IOException, InterruptedException {
        String url = API_BASE + "/api/guests/" + id;
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(8))
                .DELETE()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() != 204) {
            throw new RuntimeException("Unexpected status " + res.statusCode() + " from " + url + ": " + res.body());
        }
    }

    // ---------- HTTP helpers ----------

    private HttpResponse<String> sendGet(String url) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(8))
                .GET()
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> sendJson(String url, String method, String json) throws IOException, InterruptedException {
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
        HttpRequest.Builder b = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(8))
                .header("Content-Type", "application/json");

        HttpRequest req = switch (method) {
            case "POST" -> b.POST(body).build();
            case "PUT"  -> b.PUT(body).build();
            default     -> throw new IllegalArgumentException("Unsupported method: " + method);
        };

        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    private void ensure200(HttpResponse<String> res, String url) {
        if (res.statusCode() != 200) {
            throw new RuntimeException("Unexpected status " + res.statusCode() + " from " + url + ": " + res.body());
        }
    }

    private String encode(String s) {
        return java.net.URLEncoder.encode(s, java.nio.charset.StandardCharsets.UTF_8);
    }
}
