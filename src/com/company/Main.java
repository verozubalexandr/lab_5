package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //amount of objects
        int amount = (2 + ((int) ((Math.random()) * 4)));

        //tasks
        //trianglesTask(amount);
        //pyramidTask(amount);
    }

    private static void trianglesTask(int amount) {
        //init
        Scanner scanner = new Scanner(System.in);
        int trianglesIndex = 0;
        double triangleSide;
        final EquilateralTriangles equilateralTriangles = new EquilateralTriangles(amount);
        final EquilateralTrianglesList equilateralTrianglesList = new EquilateralTrianglesList();

        //create first triangle(enter)
        do {
            System.out.print("Enter a triangle side -> ");
            triangleSide = scanner.nextDouble();
        } while (!Utils.isCorrectSide(triangleSide));
        equilateralTriangles.setEquilateralTriangle(new EquilateralTriangle(triangleSide), trianglesIndex++);
        equilateralTrianglesList.add(new EquilateralTriangle(triangleSide));

        //create other triangles(random)
        while (trianglesIndex < amount) {
            triangleSide = (1 + ((Math.random()) * 7));
            equilateralTriangles.setEquilateralTriangle(new EquilateralTriangle(triangleSide), trianglesIndex++);
            equilateralTrianglesList.add(new EquilateralTriangle(triangleSide));
        }

        //results
        while (trianglesIndex > 0) {
            System.out.println(equilateralTriangles.getEquilateralTriangle(--trianglesIndex).toString());
            System.out.println(equilateralTrianglesList.getEquilateralTriangleFromList(trianglesIndex).toString());
        }
        System.out.println(equilateralTriangles.toString());
        System.out.println(equilateralTrianglesList.toString());
    }

    private static void pyramidTask(int amount) {
        //init
        Scanner scanner = new Scanner(System.in);
        int pyramidsIndex = 0;
        double triangleSide;
        double apothem;
        final Pyramids pyramids = new Pyramids(amount);
        final PyramidsList pyramidsList = new PyramidsList();

        //create first pyramid(enter)
        do {
            System.out.print("Enter a base side -> ");
            triangleSide = scanner.nextDouble();
            System.out.print("Enter an apothem -> ");
            apothem = scanner.nextDouble();
        } while (!Utils.isCorrectSide(triangleSide) && !Utils.isCorrectSide(apothem));
        pyramids.setPyramid(new Pyramid(apothem, triangleSide), pyramidsIndex++);
        pyramidsList.add(new Pyramid(apothem, triangleSide));

        //create other pyramids(random)
        while (pyramidsIndex < amount) {
            apothem = (7 + ((Math.random()) * 7));
            triangleSide = (1 + ((Math.random()) * 7));
            pyramids.setPyramid(new Pyramid(apothem, triangleSide), pyramidsIndex++);
            pyramidsList.add(new Pyramid(apothem, triangleSide));
        }

        //results
        while (pyramidsIndex > 0) {
            System.out.println(pyramids.getPyramid(--pyramidsIndex).toString());
            System.out.println(pyramidsList.getPyramidFromList(pyramidsIndex).toString());
        }
        System.out.println(pyramids.toString());
        System.out.println(pyramidsList.toString());
    }
}
