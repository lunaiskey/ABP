package me.lunaiskey.anotherbadplugin.random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandReturnHater implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull []args) {
        command(sender,command,label,args);
        return true;
    }

    public void command(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull []args) {

    }


}
