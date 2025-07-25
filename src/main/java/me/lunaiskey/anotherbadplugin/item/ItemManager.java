package me.lunaiskey.anotherbadplugin.item;

import me.lunaiskey.anotherbadplugin.AnotherBadPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private static ItemManager manager;
    private Map<String,Item> itemMap = new HashMap<>();

    public static ItemManager get() {
        if (manager == null) {
            manager = new ItemManager();
        }
        AnotherBadPlugin.get().getLogger().info("Item Manager has been initialized");
        return manager;
    }

    private ItemManager() {
        registerDefaultItems();
    }

    private void registerDefaultItems() {
        registerItem(new Item().identifier("GOON_JUICE").name("Goon Juice").rarity(Rarity.UNCOMMON).material(Material.GHAST_TEAR));
    }

    private void registerItem(Item item) {
        if (item == null) return;
        if (item.getIdentifier() == null) {
            AnotherBadPlugin.get().getLogger().warning("An item with a Null Item Identifier was attempting to register, Skipping...");
            return;
        }
        if (itemMap.containsKey(item.getIdentifier())) {
            AnotherBadPlugin.get().getLogger().warning("Item ("+item.getIdentifier()+") already exists, Skipping...");
        }
        if (item.getName() == null) {
            item.name("null");
            //Check Vanilla item names later.
        }
        if (item.getRarity() == null) {
            item.rarity(Rarity.COMMON);
        }
        itemMap.put(item.getIdentifier(),item);
    }

    public Item getItem(String identifier) {
        if (identifier == null) return Item.EMPTY;
        String identifierUpper = identifier.toUpperCase();
        return itemMap.get(identifierUpper);
    }


}
