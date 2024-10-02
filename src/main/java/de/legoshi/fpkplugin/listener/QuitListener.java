package de.legoshi.fpkplugin.listener;

import de.legoshi.fpkplugin.manager.CheckPointManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class QuitListener implements Listener {

    private final CheckPointManager checkPointManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        checkPointManager.getCheckpoints().remove(player);
    }
}
