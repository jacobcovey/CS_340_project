package server.main;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.handler.CommandHandler;

public class ServerCommunicator {

    private HttpServer server;
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private void run(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.setExecutor(null);
        server.createContext("/executecommand", new CommandHandler());
        server.start();
        System.out.format("Server running at: http://localhost:%d\n", port);
    }

    public static void main(String[] args) {
        new ServerCommunicator().run(3000);
    }
}
