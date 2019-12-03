package com.king.game.repository;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;

public class SessionRepository {
    private static SessionRepository sessionRepository;
    private HashMap<String, LocalDateTime> sessionMap = new HashMap<>();

    private SessionRepository() {
    }

    public static synchronized SessionRepository getInstance() {
        if (sessionRepository == null) { //if there is no instance available... create new one
            sessionRepository = new SessionRepository();
        }
        return sessionRepository;
    }

    public String create() {
        String sessionKey = RandomStringUtils.randomAlphabetic(8);
        sessionMap.put(sessionKey, LocalDateTime.now());
        return sessionKey;
    }

    public LocalDateTime get(String sessionKey) {
        return sessionMap.get(sessionKey) ;
    }
}
