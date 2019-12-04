package com.king.game.handler;

import com.king.game.service.SessionService;
import com.king.game.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

public class LoginHandler implements HttpHandler {
    private final UserService userService;
    private final SessionService sessionService;

    private final static Logger logger = Logger.getLogger(LoginHandler.class.getName());

    public LoginHandler(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        int userId = RootHandler.getPathParameter(httpExchange);
        String sessionKey = sessionService.createSessionKey();
        userService.createUserSession(sessionKey, userId);
        logger.info("Creating Session for UserID = " + userId + ", with Session Key = " + sessionKey);
        httpExchange.sendResponseHeaders(200, sessionKey.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(sessionKey.getBytes());
        outputStream.close();
    }
}
