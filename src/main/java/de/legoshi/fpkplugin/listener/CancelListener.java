package de.legoshi.fpkplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class CancelListener implements Listener {

    @EventHandler
    public void onRedstone(BlockRedstoneEvent event) {
        event.setNewCurrent(0);
    }

    @EventHandler
    public void onSpread(BlockFromToEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeafDeca(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDecay(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(ItemSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onMelt(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent entitySpawnEvent) {
        entitySpawnEvent.setCancelled(true);
    }

    @EventHandler
    public void onVineGrow(BlockGrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onGrow(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

}
