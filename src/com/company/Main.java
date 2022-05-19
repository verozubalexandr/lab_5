package com.company;

import com.classes.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //amount of objects
        int amount = (2 + ((int) ((Math.random()) * 4)));

        //trianglesTask(amount);
        //pyramidTask(amount);
    }

    private static void trianglesTask(int amount) throws IOException {
        //init
        int trianglesIndex = 0;
        int method = 0;
        final EquilateralTriangles equilateralTriangles = new EquilateralTriangles(amount);
        final TrianglesDatabase trianglesDatabase = new TrianglesDatabase();


        if (getLastSession("./src/database/last-session.txt") == null) {
            setTriangleInfo(trianglesDatabase, trianglesIndex, equilateralTriangles, amount);
            printAllObjects(trianglesDatabase);
            closeProgram(trianglesDatabase, method);
        } else {
            File file = new File(getLastSession("./src/database/last-session.txt"));
            try {
                if (file.exists()) {
                    if (method == 0) {
                        trianglesDatabase.deserialize(getLastSession("./src/database/last-session.txt"));
                    } else {
                        trianglesDatabase.load(getLastSession("./src/database/last-session.txt"));
                    }
                } else {
                    setTriangleInfo(trianglesDatabase, trianglesIndex, equilateralTriangles, amount);
                }
                printAllObjects(trianglesDatabase);
                closeProgram(trianglesDatabase, method);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void pyramidTask(int amount) throws IOException {
        //init
        int pyramidsIndex = 0;
        final Pyramids pyramids = new Pyramids(amount);
        final PyramidsDatabase pyramidsDatabase = new PyramidsDatabase();
        int method = 0;


        if (getLastSession("./src/database/last-session.txt") == null) {
            setPyramidInfo(pyramidsDatabase, pyramidsIndex, pyramids, amount);
            printAllObjects(pyramidsDatabase);
            closeProgram(pyramidsDatabase, method);
        } else {
            File file = new File(getLastSession("./src/database/last-session.txt"));
            try {
                if (file.exists()) {
                    if (method == 0) {
                        pyramidsDatabase.deserialize(getLastSession("./src/database/last-session.txt"));
                    } else {
                        pyramidsDatabase.load(getLastSession("./src/database/last-session.txt"));
                    }
                } else {
                    setPyramidInfo(pyramidsDatabase, pyramidsIndex, pyramids, amount);
                }
                printAllObjects(pyramidsDatabase);
                closeProgram(pyramidsDatabase, method);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Stop the program and write a new session file
     *
     * @param {TrianglesDatabase || PyramidsDatabase} trianglesDatabase || pyramidsDatabase — Objects
     * @param {int}              method -> (0 || 1) {0 - JAVA Native, 1 - Gson lib}
     */
    private static void closeProgram(TrianglesDatabase trianglesDatabase, int method) {
        String timeStamp = (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date())).replaceAll("[-.?!)(,:]", "-");

        try (FileWriter writer = new FileWriter("./src/database/last-session.txt", false)) {
            if (method == 0) {
                writer.write("./src/database/" + timeStamp + "--native.json");
                trianglesDatabase.serialize("./src/database/" + timeStamp + "--native.json");
            } else {
                writer.write("./src/database/" + timeStamp + "--gson.json");
                trianglesDatabase.save("./src/database/" + timeStamp + "--gson.json");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.exit(0);
    }

    private static void closeProgram(PyramidsDatabase pyramidsDatabase, int method) {
        String timeStamp = (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date())).replaceAll("[-.?!)(,:]", "-");

        try (FileWriter writer = new FileWriter("./src/database/last-session.txt", false)) {
            if (method == 0) {
                writer.write("./src/database/" + timeStamp + "--native.json");
                pyramidsDatabase.serialize("./src/database/" + timeStamp + "--native.json");
            } else {
                writer.write("./src/database/" + timeStamp + "--gson.json");
                pyramidsDatabase.save("./src/database/" + timeStamp + "--gson.json");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.exit(0);
    }

    /**
     * retrieve the name of the last saved session file
     *
     * @param {String} fileName — local filepath
     * @returns {String}
     */
    private static String getLastSession(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String s = br.readLine();
        br.close();
        return s;
    }

    private static void setTriangleInfo(TrianglesDatabase trianglesDatabase,
                                        int trianglesIndex,
                                        EquilateralTriangles equilateralTriangles,
                                        int amount) {
        //init
        Scanner scanner = new Scanner(System.in);
        double triangleSide;


        do {
            System.out.print("Enter a triangle side -> ");
            triangleSide = scanner.nextDouble();
        } while (!Utils.isCorrectSide(triangleSide));
        equilateralTriangles.setEquilateralTriangle(new EquilateralTriangle(triangleSide), trianglesIndex++);
        trianglesDatabase.add(new EquilateralTriangle(triangleSide));

        //create other triangles(random)
        while (trianglesIndex < amount) {
            triangleSide = (1 + ((Math.random()) * 7));
            equilateralTriangles.setEquilateralTriangle(new EquilateralTriangle(triangleSide), trianglesIndex++);
            trianglesDatabase.add(new EquilateralTriangle(triangleSide));
        }
    }

    private static void setPyramidInfo(PyramidsDatabase pyramidsDatabase,
                                       int pyramidsIndex,
                                       Pyramids pyramids,
                                       int amount) {
        //init
        Scanner scanner = new Scanner(System.in);
        double apothem;
        double triangleSide;


        do {
            System.out.print("Enter a base side -> ");
            triangleSide = scanner.nextDouble();
            System.out.print("Enter an apothem -> ");
            apothem = scanner.nextDouble();
        } while (!Utils.isCorrectSide(triangleSide) && !Utils.isCorrectSide(apothem));
        pyramids.setPyramid(new Pyramid(apothem, triangleSide), pyramidsIndex++);
        pyramidsDatabase.add(new Pyramid(apothem, triangleSide));

        //create other pyramids(random)
        while (pyramidsIndex < amount) {
            apothem = (7 + ((Math.random()) * 7));
            triangleSide = (1 + ((Math.random()) * 7));
            pyramids.setPyramid(new Pyramid(apothem, triangleSide), pyramidsIndex++);
            pyramidsDatabase.add(new Pyramid(apothem, triangleSide));
        }
    }

    private static void printAllObjects(PyramidsDatabase pyramidsDatabase) {
        for (int i = 0; i < pyramidsDatabase.getPyramidList().size(); i++) {
            System.out.println(pyramidsDatabase.getPyramidList().get(i));
        }
    }

    private static void printAllObjects(TrianglesDatabase trianglesDatabase) {
        for (int i = 0; i < trianglesDatabase.getEquilateralTrianglesList().size(); i++) {
            System.out.println(trianglesDatabase.getEquilateralTrianglesList().get(i));
        }
    }
}
