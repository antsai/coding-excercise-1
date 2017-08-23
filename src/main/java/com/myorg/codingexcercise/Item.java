package com.myorg.codingexcercise;

/**
 *
 * Created by kamoorr on 7/14/17.
 */
public class Item {

    private String itemId;
    private int cubicFt;
    private Shelf shelf;

    public Item(String itemId, int cubicFt) {
        this.itemId = itemId;
        this.cubicFt = cubicFt;
    }

    public String getItemId() {
        return itemId;
    }
    
    public int getCubicFt() {
    	return cubicFt;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setCubicFt(int cubicFt) {
        this.cubicFt = cubicFt;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
}