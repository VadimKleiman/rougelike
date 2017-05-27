package ru.spbau.mit.factory;

import org.junit.Test;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.world.World;
import ru.spbau.mit.world.WorldBuilder;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ItemFactoryTest {
    private World world;
    private ArrayList<String> itemTypes = new ArrayList<>(Arrays.asList("RuneOfPower", "RuneOfDefense", "RuneOfHealth"));

    @Test
    public void getItemTest() throws Exception {
        createWorld();
        ItemFactory factory = new ItemFactory(world);
        Item item = factory.getItem("RuneOfPower");
        assertTrue(item != null);
    }

    @Test
    public void getItemTypesTest() throws Exception {
        createWorld();
        ItemFactory factory = new ItemFactory(world);
        assertEquals(factory.getItemTypes(), itemTypes);
    }

    private void createWorld() {
        world = new WorldBuilder(90, 32)
                .makeCaves()
                .build();
    }
}