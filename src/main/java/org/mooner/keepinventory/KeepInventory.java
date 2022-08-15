package org.mooner.keepinventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventory extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Enabled!");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin Disabled!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        e.getDrops().clear();
        e.setKeepInventory(true);
        if(e.getEntity().isOp()) {
            e.setKeepLevel(true);
            return;
        }
        e.setDroppedExp(0);
        e.setNewExp(0);
        e.setNewLevel(0);
        e.setNewTotalExp(0);
    }

    @EventHandler
    public void onExplode(BlockExplodeEvent e) {
        e.blockList().clear();
    }

    @EventHandler
    public void onExplodeEntity(EntityExplodeEvent e) {
        e.blockList().clear();
    }
}
