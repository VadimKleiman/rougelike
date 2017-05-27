package ru.spbau.mit.mob;

import java.awt.*;

/**
 * Game characteristics of the mob
 */
public class Attributes {
    private String name;
    private char glyph;
    private Color color;
    private int maxHP;
    private int currentHP;
    private int attackValue;
    private int defenseValue;


    public Attributes(String name, char glyph, Color color, int maxHP, int attackValue, int defenseValue) {
        this.name = name;
        this.glyph = glyph;
        this.color = color;
        this.maxHP = maxHP;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
        this.currentHP = this.maxHP;
    }

    public char getGlyph() {
        return glyph;
    }

    public void setGlyph(char glyph) {
        this.glyph = glyph;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
