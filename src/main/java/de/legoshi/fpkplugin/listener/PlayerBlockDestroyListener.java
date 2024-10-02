package de.legoshi.fpkplugin.listener;

import de.legoshi.fpkplugin.manager.ItemClickManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class PlayerBlockDestroyListener implements Listener {

    private final ItemClickManager itemClickManager;

    @EventHandler
    public void onDestroy(BlockBreakEvent e) {

        if(itemClickManager.isCorrectItem(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
