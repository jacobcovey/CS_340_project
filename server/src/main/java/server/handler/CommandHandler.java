package server.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import server.commands.CommandManager;
import server.model.ServerModelRoot;
import shared.classes.CommandData;
import shared.interfaces.iCommand;

import static shared.classes.CommandData.Type.GETOUTSTANDINGCOMMANDS;
import static shared.classes.CommandData.Type.STARTGAME;
import static shared.classes.CommandData.Type.UPDATECURRENTGAME;
import static shared.classes.CommandData.Type.UPDATEGAMELIST;

public class CommandHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (!exchange.getRequestMethod().toLowerCase().equals("post")) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
                return;
            }

            Gson gson = new Gson();
            InputStream reqBody = exchange.getRequestBody();
            String serialized = readString(reqBody);
            CommandData commandData = gson.fromJson(serialized, CommandData.class);
            if (!commandData.getType().equals(GETOUTSTANDINGCOMMANDS) && !commandData.getType().equals(UPDATEGAMELIST) && !commandData.getType().equals(UPDATECURRENTGAME)) {System.out.println(commandData.getType());}
            CommandManager manager = new CommandManager();
            iCommand command = manager.createCommand(commandData, serialized);
            //deleted this v : commandData.getGameId() != null ||
            if (!commandData.getGameId().equals("") && commandData.getType() != STARTGAME) {
                ServerFacade._instance.saveCommand(commandData);
            }
            List<CommandData> result = command.execute();
//            Type commandDataListType zx2xz= new TypeToken<List<CommandData>>(){}.getType();
            String toClient = gson.toJson(result.toArray());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStream respBody = exchange.getResponseBody();
            writeString(toClient, respBody);
            respBody.close();

        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
