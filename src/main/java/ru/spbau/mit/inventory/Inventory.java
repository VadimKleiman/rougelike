package ru.spbau.mit.inventory;

import java.util.ArrayList;

/**
 * Class for game inventory
 */
public class Inventory {
    private int capacity;
    private int size = 0;
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory() {
        capacity = 10;
    }

    /**
     * The method adds a rune to inventory
     *
     * @param item rune
     */
    public void add(Item item) {
        size++;
        items.add(item);
    }

    /**
     * The method removes the rune in inventory
     *
     * @param index index in ArrayList
     */
    public void remove(int index) {
        size--;
        items.remove(index);
    }

    /**
     * Current number of runes in inventory
     */
    public int getSize() {
        return size;
    }

    /**
     * Inventory capacity
     */
    public int getCapacity() {
        return capacity;
    }
}
