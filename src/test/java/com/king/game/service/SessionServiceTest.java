package com.king.game.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SessionServiceTest {
    private final SessionService sessionService = new SessionService();

    @Test
    public void get_session_time_should_return_session_time_with_session_key() {
        String sessionKey = sessionService.createSessionKey();
        final LocalDateTime sessionTime = sessionService.getSessionTime(sessionKey);
        assertFalse(sessionTime.toString().isEmpty());
    }

    @Test
    public void get_session_time_should_return_null_with_expired_session_key() {
        final LocalDateTime sessionTime = sessionService.getSessionTime("Expired_Session_Key");
        assertNull(sessionTime);
    }
}