package com.easywedding.application.core;

/** Holds global session state: auth token, user id, current wedding id, etc. */
public class AppState {
    private static String authToken;     // e.g., "Bearer eyJ..."
    private static Long userId;
    private static Long weddingId = Long.getLong("easywedding.weddingId", 1L);

    public static String getAuthToken() { return authToken; }
    public static void setAuthToken(String token) { authToken = token; }

    public static Long getUserId() { return userId; }
    public static void setUserId(Long id) { userId = id; }

    public static Long getWeddingId() { return weddingId; }
    public static void setWeddingId(Long id) { weddingId = id; }

    public static boolean isLoggedIn() { return authToken != null && !authToken.isBlank(); }

    public static void clear() {
        authToken = null;
        userId = null;
        weddingId = Long.getLong("easywedding.weddingId", 1L);
    }
}
