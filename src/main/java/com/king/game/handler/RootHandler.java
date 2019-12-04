package com.king.game.handler;

import com.king.game.SessionAuthenticator;
import com.king.game.service.LevelService;
import com.king.game.service.SessionService;
import com.king.game.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

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

    public static int getPathParameter(HttpExchange httpExchange) {
        String path = httpExchange.getRequestURI().getPath();
        return abs(Integer.parseInt(path.split("/")[1].trim()));
    }

    public static Map<String, String> queryToMap(HttpExchange httpExchange) {
        String query = httpExchange.getRequestURI().getQuery();
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