package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //amount of objects
        int amount = (2 + ((int) ((Math.random()) * 4)));

        //tasks
        //trianglesTask(amount);
        pyramidTask(amount);

    }

    private static void trianglesTask(int amount) {
        //init
        Scanner scanner = new Scanner(System.in);
        int trianglesIndex = 0;
        double triangleSide;
        EquilateralTriangles equilateralTriangles = new EquilateralTriangles(amount);

        //create first triangle(enter)
        do {
            System.out.print("Enter a triangle side -> ");
            triangleSide = scanner.nextDouble();
        } while (!Utils.isCorrectSide(triangleSide));
        equilateralTriangles.setEquilateralTriangle(new EquilateralTriangle(triangleSide), trianglesIndex++);
        //create other triangles(random)
        while (trianglesIndex < amount) {
            equilateralTriangles.setEquilateralTriangle(new EquilateralTriangle((1 + ((Math.random()) * 7))), trianglesIndex++);
        }

        //results
        while (trianglesIndex > 0) {
            System.out.println(equilateralTriangles.getEquilateralTriangle(--trianglesIndex).toString());
        }
        System.out.println(equilateralTriangles.toString());
    }

    private static void pyramidTask(int amount) {
        //init
        Scanner scanner = new Scanner(System.in);
        int pyramidsIndex = 0;
        double triangleSide;
        double apothem;
        Pyramids pyramids = new Pyramids(amount);

        //create first pyramid(enter)
        do {
            System.out.print("Enter a base side -> ");
            triangleSide = scanner.nextDouble();
            System.out.print("Enter an apothem -> ");
            apothem = scanner.nextDouble();
        } while (!Utils.isCorrectSide(triangleSide) && !Utils.isCorrectSide(apothem));
        pyramids.setPyramid(new Pyramid(apothem, triangleSide), pyramidsIndex++);
        //create other pyramids(random)
        while (pyramidsIndex < amount) {
            pyramids.setPyramid(new Pyramid((1 + ((Math.random()) * 7)), (1 + ((Math.random()) * 7))), pyramidsIndex++);
        }

        //results
        while (pyramidsIndex > 0) {
            System.out.println(pyramids.getPyramid(--pyramidsIndex).toString());
        }
        System.out.println(pyramids.toString());
    }
}
