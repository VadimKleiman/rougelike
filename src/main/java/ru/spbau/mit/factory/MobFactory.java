package ru.spbau.mit.factory;

import asciiPanel.AsciiPanel;
import ru.spbau.mit.inventory.Inventory;
import ru.spbau.mit.mob.*;
import ru.spbau.mit.world.World;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for creating mobs
 * */
public class MobFactory {
    private World world;
    public ArrayList<String> mobTypes = new ArrayList<>(Arrays.asList("orc", "troll", "zombie", "vampire"));

    public MobFactory(World world) {
        this.world = world;
    }

    /**
     * The method creates a mob of a certain type
     * @param name Type of mob
     * @return mob object or null if the object could not be created
     * */
    public Mob getMob(String name, ArrayList<String> messages) {
        Attributes attr;
        switch (name) {
            case "player":
                attr = new Attributes(
                        "player",
                        '@',
                        AsciiPanel.brightWhite,
                        100,
                        20,
                        5);

                return new Player(world.getEmptyLocation(), attr, new Inventory(), messages, world);
            case "orc":
                attr = new Attributes(
                        "orc",
                        'o',
                        AsciiPanel.green,
                        200,
                        40,
                        20);
                return new Orc(world.getEmptyLocation(), attr, messages, world);
            case "troll":
                attr = new Attributes(
                        "troll",
                        't',
                        AsciiPanel.brightBlue,
                        70,
                        20,
                        15);
                return new Troll(world.getEmptyLocation(), attr, messages, world);
            case "zombie":
                attr = new Attributes(
                        "zombie",
                        'z',
                        AsciiPanel.white,
                        60,
                        10,
                        5);
                return new Zombie(world.getEmptyLocation(), attr, messages, world);
            case "vampire":
                attr = new Attributes(
                        "vampire",
                        'v',
                        AsciiPanel.red,
                        500,
                        70,
                        50);
                return new Vampire(world.getEmptyLocation(), attr, messages, world);
        }
        return null;
    }

    /**
     * The method returns the available types of mobs
     * */
    public ArrayList<String> getMobTypes() {
        return mobTypes;
    }
}
