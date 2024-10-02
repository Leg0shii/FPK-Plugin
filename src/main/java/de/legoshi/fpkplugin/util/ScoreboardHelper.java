package de.legoshi.fpkplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardHelper {

    public static void initializeScoreboard(Player player) {

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("fpk", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        scoreboard.registerNewTeam("playtime").addEntry(ChatColor.YELLOW + "" + ChatColor.WHITE);
        scoreboard.registerNewTeam("jumps").addEntry(ChatColor.GRAY + "" + ChatColor.WHITE);

        Bukkit.getConsoleSender().sendMessage("Scoreboard Created!");

        objective.setDisplayName("" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Factory PK");

        //space
        objective.getScore(ChatColor.YELLOW + " ").setScore(13);

        //set ranks
        objective.getScore("" + ChatColor.GRAY + ChatColor.BOLD + "Playtime").setScore(12);
        scoreboard.getTeam("playtime").setPrefix(" " + ChatColor.WHITE + player.getStatistic(Statistic.PLAY_ONE_TICK)/(20*3600) + " h");
        objective.getScore(ChatColor.YELLOW + "" + ChatColor.WHITE).setScore(11);

        //space
        objective.getScore(ChatColor.DARK_BLUE + " ").setScore(10);

        //set pp
        objective.getScore("" + ChatColor.GRAY + ChatColor.BOLD + "Jumps").setScore(9);
        scoreboard.getTeam("jumps").setPrefix(" " + ChatColor.WHITE + player.getStatistic(Statistic.JUMP));
        objective.getScore(ChatColor.GRAY + "" + ChatColor.WHITE).setScore(8);

        player.setScoreboard(scoreboard);
        Bukkit.getConsoleSender().sendMessage("Scoreboard added to player!");

    }

}
