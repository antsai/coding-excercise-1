package com.myorg.codingexcercise;

/**
 * Created by atsai on 8/19/17.
 */
public class Shelf {
    private int shelfCuFt;
    private int availableCuFt;

    public Shelf(int shelfCuFt){
        this.shelfCuFt = shelfCuFt;
        this.availableCuFt = shelfCuFt;
    }

    public int getShelfCuFt() {return shelfCuFt;}

    public void setShelfCuFt(int shelfCuFt) {
        this.shelfCuFt = shelfCuFt;
    }

    public int getAvailableCuFt() {
        return availableCuFt;
    }

    public void setAvailableCuFt(int availableCuFt) {
        this.availableCuFt = availableCuFt;
    }
}
