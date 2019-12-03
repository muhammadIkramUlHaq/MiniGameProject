package com.king.game.handler;

import com.king.game.SessionAuthenticator;
import com.king.game.service.LevelService;
import com.king.game.service.SessionService;
import com.king.game.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class RootHandler implements HttpHandler {
    private final static String LOGIN = "/login";
    private final static String SCORE = "/score";
    private final static String HIGHSCORELIST = "/highscorelist";
    private final UserService userService = new UserService();
    private final LevelService levelService = new LevelService();
    private final SessionService sessionService = new SessionService();
    private final SessionAuthenticator sessionAuthenticator = new SessionAuthenticator(sessionService);

    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        if (path.contains(LOGIN)) {
            new LoginHandler(userService, sessionService).handle(httpExchange);
        } else if (path.contains(SCORE)) {
            new ScoreHandler(userService, levelService, sessionAuthenticator).handle(httpExchange);
        } else if (path.contains(HIGHSCORELIST)) {
            new HighScoreHandler(levelService).handle(httpExchange);
        } else {
            new NotFoundHandler().handle(httpExchange);
        }
    }
}