package server.main;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

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

        try {
            System.out.println("Server running at: http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port);
            System.out.printf("Address: " + InetAddress.getLocalHost().getHostAddress() + "\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.err.println("usage: ServerCommunicator.main <port>");
//            return;
//        }
//        int port;
//        try {
//            port = Integer.parseInt(args[0]);
//        } catch (NumberFormatException e) {
//            System.err.println("port must be a number");
//            return;
//        }
//        new ServerCommunicator().run(port);
        new ServerCommunicator().run(4000);
    }
}
