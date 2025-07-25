package me.lunaiskey.anotherbadplugin.item.items;

import com.destroystokyo.paper.MaterialTags;
import me.lunaiskey.anotherbadplugin.AnotherBadPlugin;
import me.lunaiskey.anotherbadplugin.item.Item;
import me.lunaiskey.anotherbadplugin.item.attributes.BlockBreak;
import me.lunaiskey.anotherbadplugin.item.attributes.ItemClickActions;
import me.lunaiskey.anotherbadplugin.item.tasks.TreeFellerTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemTreeFeller extends Item implements ItemClickActions, BlockBreak {
    public ItemTreeFeller(String identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event) {

    }

    @Override
    public void onLeftShiftClick(PlayerInteractEvent event) {

    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {

    }

    @Override
    public void onRightShiftClick(PlayerInteractEvent event) {

    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockMat = event.getBlock().getType();
        event.getPlayer().sendMessage(blockMat.name());
        if (!blockMat.name().endsWith("_LOG")) {
            return;
        }
        event.setCancelled(true);
        Set<Location> locations = new HashSet<>();
        locations.add(event.getBlock().getLocation());
        AnotherBadPlugin.get().getLogger().info("onBlockBreak: "+locations.size());
        new TreeFellerTask(locations,blockMat).runTaskTimer(AnotherBadPlugin.get(),0,2);
    }
}
