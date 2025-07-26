package me.lunaiskey.anotherbadplugin.item.tasks;

import me.lunaiskey.anotherbadplugin.AnotherBadPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import oshi.util.tuples.Triplet;

import java.util.HashSet;
import java.util.Set;

public class TreeFellerTask extends BukkitRunnable {

    private Set<Triplet<Integer,Integer,Integer>> locations;
    private Material blockMat;
    private World world;

    public TreeFellerTask(Set<Triplet<Integer,Integer,Integer>> locations, Material blockMat, World world) {
        this.locations = locations;
        this.blockMat = blockMat;
        this.world = world;
    }

    private Set<Triplet<Integer,Integer,Integer>> newLocations = new HashSet<>();
    private int totalblocks = 0;
    private final int maxBlocks = 35;
    @Override
    public void run() {
        if (locations.isEmpty()) {
            this.cancel();
            AnotherBadPlugin.get().getLogger().info("Ran out of blocks to break, cancelling task...");
            return;
        }

        AnotherBadPlugin.get().getLogger().info(""+locations.size());
        for (Triplet<Integer,Integer,Integer> location : locations) {
            if (totalblocks >= maxBlocks) {
                AnotherBadPlugin.get().getLogger().info("Max blocks have been reached, ending task.");
                this.cancel();
                return;
            }
            Location coordLocation = new Location(world,location.getA(),location.getB(),location.getC());
            for (int x = 0; x < 3; x++) {
                for (int z = 0; z < 3; z++) {
                    for (int y = 0; y < 3; y++) {
                        if (x == 1 && y == 1 && z == 1) continue;
                        Triplet<Integer,Integer,Integer> coords = new Triplet<>(location.getA()+x-1,location.getB()+y-1,location.getC()+z-1);
                        Location newLoc = new Location(world, coords.getA(), coords.getB(), coords.getC());
                        Block newBlock = newLoc.getBlock();
                        if (newBlock.getType() == blockMat) {
                            if (totalblocks >= maxBlocks) {
                                AnotherBadPlugin.get().getLogger().info("Max blocks have been reaches during the check process...");
                                coordLocation.getBlock().setType(Material.AIR);
                                return;
                            }
                            newLocations.add(coords);
                        }
                    }
                }
            }
            Block a = coordLocation.getBlock();
            if (a.getType() == blockMat) {
                coordLocation.getBlock().setType(Material.AIR);
                totalblocks++;
            }
        }
        locations.clear();
        locations.addAll(newLocations);
        newLocations.clear();
    }
}
