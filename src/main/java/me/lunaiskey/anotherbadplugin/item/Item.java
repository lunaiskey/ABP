package me.lunaiskey.anotherbadplugin.item;

import org.bukkit.Material;

public class Item {

    public static Item EMPTY = new Item();
    private String name = null;
    private String identifier = null;
    private Rarity rarity = null;
    private Material material = null;

    public Item name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item identifier(String identifier) {
        this.identifier = identifier.toUpperCase();
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Item rarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Item material(Material material) {
        this.material = material;
        return this;
    }

    public Material getMaterial() {
        return material;
    }
}
