package de.legoshi.fpkplugin.util;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@Getter
@Setter
public class PlayerObject {

    private Player player;
    private ArrayList<Location> locationList;
    private Location signPos;

    public PlayerObject(Player player) {

        this.locationList = new ArrayList<>();
        this.player = player;
        this.signPos = player.getLocation();
    }

}
