package ru.spbau.mit.mob;


import ru.spbau.mit.world.World;

import java.util.ArrayList;

/**
 * Abstract class for game characters
 */
public abstract class Mob {
    protected Point pos;
    protected Attributes attr;
    protected ArrayList<String> messages;
    protected World world;

    public void attack(Mob other) {
        int amount = Math.max(0, this.attr.getAttackValue() - other.attr.getDefenseValue());
        amount = (int) (Math.random() * amount) + 1;
        messages.add(this.getAttr().getName() + " attack the " + other.attr.getName() + " for " + amount + " damage.");
        other.attr.setCurrentHP(other.attr.getCurrentHP() - amount);
    }

    public Point getPosition() {
        return pos;
    }

    public Attributes getAttr() {
        return attr;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void setAttr(Attributes attr) {
        this.attr = attr;
    }

    public void setMessages(ArrayList<String> mes) {
        this.messages = mes;
    }

    public ArrayList<String> getMessages(ArrayList<String> mes) {
        return mes;
    }


    abstract public void update();

}