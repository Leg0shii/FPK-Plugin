package de.legoshi.fpkplugin.manager;

import de.legoshi.fpkplugin.util.PlayerObject;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
public class PlayerObjectManager {

    private final HashMap<Player, PlayerObject> playerObjects;

    public PlayerObjectManager() {
        this.playerObjects = new HashMap<>();
    }

    /**
     * Is used to initialize the player properly when he joins
     * @param player player that joins
     */
    public void joinPlayer(Player player) {
        this.playerObjects.put(player, new PlayerObject(player));
    }

}
