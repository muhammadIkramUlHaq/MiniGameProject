package com.king.game.service;

import com.king.game.repository.SessionRepository;

import java.time.LocalDateTime;

public class SessionService {

    public String createSessionKey() {
         return SessionRepository.getInstance().create();
    }

    public LocalDateTime getSessionTime(String sessionKey) {
         return SessionRepository.getInstance().get(sessionKey);
    }
}
