package server.main;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import server.ServerFacade;
import server.database.PluginRegistery;
import server.database.iDatabaseFactory;
import server.handler.CommandHandler;
import server.model.ServerModelRoot;

public class ServerCommunicator {

    private HttpServer server;
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private ServerCommunicator() {

    }

    private ServerCommunicator(String pluginName) {
        ServerFacade serverFacade = ServerFacade._instance;
        iDatabaseFactory plugin = this.initializePlugin(pluginName);
        plugin.startTransaction();
        serverFacade.restoreUsers(plugin.getUserDAO().read());
        serverFacade.restoreGames(plugin.getGameDAO().read());
        serverFacade.runCommands(plugin.getCommandDAO().read());
        plugin.endTransaction();
    }


    private iDatabaseFactory initializePlugin(String pluginName) {
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
        if (args.length < 1 || args.length == 2 || args.length > 3) {
            System.err.println("usage: ServerCommunicator.main <port> [<database> <increment>]");
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
            new ServerCommunicator(pluginName).run(port);
        }
    }
}
