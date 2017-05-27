package ru.spbau.mit.mob;


import ru.spbau.mit.inventory.Inventory;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.world.Tile;
import ru.spbau.mit.world.World;

import java.util.ArrayList;

public class Player extends Mob {
    private Inventory inventory;

    public Player(Point pos, Attributes attr, Inventory inv, ArrayList<String> mes, World world) {
        this.pos = pos;
        this.attr = attr;
        this.inventory = inv;
        this.messages = mes;
        this.world = world;
    }

    public void moveBy(int dx, int dy) {
        Mob other = world.checkMob(this.pos.getX() + dx, this.pos.getY() + dy);
        Item item = world.checkItem(this.pos.getX() + dx, this.pos.getY() + dy);
        if (dx == 0 && dy == 0) {
            if (item != null) {
                this.messages.add(item.getName() + "(dmg: " + item.getAttack() + "; def: " + item.getDefence() + "; hp: " + item.getHealth() + ")");
            }
            return;
        }
        if (other == null && item == null) {
            if (world.tile(this.pos.getX() + dx, this.pos.getY() + dy) == Tile.WALL) {
                world.dig(this.pos.getX() + dx, this.pos.getY() + dy);
                this.messages.add("The " + this.attr.getName() + " is digging");
            } else if (world.tile(this.pos.getX() + dx, this.pos.getY() + dy) == Tile.FLOOR) {
                pos.move(dx, dy);
            }
        } else if (other != null) {
            this.attack(other);
        } else {
            this.messages.add(item.getName() + "(dmg: " + item.getAttack() + "; def: " + item.getDefence() + "; hp: " + item.getHealth() + ")");
            pos.move(dx, dy);
        }
    }

    @Override
    public void update() {

    }

    public void addItem(Item item) {
        if (item != null && inventory.getSize() < inventory.getCapacity()) {
            inventory.add(item);
            this.attr.setAttackValue(this.attr.getAttackValue() + item.getAttack());
            this.attr.setDefenseValue(this.attr.getDefenseValue() + item.getDefence());
            this.attr.setMaxHP(this.attr.getMaxHP() + item.getHealth());
            this.attr.setCurrentHP(this.attr.getCurrentHP() + item.getHealth());
            messages.add(item.getName() + " is inserted into the cell");
            world.removeItem(item);
        } else {
            messages.add("No free cell for runes");
        }
    }

    public void removeItem(int index) {
        if (index < inventory.getSize()) {
            inventory.remove(index);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
