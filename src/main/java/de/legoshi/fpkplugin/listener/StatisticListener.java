package de.legoshi.fpkplugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public class StatisticListener implements Listener {

    @EventHandler
    public void onStatistic(PlayerStatisticIncrementEvent event) {

        Player player = event.getPlayer();
        if(event.getStatistic() == Statistic.JUMP) {
            player.getScoreboard().getTeam("jumps")
                    .setPrefix(" " + ChatColor.WHITE + player.getStatistic(Statistic.JUMP));
        } else if(event.getStatistic() == Statistic.PLAY_ONE_TICK) {
            player.getScoreboard().getTeam("jumps")
                    .setPrefix(" " + ChatColor.WHITE + player.getStatistic(Statistic.PLAY_ONE_TICK)/(20*3600) + " h");
        }

    }

}
