package com.king.game.handler;

import com.king.game.SessionAuthenticator;
import com.king.game.model.User;
import com.king.game.service.LevelService;
import com.king.game.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;

import static com.king.game.handler.RootHandler.getPathParameter;
import static com.king.game.handler.RootHandler.queryToMap;
import static java.lang.Math.abs;

public class ScoreHandler implements HttpHandler {
    private final UserService userService;
    private final LevelService levelService;
    private final SessionAuthenticator sessionAuthenticator;

    private final static Logger logger = Logger.getLogger(ScoreHandler.class.getName());

    public ScoreHandler(UserService userService,
                        LevelService levelService,
                        SessionAuthenticator sessionAuthenticator) {
        this.userService = userService;
        this.levelService = levelService;
        this.sessionAuthenticator = sessionAuthenticator;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Map<String, String> params = queryToMap(httpExchange);
        final String sessionKey = params.get("sessionkey");
        if (!sessionAuthenticator.authenticate(sessionKey)) {
            String response = "Session Key Expired";
            logger.info("Session Key Expired");
            httpExchange.sendResponseHeaders(401, 0);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        } else {
            int levelId = getPathParameter(httpExchange);
            InputStreamReader inputStreamReader = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int score = abs(Integer.parseInt(bufferedReader.readLine()));
            User user = userService.getUser(sessionKey);
            logger.info("Posting the Score for UserID =" + user.getUserId() + "," +
                    " for Level = " + levelId +
                    ", with SessionKey = " + sessionKey +
                    ", Score = " + score);
            userService.createScore(user, score);
            levelService.createLevel(levelId, user);
            httpExchange.close();
        }
    }
}
