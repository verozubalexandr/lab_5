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

public class TrianglesDatabase implements Serializable {
    private List<EquilateralTriangle> equilateralTrianglesList;

    public TrianglesDatabase() {
        equilateralTrianglesList = new ArrayList<EquilateralTriangle>();
    }

    public boolean add(EquilateralTriangle equilateralTriangle) {
        return equilateralTrianglesList.add(equilateralTriangle);
    }

    /**
     * Save data to file with given name (with Gson lib)
     *
     * @param {String} fileName — filename
     */
    public void save(String fileName) {
        Gson gson = new GsonBuilder().create();
        JsonArray json = gson.toJsonTree(equilateralTrianglesList).getAsJsonArray();
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
            this.equilateralTrianglesList = new Gson().fromJson(reader, new TypeToken<List<EquilateralTriangle>>() {
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
            out.writeObject(equilateralTrianglesList);
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
            this.equilateralTrianglesList = (ArrayList<EquilateralTriangle>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
        }
    }

    public double getAverageArea() {
        double averageArea = 0;
        for (EquilateralTriangle equilateralTriangle : equilateralTrianglesList) {
            averageArea += equilateralTriangle.getArea();
        }
        return averageArea / (equilateralTrianglesList.size());
    }

    public TrianglesDatabase countMoreThanAverage() {
        TrianglesDatabase result = new TrianglesDatabase();
        double average = getAverageArea();
        for (EquilateralTriangle equilateralTriangle : equilateralTrianglesList) {
            if (equilateralTriangle.getArea() > average) {
                result.add(equilateralTriangle);
            }
        }
        return result;
    }

    public EquilateralTriangle getEquilateralTriangleFromList(int index) {
        return equilateralTrianglesList.get(index);
    }

    public List<EquilateralTriangle> getEquilateralTrianglesList() {
        return equilateralTrianglesList;
    }

    public void clear() {
        this.equilateralTrianglesList.clear();
    }

    @Override
    public String toString() {
        return "average area -> " + Utils.formatDouble(getAverageArea())
                + "\n" +
                "more than average count -> " + countMoreThanAverage().getEquilateralTrianglesList().size()
                + "\n\n";
    }
}
