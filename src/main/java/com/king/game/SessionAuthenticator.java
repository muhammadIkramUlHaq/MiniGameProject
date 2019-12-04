package com.king.game;

import com.king.game.service.SessionService;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.lang.Math.abs;

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
        long diff = abs(duration.toMinutes());
        return diff < SESSION_TIMEOUT;
    }
}
