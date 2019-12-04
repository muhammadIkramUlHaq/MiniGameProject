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
        if (args.length > 0) {
            start(Integer.parseInt(args[0]));
        } else {
            start(PORT_NUMBER);
        }
    }

    public static void start(int portNumber) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(portNumber), 0);
            logger.info("Server is started at port: " + portNumber);
            HttpContext httpContext = httpServer.createContext(ROOT, new RootHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
