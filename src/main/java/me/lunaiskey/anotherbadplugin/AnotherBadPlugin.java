package me.lunaiskey.anotherbadplugin;

import me.lunaiskey.anotherbadplugin.events.BlockEvents;
import me.lunaiskey.anotherbadplugin.events.PlayerEvents;
import me.lunaiskey.anotherbadplugin.item.ItemManager;
import me.lunaiskey.anotherbadplugin.item.commands.CommandCustomItem;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnotherBadPlugin extends JavaPlugin {

    private static AnotherBadPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        registerManagers();
        registerCommands();
        registerEvents();
        // Plugin startup logic
        this.getLogger().info("Started AnotherBadPlugin (ABP)");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AnotherBadPlugin get() {
        return instance;
    }

    private void registerManagers() {
        ItemManager.get();
    }
    private void registerCommands() {
        this.getCommand("customitem").setExecutor(new CommandCustomItem());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerEvents(),this);
        getServer().getPluginManager().registerEvents(new BlockEvents(), this);
    }

}
