package storage;


import models.Animal;
import models.Eat;
import models.Grass;

import java.io.*;
import java.util.HashSet;

public class DataStore implements Serializable {
    private static DataStore manager = null;
    private static final String fileName = "data.bin";
    private static final String fileNameCounter = "counter.bin";
    private static Integer COUNTER = 0;
    private HashSet<Eat> essence = new HashSet<Eat>();

    public static DataStore getManager(){
        if (manager == null) {
            System.out.println("getManager():   manager == null true");
            manager = new DataStore();
        }
        return manager;
    }

    private DataStore(){}

    public  Eat create (Eat eat){
        eat.setId(COUNTER++);
        essence.add(eat);
        return eat;
    }

    public void replace (Eat eat) throws Exception{
        if (!essence.contains(eat))
            throw new Exception();
        essence.remove(eat);
        essence.add(eat);
    }


    public Eat delete (Eat eat) throws Exception{
        if (!essence.contains(eat))
            throw new Exception();
        essence.remove(eat);
        return eat;
    }

    public Eat getById(int id) {
        for (Eat entry: this.getAll()) {
            if ( id == entry.getId() )
                return entry;
        }
        return null;
    }

    public HashSet<Eat> getByGenus(String objectClass) {
        HashSet<Eat> c = new HashSet<Eat>();
        for (Eat entry: this.getAll()) {
            if (objectClass.equals(entry.getGenus()))
                c.add(entry);
        }
        return c;
    }

    public HashSet<Eat> getByAlive(boolean alive) {
        HashSet<Eat> c = new HashSet<Eat>();
        for (Eat entry: this.getAll()) {
            if (!(entry instanceof Grass))
                if(alive == ((Animal)entry).isAlive())
                c.add(entry);
        }
        return c;
    }

    public static void save(){
        try {
            FileOutputStream fileOs = new FileOutputStream(fileName);
            ObjectOutputStream dataOs = new ObjectOutputStream(fileOs);
            dataOs.writeObject(manager);
            dataOs.close();
            fileOs = new FileOutputStream(fileNameCounter);
            dataOs = new ObjectOutputStream(fileOs);
            dataOs.writeObject(COUNTER);
            dataOs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() throws Exception {

        FileInputStream fileOs = new FileInputStream(fileName);
        ObjectInputStream dataOs = new ObjectInputStream(fileOs);
        manager = (DataStore)dataOs.readObject();
        dataOs.close();
        fileOs = new FileInputStream(fileNameCounter);
        dataOs = new ObjectInputStream(fileOs);
        COUNTER = (Integer)dataOs.readObject();
        dataOs.close();

    }

    public HashSet<Eat> getAll() {
        return essence;
    }

    private void setEssence(HashSet<Eat> essence) {
        this.essence = essence;
    }


}
