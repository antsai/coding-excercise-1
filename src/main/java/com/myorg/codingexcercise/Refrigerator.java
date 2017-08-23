package com.myorg.codingexcercise;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Iterator;


/**
 *
 * You are about to build a Refrigerator which has SMALL, MEDIUM, and LARGE sized shelves.
 *
 * Method signature are given below. You need to implement the logic to
 *
 *  1. To keep track of items put in to the Refrigerator (add or remove)
 *  2. Make sure enough space available before putting it in
 *  3. Make sure space is used as efficiently as possible
 *  4. Make sure code runs efficiently
 *
 *
 * Created by kamoorr on 7/14/17.
 *
 * Please read README.md for my design idea
 */
public class Refrigerator {

    /**
     * Refrigerator Total Cubic Feet (CuFt)
     */
    private int cubicFt;

    /**
     * Large size shelf count and size of one shelf
     */
    private int largeShelfCount;
    private int largeShelfCuFt;

    /**
     * Medium size shelf count and size of one shelf
     */
    private int mediumShelfCount;
    //mediumShelfCuFt is not going to be used so it is removed

    /**
     * Medium size shelf count and size of one shelf
     */
    private int smallShelfCount;
    //smallShelfCuFt is not going to be used so it is removed

    PriorityQueue<Shelf> maxHeapLargeShelf;
    PriorityQueue<Shelf> maxHeapMediumShelf;
    PriorityQueue<Shelf> maxHeapSmallShelf;
    List<Item> items;
    private int usedSpace;
    /**
     *
     *  Create a new refrigerator by specifying shelfSize and count for SMALL, MEDIUM, LARGE shelves
     * @param largeShelfCount
     * @param largeShelfCuFt
     * @param mediumShelfCount
     * @param mediumShelfCuFt
     * @param smallShelfCount
     * @param smallShelfCuFt
     */
   public Refrigerator(int largeShelfCount, int largeShelfCuFt, int mediumShelfCount, int mediumShelfCuFt, int smallShelfCount, int smallShelfCuFt) {

       /**
        * Calculating total cuft as local variable to improve performance. Assuming no vacant space in the refrigerator
        *
        */
        this.cubicFt = (largeShelfCount * largeShelfCuFt) + (mediumShelfCount * mediumShelfCuFt) + (smallShelfCount* smallShelfCuFt);

        this.largeShelfCount = largeShelfCount;
        this.largeShelfCuFt = largeShelfCuFt;

        this.mediumShelfCount = mediumShelfCount;

        this.smallShelfCount = smallShelfCount;
       //initialize items, maxHeapLargeShelf, maxHeapMediumShelf, and maxHeapSmallShelf
       //Define number of large shelf, medium, and small shelves
       this.items = new LinkedList<>();
       this.maxHeapLargeShelf =  new PriorityQueue<>(4,new Comparator<Shelf>() {
           public int compare(Shelf o1, Shelf o2) {
               return o2.getAvailableCuFt() - o1.getAvailableCuFt()  ;
           }
       });
       this.maxHeapMediumShelf =  new PriorityQueue<>(4,new Comparator<Shelf>() {
           public int compare(Shelf o1, Shelf o2) {
               return o2.getAvailableCuFt() - o1.getAvailableCuFt()  ;
           }
       });
       this.maxHeapSmallShelf =  new PriorityQueue<>(4,new Comparator<Shelf>() {
           public int compare(Shelf o1, Shelf o2) {
               return o2.getAvailableCuFt() - o1.getAvailableCuFt()  ;
           }
       });

       for(int i =0; i< this.largeShelfCount; i++){
           maxHeapLargeShelf.add(new Shelf(largeShelfCuFt));      }

       for(int i =0; i< this.mediumShelfCount; i++){
           maxHeapMediumShelf.add(new Shelf(mediumShelfCuFt));       }

       for(int i =0; i< this.smallShelfCount; i++){
           maxHeapSmallShelf.add(new Shelf(smallShelfCuFt));       }

       usedSpace= 0;

   }

