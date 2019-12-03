package com.king.game;

import com.king.game.service.SessionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionAuthenticatorTest {
    private final SessionService sessionService = new SessionService();
    private final SessionAuthenticator sessionAuthenticator = new SessionAuthenticator(sessionService) ;
    private static String SESSION_KEY ;

    @Test
    void authenticate_should_return_true_for_session_key() {
        SESSION_KEY = sessionService.createSessionKey();
        assertEquals(true,sessionAuthenticator.authenticate(SESSION_KEY));
    }

    @Test
    void authenticate_should_return_false_for_wrong_session_key() {
        SESSION_KEY = sessionService.createSessionKey();
        assertEquals(false,sessionAuthenticator.authenticate("SESSION_KEY"));
    }
}