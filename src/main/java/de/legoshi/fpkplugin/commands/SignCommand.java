package de.legoshi.fpkplugin.commands;

import de.legoshi.fpkplugin.manager.PlayerObjectManager;
import de.legoshi.fpkplugin.util.PlayerObject;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SignCommand implements CommandExecutor {

    private final PlayerObjectManager playerObjectManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cYou are not a player!");
            return false;
        }

        Player player = ((Player) sender).getPlayer();

        // if(!(player.hasPermission("td2.sign"))) {
            PlayerObject playerObject = playerObjectManager.getPlayerObjects().get(player);
            playerObject.setSignPos(player.getLocation());
            sender.sendMessage("§aSuccessfully set sign coords!");
        // }

        // sender.sendMessage("§cYou don't have permissions for this!");
        return false;
    }
}
