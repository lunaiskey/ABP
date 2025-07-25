package me.lunaiskey.anotherbadplugin.item.tasks;

import me.lunaiskey.anotherbadplugin.AnotherBadPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeFellerTask extends BukkitRunnable {

    private Set<Location> locations;
    private Material blockMat;

    public TreeFellerTask(Set<Location> locations, Material blockMat) {
        this.locations = locations;
        this.blockMat = blockMat;
    }

    private Set<Location> newLocations = new HashSet<>();
    private int totalblocks = 0;
    private boolean finished = false;
    private final int maxBlocks = 35;
    @Override
    public void run() {
        if (locations.isEmpty()) {
            this.cancel();
            AnotherBadPlugin.get().getLogger().info("Ran out of blocks to break, cancelling task...");
            return;
        }
        //AnotherBadPlugin.get().getLogger().info("TreeFellerTask: "+locations.size()+" Material: "+blockMat);
        AnotherBadPlugin.get().getLogger().info(""+locations.size());
        for (Location location : locations) {
            //AnotherBadPlugin.get().getLogger().info("checkpoint 1");
            if (totalblocks >= maxBlocks) {
                AnotherBadPlugin.get().getLogger().info("Max blocks have been reached, ending task.");
                this.cancel();
                return;
            }
            //AnotherBadPlugin.get().getLogger().info("checkpoint 2");
            //AnotherBadPlugin.get().getLogger().info("x: " + (location.getBlockX()) + "y: " + (location.getBlockY()) + "z: " + (location.getBlockX()) + "Material: " + location.getBlock().getType());
            for (int x = 0; x < 3; x++) {
                for (int z = 0; z < 3; z++) {
                    for (int y = 0; y < 3; y++) {
                        if (x == 1 && y == 1 && z == 1) continue;
                        Location newLoc = new Location(location.getWorld(), location.getBlockX()+x-1, location.getBlockY()+y-1, location.getBlockZ()+z-1);
                        Block newBlock = newLoc.getBlock();
                        //AnotherBadPlugin.get().getLogger().info("x: " + (newLoc.getBlockX()) + "y: " + (newLoc.getBlockY()) + "z: " + (newLoc.getBlockZ()) + "Material: " + newBlock.getType());
                        if (newBlock.getType() == blockMat) {
                            if (totalblocks >= maxBlocks) {
                                AnotherBadPlugin.get().getLogger().info("Max blocks have been reaches during the check process...");
                                location.getBlock().setType(Material.AIR);
                                return;
                            }
                            newLocations.add(newLoc);
                        }
                    }
                }
            }
            location.getBlock().setType(Material.AIR);
            totalblocks++;
        }
        locations.clear();
        locations.addAll(newLocations);
        newLocations.clear();
    }
}
