package de.legoshi.fpkplugin.listener;

import de.legoshi.fpkplugin.manager.ItemClickManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class PlayerInteractListener implements Listener {

    private final ItemClickManager clickManager;

    /**
     * The listener listens to left and right clicks of the player.
     * If it occurs on an registered item, the methods get executed.
     * @param event
     */
    @EventHandler
    public void onItemInHand(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        if((Action.RIGHT_CLICK_AIR == action || Action.RIGHT_CLICK_BLOCK == action)) {
            clickManager.rightSignClick(event);
            if(!clickManager.isCorrectItem(player)) return;
            clickManager.redDyeRight(event);
        } else if((Action.LEFT_CLICK_AIR == action || Action.LEFT_CLICK_BLOCK == action)) {
            clickManager.leftSignClick(event);
            if(!clickManager.isCorrectItem(player)) return;
            clickManager.redDyeLeft(event);
        }
    }
}
