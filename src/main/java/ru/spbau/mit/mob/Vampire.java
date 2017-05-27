package ru.spbau.mit.mob;


import ru.spbau.mit.world.Tile;
import ru.spbau.mit.world.World;

import java.util.ArrayList;
import java.util.Random;

public class Vampire extends Mob {
    public Vampire(Point pos, Attributes attr, ArrayList<String> mes, World world) {
        this.pos = pos;
        this.attr = attr;
        this.messages = mes;
        this.world = world;
    }

    public void attack(Mob other) {
        int amount = Math.max(0, this.attr.getAttackValue() - other.attr.getDefenseValue());
        amount = (int) (Math.random() * amount) + 1;
        messages.add(this.getAttr().getName() + " attack the " + other.attr.getName() + " for " + amount + " damage.");
        other.attr.setCurrentHP(other.attr.getCurrentHP() - amount);
        this.attr.setCurrentHP(this.attr.getCurrentHP() + amount);
        if (this.attr.getCurrentHP() > this.attr.getMaxHP()) {
            this.attr.setCurrentHP(this.attr.getMaxHP());
        }
        messages.add(this.getAttr().getName() + " regenerated " + amount + " hp.");
    }

    public void moveBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        Mob other = world.checkMob(this.pos.getX() + dx, this.pos.getY() + dy);
        if (other == null) {
            if (world.tile(this.pos.getX() + dx, this.pos.getY() + dy) == Tile.WALL) {
                this.messages.add("The " + this.attr.getName() + " hit the wall");
            } else if (world.tile(this.pos.getX() + dx, this.pos.getY() + dy) == Tile.FLOOR) {
                pos.move(dx, dy);
            }
        } else {
            this.attack(other);
        }
    }


    @Override
    public void update() {
        final Random rnd = new Random();
        int dx = -3 + rnd.nextInt(7);
        int dy = -3 + rnd.nextInt(7);
        this.moveBy(dx, dy);
    }
}
