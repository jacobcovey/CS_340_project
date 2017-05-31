package com.example.jacobcovey.communication;

import com.example.jacobcovey.model.ClientFacade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import shared.classes.CommandData;
import shared.classes.User;

/**
 * Created by billrichards on 5/15/17.
 */

public class ClientCommunicator {

    private static Gson gson = new Gson();

    public static List<CommandData> sendToServer(CommandData commandData) {
        List<CommandData> data = null;
        try {
            String serverHost = "192.168.0.4";
            String serverPort = "4000";

            URL url = new URL("http://" + serverHost + ":" + serverPort + "/executecommand");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            User user = ClientFacade._instance.getUser();
            if (user != null) {
                String token = user.getAuthToken();
                http.setRequestProperty("authorization", token);
            }
            http.setDoOutput(true);

            OutputStreamWriter os = new OutputStreamWriter(http.getOutputStream());
            gson.toJson(commandData, CommandData.class, os);
            os.close();

            http.connect();
           InputStreamReader is = new InputStreamReader(http.getInputStream());
            Type listType = new TypeToken<ArrayList<CommandData>>(){}.getType();
            data = (List<CommandData>) gson.fromJson(is, listType);
//            System.out.println(data.to);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
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
}
