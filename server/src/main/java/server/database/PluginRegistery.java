package server.database;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
//            iPersistenceProvider p = null;
//            p = (iPersistenceProvider)classLoader.loadClass("com.server.")
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
