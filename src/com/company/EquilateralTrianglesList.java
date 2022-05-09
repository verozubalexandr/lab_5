package com.company;

import java.util.ArrayList;
import java.util.List;

public class EquilateralTrianglesList {
    private final List<EquilateralTriangle> equilateralTrianglesList;

    public EquilateralTrianglesList() {
        equilateralTrianglesList = new ArrayList<EquilateralTriangle>();
    }

    public boolean add(EquilateralTriangle equilateralTriangle) {
        return equilateralTrianglesList.add(equilateralTriangle);
    }

    public double getAverageArea() {
        double averageArea = 0;
        for (EquilateralTriangle equilateralTriangle : equilateralTrianglesList) {
            averageArea += equilateralTriangle.getArea();
        }
        return averageArea / (equilateralTrianglesList.size());
    }

    public EquilateralTrianglesList countMoreThanAverage() {
        EquilateralTrianglesList result = new EquilateralTrianglesList();
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

    @Override
    public String toString() {
        return "average area -> " + Utils.formatDouble(getAverageArea())
                + "\n" +
                "more than average count -> " + countMoreThanAverage().getEquilateralTrianglesList().size()
                + "\n\n";
    }
}
