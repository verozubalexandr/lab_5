package com.classes;

public class Pyramids {
    Pyramid[] pyramids;

    public Pyramids(int amount) {
        pyramids = new Pyramid[amount];
    }

    public void setPyramid(Pyramid pyramid, int index) {
        this.pyramids[index] = pyramid;
    }

    public Pyramid getPyramid(int index) {
        return this.pyramids[index];
    }

    public double getLargestVolume() {
        double largestVolume = this.pyramids[0].getVolume();
        for (int i = 1; i < pyramids.length; i++) {
            if (largestVolume < pyramids[i].getVolume()) {
                largestVolume = pyramids[i].getVolume();
            }
        }
        return largestVolume;
    }

    @Override
    public String toString() {
        return "largest volume -> " + Utils.formatDouble(getLargestVolume())
                + "\n\n\n";
    }
}
