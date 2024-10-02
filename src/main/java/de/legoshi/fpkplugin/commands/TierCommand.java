package de.legoshi.fpkplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TierCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cNo Player");
            return true;
        }

        Player player = ((Player) commandSender).getPlayer();
        int length = strings.length;
        if (length == 0) {
            player.sendMessage("§c/tier <Height>");
            return true;
        }

        if (length == 1) {

            try {
                if (Double.parseDouble(strings[0]) >= 1.24920 || Double.parseDouble(strings[0]) < -10000) {
                    player.sendMessage("§cYour Input is too high or too small");
                } else player.sendMessage("§aTier: " + (tierCalc(Double.parseDouble(strings[0])) - 11) * (-1));

                return true;
            } catch (NumberFormatException ex) {

                player.sendMessage("§cWrong input.");
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the tier based on a given Y difference.
     * @param height the difference from current position and landing position on the Y axis.
     * @return the in-game tier that derives out of it.
     */
    public int tierCalc(double height) {

        double sum = 1.24919;
        double n = 0.00301;
        int i;
        for (i = 4; sum >= height; i++) {
            sum = sum + n;
            n = (n - 0.08) * 0.98;
        }
        return i;
    }
}
