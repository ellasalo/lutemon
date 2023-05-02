package com.olio.lutemon.LutemonClasses;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class Storage {

    final private String FILENAME;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private static Storage storage = null;

    private Storage(){
        FILENAME = "lutemon.data";
    }

    public static Storage getInstance(){
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }


    public int returnIndex(String nick) {
        int i = 0;
        while (!lutemons.get(i).getNickname().equals(nick)) {
            i++;
        }
        return i;
    }

    public Lutemon getLutemonByNickName(String nick) {
        for (Lutemon lutemon: lutemons) {
            if (lutemon.getNickname().toLowerCase().equals(nick.toLowerCase())) {
                return lutemon;
            }
        }
        return null;
    }

    public void deleteLutemon(int i) {
        lutemons.remove(i);
    }

    public void saveLutemons(Context context) {
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();

        } catch (IOException e) {
            System.out.println("Lutemonien tallentaminen ei onnistunut.");
        }
    }

    public void loadLutemons(Context context) {
        ObjectInputStream lutemonReader = null;
        try {
            lutemonReader = new ObjectInputStream(context.openFileInput(FILENAME));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut");
            e.printStackTrace();
        }
    }
}
