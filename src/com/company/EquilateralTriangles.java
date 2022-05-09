package com.company;

public class EquilateralTriangles {
    EquilateralTriangle[] equilateralTriangles;

    public EquilateralTriangles(int amount) {
        equilateralTriangles = new EquilateralTriangle[amount];
    }

    public void setEquilateralTriangle(EquilateralTriangle equilateralTriangle, int index) {
        this.equilateralTriangles[index] = equilateralTriangle;
    }

    public EquilateralTriangle getEquilateralTriangle(int index) {
        return this.equilateralTriangles[index];
    }

    public double getAverageArea() {
        double averageArea = 0;
        for (EquilateralTriangle equilateralTriangle : equilateralTriangles) {
            averageArea += equilateralTriangle.getArea();
        }
        return averageArea / (equilateralTriangles.length);
    }

    public int countMoreThanAverage() {
        int counter = 0;
        double average = getAverageArea();
        for (EquilateralTriangle equilateralTriangle : equilateralTriangles) {
            if (equilateralTriangle.getArea() > average) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return "average area -> " + Utils.formatDouble(getAverageArea())
                + "\n" +
                "more than average count -> " + countMoreThanAverage()
                + "\n\n\n";
    }
}
