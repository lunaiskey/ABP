package me.lunaiskey.anotherbadplugin.item.commands;

import me.lunaiskey.anotherbadplugin.item.ItemManager;
import me.lunaiskey.anotherbadplugin.item.MinecraftItemStackCreator;
import me.lunaiskey.anotherbadplugin.random.CommandReturnHater;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandCustomItem extends CommandReturnHater {
    @Override
    public void command(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You have to be a Player to use this command!");
            return;
        }
        if (args.length == 0) {
            onSubCommandHelp(player);
            return;
        }
        if (args[0].equalsIgnoreCase("get")) {
            onSubCommandGet(player,args);
            return;
        }
    }

    private void onSubCommandHelp(Player player) {
        player.sendMessage(
                "CustomItem Command:",
                "| /customitem help",
                "| /customitem get <identifier>"
        );
    }

    private void onSubCommandGet(Player player, String[] args) {
        player.getInventory().addItem(MinecraftItemStackCreator.getMinecraftItemStackFromItem(ItemManager.get().getItem(args[1])));
    }


}
