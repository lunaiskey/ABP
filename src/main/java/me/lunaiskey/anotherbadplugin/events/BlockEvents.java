package me.lunaiskey.anotherbadplugin.events;

import me.lunaiskey.anotherbadplugin.item.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        ItemManager.get().onPlace(event);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        ItemManager.get().onBreak(event);
    }
}
