package server.database;

/**
 * Created by Dylan on 6/15/2017.
 */

public class PluginRegistery {

    iPersistenceProvider plugin;

    public void loadConfig(String pluginName)  {

        Class c = null;
        try {
            c = Class.forName("server.database.plugin." + pluginName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        iPersistenceProvider p = null;
        try {
            p = (iPersistenceProvider)c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.plugin = p;
    }

    public iPersistenceProvider getPlugin() {
        return plugin;
    }

}
