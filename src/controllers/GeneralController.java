package controllers;

import com.company.Server;
import form.ServForm;
import listener.ServFormListener;
import models.Eat;
import models.Graminivorous;
import models.Grass;
import models.Predator;
import storage.DataStore;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import thread.*;
/**
 * Created by c-0k on 01.12.2016.
 */
public class GeneralController  {
    static ServForm servForm;
    static ServFormListener servFormListener;
    private static Server server;

    public static void startApp()  {
        try {
            DataStore.load();
            /*DataStore.getManager().create(new Predator(150, "Волк"));
            DataStore.getManager().create(new Graminivorous(70, "Заяц"));
            DataStore.getManager().create(new Grass(30, "Клевер"));*/
            server = Server.getServer();
            servForm = new ServForm();
            servFormListener = new ServFormListener(servForm);
            servForm.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void clientConnected(InetAddress ia, String name){
        servForm.getDetalsTextArea().setText(servForm.getDetalsTextArea().getText()
                + "Подключился клиент: " + name + ia + '\n');
    }
    public static void saveAndExit() {
        DataStore.save();
    }

    public static void stopServ() throws IOException {

        server.stopServer();
    }

    public static void startServ(String portS) throws IOException {
        int port = Integer.parseInt(portS);
        server.startServer(port);
    }
}
