package de.legoshi.fpkplugin.listener;

import de.legoshi.fpkplugin.manager.CheckPointManager;
import de.legoshi.fpkplugin.manager.PlayerObjectManager;
import de.legoshi.fpkplugin.util.InventoryHelper;
import de.legoshi.fpkplugin.util.ScoreboardHelper;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class JoinListener implements Listener {

    private final CheckPointManager checkPointManager;
    private final PlayerObjectManager playerObjectManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        checkPointManager.joinPlayer(player);
        playerObjectManager.joinPlayer(player);
        InventoryHelper.setBuild(player);
        ScoreboardHelper.initializeScoreboard(player);
    }
}