    /**
     * Implement logic to put an item to this refrigerator. Make sure
     *  -- You have enough vacant space in the refrigerator
     *  -- Make this action efficient in a way to increase maximum utilization of the space, re-arrange items when necessary
     *
     * Return
     *      true if put is successful
     *      false if put is not successful, for example, if you don't have enough space any shelf, even after re-arranging
     *
     *
     * @param item
     */
    public boolean put(Item item) {

        if(item.getCubicFt() < this.largeShelfCuFt){
            //check whether large shelf has available space for large item and small item
            if(this.maxHeapLargeShelf.peek().getAvailableCuFt() >= item.getCubicFt()){
                Shelf largShelf = this.maxHeapLargeShelf.poll();// poll from shelves
                largShelf.setAvailableCuFt(largShelf.getAvailableCuFt()-item.getCubicFt());
                this.maxHeapLargeShelf.add(largShelf);
                this.usedSpace+=item.getCubicFt();
                item.setShelf(largShelf);
                this.items.add(item);
                return true;
             //check small shelf has available space for small item
            }else if(this.maxHeapSmallShelf.peek().getAvailableCuFt() >= item.getCubicFt()){
                Shelf smallShelf = this.maxHeapSmallShelf.poll();// poll from shelves
                smallShelf.setAvailableCuFt(smallShelf.getAvailableCuFt()-item.getCubicFt());
                this.maxHeapSmallShelf.add(smallShelf);
                this.usedSpace+=item.getCubicFt();
                item.setShelf(smallShelf);
                this.items.add(item);
                return true;
             //check medium shelf has available space for small and medium item
            }else  if(this.maxHeapMediumShelf.peek().getAvailableCuFt() >= item.getCubicFt()) {
                Shelf mediumShelf = this.maxHeapMediumShelf.poll();// poll from shelves
                mediumShelf.setAvailableCuFt(mediumShelf.getAvailableCuFt() - item.getCubicFt());
                this.maxHeapMediumShelf.add(mediumShelf);
                this.usedSpace += item.getCubicFt();
                item.setShelf(mediumShelf);
                this.items.add(item);
                return true;
            }
        }

        return false;
    }


    /**
     * remove and return the requested item
     * Return null when not available
     * @param itemId
     * @return
     */
    public Item get(String itemId) {
        //First of all, we gonna find the item
        Iterator<Item> itemIterator = this.items.iterator();
        Item foundItem;
        Shelf currentShelf;
        //Traverse items to find the desired item
        while(itemIterator.hasNext()){
            Item nextItem = itemIterator.next();
            //Desired item is found
            if(itemId.equalsIgnoreCase(nextItem.getItemId())){
                foundItem = nextItem;
                currentShelf = foundItem.getShelf();
                //Remove found item from either large,medium, or small shelves
                if(this.maxHeapLargeShelf.remove(currentShelf)){
                    currentShelf.setAvailableCuFt(currentShelf.getAvailableCuFt() + foundItem.getCubicFt());
                    this.maxHeapLargeShelf.add(currentShelf);
                    items.remove(foundItem);
                    usedSpace-=foundItem.getCubicFt();
                    //After found item is removed, resting items need to re-arrange so that space is used efficiently
                    reArrangeItemsInShelf();
                    return foundItem;
                }else if(this.maxHeapMediumShelf.remove(currentShelf)){
                    currentShelf.setAvailableCuFt(currentShelf.getAvailableCuFt() + foundItem.getCubicFt());
                    this.maxHeapMediumShelf.add(currentShelf);
                    items.remove(foundItem);
                    usedSpace-=foundItem.getCubicFt();
                    reArrangeItemsInShelf();
                    return foundItem;
                }else if(this.maxHeapSmallShelf.remove(currentShelf)){
                    currentShelf.setAvailableCuFt(currentShelf.getAvailableCuFt() + foundItem.getCubicFt());
                    this.maxHeapSmallShelf.add(currentShelf);
                    items.remove(foundItem);
                    usedSpace-=foundItem.getCubicFt();
                    reArrangeItemsInShelf();
                    return foundItem;
                }
            }
        }

        return null;

    }

    //Creating a private re-arrange function to move resting items to right space
    private void reArrangeItemsInShelf(){
        Iterator itemIterator = this.items.iterator();
        while(itemIterator.hasNext()){
            Item nextItem = (Item)itemIterator.next();

                Shelf currShelf = nextItem.getShelf();

                if( this.maxHeapLargeShelf.peek().getAvailableCuFt() >= nextItem.getCubicFt() && this.maxHeapLargeShelf.contains(currShelf)){
                    this.maxHeapLargeShelf.remove(currShelf);
                    currShelf.setAvailableCuFt(currShelf.getAvailableCuFt() + nextItem.getCubicFt());
                    this.maxHeapLargeShelf.add(currShelf);
                    usedSpace-=nextItem.getCubicFt();
                    this.put(nextItem);
                }else if( this.maxHeapMediumShelf.peek().getAvailableCuFt() >= nextItem.getCubicFt() &&this.maxHeapMediumShelf.contains(currShelf)) {
                    this.maxHeapMediumShelf.remove(currShelf);
                    currShelf.setAvailableCuFt(currShelf.getAvailableCuFt() + nextItem.getCubicFt());
                    this.maxHeapMediumShelf.add(currShelf);
                    usedSpace -= nextItem.getCubicFt();
                    this.put(nextItem);
                }

        }

    }

    /**
     * Return current utilization of the space
     * @return
     */
    public float getUtilizationPercentage() {
        return (this.usedSpace * 100.0f)/this.cubicFt;
    }

    /**
     * Return current utilization in terms of cuft
     * @return
     */
    public int getUsedSpace() {
        return this.usedSpace;
    }



}
