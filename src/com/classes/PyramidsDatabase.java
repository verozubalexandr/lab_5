package com.classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PyramidsDatabase implements Serializable {
    private List<Pyramid> pyramidsList;

    public PyramidsDatabase() {
        pyramidsList = new ArrayList<Pyramid>();
    }

    public boolean add(Pyramid pyramid) {
        return pyramidsList.add(pyramid);
    }

    public EquilateralTriangle getPyramidFromList(int index) {
        return pyramidsList.get(index);
    }

    /**
     * Save data to file with given name (with Gson lib)
     *
     * @param {String} fileName — filename
     */
    public void save(String fileName) {
        Gson gson = new GsonBuilder().create();
        JsonArray json = gson.toJsonTree(pyramidsList).getAsJsonArray();
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(json.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Retrieving data from a file into an object (with Gson lib)
     *
     * @param {String} fileName — filename
     */
    public void load(String fileName) {
        this.clear();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            this.pyramidsList = new Gson().fromJson(reader, new TypeToken<List<Pyramid>>() {
            }.getType());

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Save data to file with given name (JAVA Native)
     *
     * @param {String} fileName — filename
     */
    public void serialize(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(pyramidsList);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieving data from a file into an object (JAVA Native)
     *
     * @param {String} fileName — filename
     */
    public void deserialize(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.pyramidsList = (ArrayList<Pyramid>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
        }
    }

    public List<Pyramid> getPyramidList() {
        return pyramidsList;
    }

    public double getLargestVolume() {
        double largestVolume = this.pyramidsList.get(0).getVolume();
        for (int i = 1; i < pyramidsList.size(); i++) {
            if (largestVolume < pyramidsList.get(i).getVolume()) {
                largestVolume = pyramidsList.get(i).getVolume();
            }
        }
        return largestVolume;
    }

    /**
     * Clear this. ArrayList<>
     */
    public void clear() {
        this.pyramidsList.clear();
    }

    @Override
    public String toString() {
        return "largest volume -> " + Utils.formatDouble(getLargestVolume()) +
                "\n\n\n";
    }
}
