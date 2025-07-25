package me.lunaiskey.anotherbadplugin.item;

import me.lunaiskey.anotherbadplugin.AnotherBadPlugin;
import me.lunaiskey.anotherbadplugin.item.attributes.ItemClickActions;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private static ItemManager manager;
    private Map<String,Item> itemMap = new HashMap<>();

    public static ItemManager get() {
        if (manager == null) {
            manager = new ItemManager();
            AnotherBadPlugin.get().getLogger().info("Item Manager has been initialized");
        }
        return manager;
    }

    private ItemManager() {
        registerDefaultItems();
    }

    private void registerDefaultItems() {
        registerItem(new Item("GOON_JUICE", Material.GHAST_TEAR).name("Goon Juice").rarity(Rarity.UNCOMMON));
        registerItem(new Item("CONCRETE_CUM",Material.WHITE_CONCRETE).name("Concrete Cum").rarity(Rarity.RARE));
        registerItem(new Item("SESAME_SEED_SEMEN", Material.DRAGON_BREATH).name("Sesame Seed Semen").rarity(Rarity.EPIC));
        registerItem(new Item("DADS_ASHES",Material.LIGHT_GRAY_DYE).name("Dad's Ashes").rarity(Rarity.LEGENDARY));
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


    public void onClick(PlayerInteractEvent event) {
        Item item = getItem(NBTUtil.getItemIdentifier(event.getItem()));
        if (item.getIdentifier() == null) return;
        if (item instanceof ItemClickActions action) {
            switch(event.getAction()) {
                case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                    if (event.getPlayer().isSneaking()) {
                        action.onLeftShiftClick(event);
                    } else {
                        action.onLeftClick(event);
                    }
                }
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    if (event.getPlayer().isSneaking()) {
                        action.onRightShiftClick(event);
                    } else {
                        action.onRightClick(event);
                    }
                }
            }
        }
    }

    public void onPlace(BlockPlaceEvent event) {
        Item item = getItem(NBTUtil.getItemIdentifier(event.getItemInHand()));
        if (item.getIdentifier() == null) return;
        if (!item.isPlaceable()) {
            event.setCancelled(true);
        }
    }
}
