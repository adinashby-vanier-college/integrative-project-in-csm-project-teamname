package com.example.theunknownvariable.Model;

public class Substance {
    private static final Substance instance = new Substance(); // Singleton instance

    private int substanceNb1;
    private int substanceNb2;

    // Constructor
    private Substance() {}

    // Public method to access the singleton instance
    public static Substance getInstance() {
        return instance;
    }

    public void setSubstanceNb1(int substanceNb) {
        this.substanceNb1 = substanceNb;
    }

    public void setSubstanceNb2(int substanceNb) {
        this.substanceNb2 = substanceNb;
    }

    public int getSubstanceNb1() {
        return this.substanceNb1;
    }

    public int getSubstanceNb2() {
        return this.substanceNb2;
    }
}
