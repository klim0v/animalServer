package thread;

import controllers.GeneralController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by c-0k on 11.12.2016.
 */
public class NetListener implements Runnable {

    private ServerSocket socketListener;
    private Thread t;

    public NetListener(ServerSocket socketListener) {
        this.socketListener = socketListener;
        this.t = new Thread(this);
        this.t.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket client = null;
                System.out.println("Ожидается клиент...");


                    client = socketListener.accept(); // не дает остановить сервер



        //        System.out.println("Подключился клиент: "+ client.getInetAddress());
          //      GeneralController.clientConnected(client.getInetAddress(), "new");
                new ClientThread(client); //Создаем новый поток, которому передаем сокет

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getSocketListener() {
        return socketListener;
    }

    public void setSocketListener(ServerSocket socketListener) {
        this.socketListener = socketListener;
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }
}
