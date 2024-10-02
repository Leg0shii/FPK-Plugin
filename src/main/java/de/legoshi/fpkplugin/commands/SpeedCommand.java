package de.legoshi.fpkplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = ((Player) sender).getPlayer();

        if(args.length == 0) {

            player.sendMessage("§cSyntax /potspeed <speedvalue>");
            return false;
        }

        int speed = 0;
        try {

            speed = Integer.parseInt(args[0])-1;
            if(speed>100 || speed<0) {

                player.sendMessage("§cPlease enter a number from 1-100");
                return false;
            }

        } catch (NumberFormatException e) {

            player.sendMessage("§cPlease enter a number");
            return false;
        }

        player.removePotionEffect(PotionEffectType.SPEED);
        player.addPotionEffect(PotionEffectType.SPEED.createEffect(100000, speed));
        player.sendMessage("§aSuccessfully set your speed to " + (speed+1));
        return false;
    }
}
