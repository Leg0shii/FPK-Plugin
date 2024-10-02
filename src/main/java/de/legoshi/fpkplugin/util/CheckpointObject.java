package de.legoshi.fpkplugin.util;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter
@Setter
public class CheckpointObject {

    private Player player;
    private Location location;

    public CheckpointObject(Player player, Location location) {

        this.player = player;
        this.location = location;
    }
}
