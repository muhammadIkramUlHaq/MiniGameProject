package com.king.game;

import com.king.game.service.SessionService;
import com.sun.net.httpserver.HttpExchange;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SessionAuthenticator {
    private final static int SESSION_TIMEOUT = 10;
    private final SessionService sessionService;

    public SessionAuthenticator(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public Boolean authenticate(String sessionKey) {
        LocalDateTime sessionTime = sessionService.getSessionTime(sessionKey);
        if (sessionTime == null) {
            return false;
        }
        Duration duration = Duration.between(LocalDateTime.now(), sessionTime);
        long diff = Math.abs(duration.toMinutes());
        return diff < SESSION_TIMEOUT;
    }

    public static int getPathParameter(HttpExchange httpExchange) {
        String path = httpExchange.getRequestURI().getPath();
        return Integer.parseInt(path.split("/")[1].trim());
    }

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                } else {
                    result.put(entry[0], "");
                }
            }
        }
        return result;
    }
}
