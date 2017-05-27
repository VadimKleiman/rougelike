package ru.spbau.mit.mob;


import ru.spbau.mit.world.Tile;
import ru.spbau.mit.world.World;

import java.util.ArrayList;
import java.util.Random;

public class Orc extends Mob {
    public Orc(Point pos, Attributes attr, ArrayList<String> mes, World world) {
        this.pos = pos;
        this.attr = attr;
        this.messages = mes;
        this.world = world;
    }

    public void moveBy(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        Mob other = world.checkMob(this.pos.getX() + dx, this.pos.getY() + dy);
        if (other == null) {
            if (world.tile(this.pos.getX() + dx, this.pos.getY() + dy) == Tile.WALL) {
                world.dig(this.pos.getX() + dx, this.pos.getY() + dy);
                this.messages.add("The " + this.attr.getName() + " is digging");
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
        int dx = -1 + rnd.nextInt(3);
        int dy = -1 + rnd.nextInt(3);
        this.moveBy(dx, dy);
    }
}
