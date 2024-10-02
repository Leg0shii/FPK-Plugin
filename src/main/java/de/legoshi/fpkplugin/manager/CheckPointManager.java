package de.legoshi.fpkplugin.manager;

import de.legoshi.fpkplugin.util.CheckpointObject;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
public class CheckPointManager {

    private final HashMap<Player, CheckpointObject> checkpoints;

    public CheckPointManager() {

        this.checkpoints = new HashMap<>();
    }

    /**
     * Is used to initialize the player properly when he joins
     * @param player player that joins
     */
    public void joinPlayer(Player player) {

        this.checkpoints.put(player, new CheckpointObject(player, player.getLocation()));
    }
}
