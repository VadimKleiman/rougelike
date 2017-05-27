package ru.spbau.mit.inventory;


import ru.spbau.mit.mob.Point;

import java.awt.*;


/**
 * Game item
 */
public class Item {
    private Point pos;
    private int attack;
    private int defence;
    private int health;
    private String name;
    private Color color;
    private char glyph;

    public Item(String name, char glyph, Color color, int attack, int defence, int health, Point pos) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.glyph = glyph;
        this.color = color;
        this.pos = pos;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public char getGlyph() {
        return glyph;
    }

    public Point getPos() {
        return pos;
    }
}
