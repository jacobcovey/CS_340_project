package server.main;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import server.ServerFacade;
import shared.interfaces.iDatabaseFactory;
import server.handler.CommandHandler;
import server.model.ServerModelRoot;

public class ServerCommunicator {

    private HttpServer server;
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private ServerCommunicator() {

    }


    private ServerCommunicator(String pluginName) {
        ServerFacade._instance.initializePlugin(pluginName);
    }


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
        if (args.length < 1 || args.length == 2 || args.length > 4) {
            System.err.println("usage: ServerCommunicator.main <port> [<database> <increment> -wipe]");
            return;
        }
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("port must be a number");
            return;
        }
        if (args.length == 1) {
            new ServerCommunicator().run(port);
            return;
        }
        if (args.length > 1) {
            String pluginName = args[1];
            int incrementer;
            try {
                incrementer = Integer.parseInt(args[2]);
                ServerModelRoot.getInstance().setResetCountLimit(incrementer);
            } catch (NumberFormatException e) {
                System.err.println("increment must be a number");
                return;
            }
            ServerCommunicator server = new ServerCommunicator(pluginName);
            if (args.length == 4 && args[3].equals("-wipe")) {
                server.clearAllTables();
            } else {
                server.restoreTables();
            }
            server.run(port);
        }
    }

    private void clearAllTables() {
        iDatabaseFactory plugin = ServerFacade._instance.getPlugin();
        plugin.startTransaction();
        plugin.getUserDAO().clear();
        plugin.getGameDAO().clear();
        plugin.getCommandDAO().clear();
        plugin.endTransaction();
    }

    private void restoreTables() {
        iDatabaseFactory plugin = ServerFacade._instance.getPlugin();
        plugin.startTransaction();
        ServerFacade._instance.restoreUsers(plugin.getUserDAO().read());
        ServerFacade._instance.restoreGames(plugin.getGameDAO().read());
        ServerFacade._instance.runCommands(plugin.getCommandDAO().read());
        plugin.endTransaction();

    }
}
