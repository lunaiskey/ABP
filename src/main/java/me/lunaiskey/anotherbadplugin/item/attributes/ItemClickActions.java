package me.lunaiskey.anotherbadplugin.item.attributes;

import org.bukkit.event.player.PlayerInteractEvent;

public interface ItemClickActions {

    void onLeftClick(PlayerInteractEvent event);

    void onLeftShiftClick(PlayerInteractEvent event);

    void onRightClick(PlayerInteractEvent event);

    void onRightShiftClick(PlayerInteractEvent event);
}
