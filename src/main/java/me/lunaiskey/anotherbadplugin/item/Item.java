package me.lunaiskey.anotherbadplugin.item;

import org.bukkit.Material;

public class Item {

    public static Item EMPTY = new Item();
    private String name = null;
    private String identifier = null;
    private Rarity rarity = null;
    private Material material = null;
    private boolean placeable = false;

    private Item() {

    }

    public Item(String identifier, Material material) {
        this.identifier = identifier.toUpperCase();
        this.material = material;
    }

    public Item name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
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

    public Material getMaterial() {
        return material;
    }

    public Item setPlaceable(boolean placeable) {
        this.placeable = placeable;
        return this;
    }

    public boolean isPlaceable() {
        return placeable;
    }
}
