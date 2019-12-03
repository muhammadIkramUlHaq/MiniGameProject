package com.king.game.handler;

import com.king.game.model.User;
import com.king.game.service.LevelService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.king.game.SessionAuthenticator.getPathParameter;

public class HighScoreHandler implements HttpHandler {
    private final LevelService levelService;

    private final static Logger logger = Logger.getLogger(HighScoreHandler.class.getName());

    public HighScoreHandler(LevelService levelService) {
        this.levelService = levelService;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        int levelId = getPathParameter(httpExchange);
        logger.info("Getting High Score List for Level = " + levelId);
        final Set<User> sortedUsers = levelService.getUsersByLevel(levelId)
                .stream()
                .sorted(Comparator.comparing(User::getScore).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        String response = convertToCSV(sortedUsers);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public String convertToCSV(Set<User> data) {
        if (data.isEmpty()) return "";
        return data
                .stream()
                .map(user -> user.getUserId() + " = " + user.getScore())
                .limit(15)
                .collect(Collectors.joining(","));
    }
}
