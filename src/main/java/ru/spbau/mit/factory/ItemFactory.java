package ru.spbau.mit.factory;

import asciiPanel.AsciiPanel;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.world.World;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class for creating game items
 * */
public class ItemFactory {
    private World world;
    private ArrayList<String> itemTypes = new ArrayList<>(Arrays.asList("RuneOfPower", "RuneOfDefense", "RuneOfHealth"));

    public ItemFactory(World world) {
        this.world = world;
    }

    /**
     * The method creates a rune of a certain type
     * @param name Type of rune
     * @return item object or null if the object could not be created
     * */
    public Item getItem(String name) {
        final Random rnd = new Random();
        switch (name) {
            case "RuneOfPower":
                return new Item("Rune of power", '^', AsciiPanel.brightRed, rnd.nextInt(100), 0, 0, world.getEmptyLocation());
            case "RuneOfDefense":
                return new Item("Rune of defense", '#', AsciiPanel.brightCyan, 0, rnd.nextInt(100), 0, world.getEmptyLocation());
            case "RuneOfHealth":
                return new Item("Rune of health", '*', AsciiPanel.brightGreen, 0, 0, rnd.nextInt(100), world.getEmptyLocation());
        }
        return null;
    }
    /**
     * The method returns the available types of runes
     * */
    public ArrayList<String> getItemTypes() {
        return itemTypes;
    }
}
