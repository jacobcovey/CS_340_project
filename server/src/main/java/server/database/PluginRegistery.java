package server.database;

/**
 * Created by Dylan on 6/15/2017.
 */

public class PluginRegistery {

    iDatabaseFactory plugin;

    public void loadConfig(String pluginName)  {

        Class c = null;
        try {
            if (pluginName.equals("SQL")) {
                c = Class.forName("server.database.plugin.SQLDatabaseFactory");
            }
            else if (pluginName.equals("NoSQL")) {
                c = Class.forName("server.database.plugin.NoSQLDatabaseFactory");
            }
            else {
                c = Class.forName("server.database.plugin." + pluginName);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        iDatabaseFactory p = null;
        try {
            p = (iDatabaseFactory)c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.plugin = p;
    }

    public iDatabaseFactory getPlugin() {
        return plugin;
    }

//    public void rileyLoad(String pluginName) {
//        String dbPath = "database" + File.separator + pluginName;
//        File file = new File(dbPath);
//        try {
//            URL url = file.toURI().toURL();
//            URL[] urls = {url};
//
//            URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
//            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//            method.setAccessible(true);
//            method.invoke(classLoader, url);
//
//            iDatabaseFactory p = null;
//            p = (iDatabaseFactory)classLoader.loadClass("com.server.")
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
