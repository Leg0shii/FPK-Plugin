package de.legoshi.fpkplugin.listener;

import de.legoshi.fpkplugin.manager.ItemClickManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

@RequiredArgsConstructor
public class PlayerBlockPlaceListener implements Listener {

    private final ItemClickManager itemClickManager;

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {

        if(itemClickManager.isCorrectItem(event.getPlayer())) {
            event.setCancelled(true);
        }

    }
}
