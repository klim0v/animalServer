package com.company;

import thread.NetListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Properties;

import static com.company.Valid.valid;

/**
 * Created by c-0k on 15.12.2016.
 */
public class Server {
    public static String resourses = "/resources/config.properties";
    private Properties prop = new Properties();
    private static Server server = null;
    private int port;
    private ServerSocket ss;
    NetListener nl;
    private static int DEFAULT_PORT = -1;
    private HashSet<Socket> clients = new HashSet<Socket>();

    public static Server getServer() throws IOException {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    private Server() throws IOException {
        //prop.load(Server.class.getResourceAsStream(resourses));
        FileInputStream propertiesFile = null;
        try {
            propertiesFile = new FileInputStream(resourses);
            prop.load(propertiesFile);
            if (valid(prop.getProperty("PORT"))) {
                DEFAULT_PORT = Integer.parseInt(prop.getProperty("PORT"));
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Файл настроек не найден");
        } catch (IOException ex) {
            System.err.println("Ошибка чтения из файла настроек");
        }
        /*finally {
            try {
                assert propertiesFile != null;
                propertiesFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }*/
    }

    public void stopServer() throws IOException {
        nl.getT().interrupt();
        if (!nl.getSocketListener().isClosed()) {
            nl.getSocketListener().close();
        }
        ss.close();
    }

    public void startServer(int port) throws IOException {
        this.port = port;
        ss = new ServerSocket(port);
        nl = new NetListener(ss);
     }

    public static int getDefaultPort() {
        return DEFAULT_PORT;
    }
}
