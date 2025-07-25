package me.lunaiskey.anotherbadplugin.events;

import me.lunaiskey.anotherbadplugin.item.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemManager.get().onClick(event);
    }
}
