package com.company;

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

    public double largestVolume() {
        double largestVolume = this.pyramids[0].getVolume();
        for (Pyramid pyramid : pyramids) {
            if (largestVolume < pyramid.getVolume()) {
                largestVolume = pyramid.getVolume();
            }
        }
        return largestVolume;
    }

    @Override
    public String toString() {
        return "largest volume -> " + Utils.formatDouble(largestVolume())
                + "\n\n\n";
    }
}
