package com.company;

import java.util.ArrayList;
import java.util.List;

public class PyramidsList {
    private final List<Pyramid> pyramidsList;

    public PyramidsList() {
        pyramidsList = new ArrayList<Pyramid>();
    }

    public boolean add(Pyramid pyramid) {
        return pyramidsList.add(pyramid);
    }

    public EquilateralTriangle getPyramidFromList(int index) {
        return pyramidsList.get(index);
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

    @Override
    public String toString() {
        return "largest volume -> " + Utils.formatDouble(getLargestVolume()) +
                "\n\n\n";
    }
}
