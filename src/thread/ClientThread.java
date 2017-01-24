package thread;

import models.Eat;
import controllers.GeneralController;
import storage.DataStore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import models.*;

/**
 * Created by c-0k on 05.12.2016.
 */
public class ClientThread implements Runnable {
    private Socket s;
    private String name;


    public ClientThread(Socket s) throws IOException {
        this.s = s;
        Thread t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {
        try {
            while (true) {
                final ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
                final ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
                //System.out.println("new thread ");
                int a = inputStream.readInt();
                //outputStream.writeInt(1);
                name = (String) inputStream.readObject();
                //outputStream.writeInt(1);

                switch (a) {
                    case 1: {  //получение всех сущностей

                        GeneralController.clientConnected(s.getInetAddress(), name);
                        HashSet<Eat> all = DataStore.getManager().getAll();
                        outputStream.writeInt(all.size());
                        for (Eat entity : all) {
                            outputStream.writeObject(entity);
                            System.out.println("Отправлена сущность:  " + entity.getName()
                                    + '\t' + entity.getWeight() + '\t' + entity.getId());
                        }
                        break;
                    }
                    case 2: { // получение возможной еды для сущности
                        System.out.println("case 2 ");
                        Eat sel = (Eat) inputStream.readObject();
                        HashSet<Eat> gen = null;
                        switch (sel.getGenus()) {
                            case "Хищник": {
                                gen = DataStore.getManager().getByGenus("Травоядное");
                                break;
                            }
                            case "Травоядное": {
                                gen = DataStore.getManager().getByGenus("Трава");
                                break;
                            }
                        }

                        if(gen != null) {
                            System.out.println(gen.size());
                            outputStream.writeInt(gen.size());
                            for (Eat entity : gen) {
                                outputStream.writeObject(entity);
                                System.out.println("Отправлена сущность:  " + entity.getName()
                                        + '\t' + entity.getWeight() + '\t' + entity.getId());
                            }
                        }
                        break;
                    }
                    case 3:{ //откл клиента
                        System.out.println("case 3");
                        return;

                    }
                    case 4:{ //создание
                        System.out.println("case 4");
                        Eat eat = (Eat) inputStream.readObject();
                        DataStore.getManager().create(eat);

                        break;
                    }
                    case 5:{ //съесть

                        break;
                    }
                }

            }


            //Eat item = (Eat)inputStream.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}