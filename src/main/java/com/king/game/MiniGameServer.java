package com.king.game;

import com.king.game.handler.RootHandler;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class MiniGameServer {
    private final static int PORT_NUMBER = 9000;
    private final static String ROOT = "/";
    private final static Logger logger = Logger.getLogger(MiniGameServer.class.getName());

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT_NUMBER), 0);
            logger.info("Server is started at port: " + PORT_NUMBER);
            HttpContext httpContext = httpServer.createContext(ROOT, new RootHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
