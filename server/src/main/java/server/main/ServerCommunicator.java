package server.main;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import server.ServerFacade;
import server.database.PluginRegistery;
import server.database.iPersistenceProvider;
import server.handler.CommandHandler;

public class ServerCommunicator {

    private HttpServer server;
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private ServerCommunicator(String pluginName) {
        ServerFacade serverFacade = ServerFacade._instance;
        iPersistenceProvider plugin = this.initializePlugin(pluginName);
        plugin.startTransaction();
//      serverFacade.restoreUsers(plugin.getUserDAO());
//      serverFacade.restoreGames(plugin.getGameDAO());
//      serverFacade.runCommands(plugin.getCommandDAO());
        plugin.endTransaction();
    }


    private iPersistenceProvider initializePlugin(String pluginName) {
        PluginRegistery r = new PluginRegistery();
        r.loadConfig(pluginName);
        return r.getPlugin();
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
        if (args.length < 1) {
            System.err.println("usage: ServerCommunicator.main <port>");
            return;
        }
        int port;
        String pluginName;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("port must be a number");
            return;
        }
        pluginName = args[1];

        new ServerCommunicator(pluginName).run(port);
    }
}
