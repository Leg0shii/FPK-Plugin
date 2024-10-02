package de.legoshi.fpkplugin.manager;

import de.legoshi.fpkplugin.FPKPlugin;
import de.legoshi.fpkplugin.util.CheckpointObject;
import de.legoshi.fpkplugin.util.PlayerObject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Objects;

@RequiredArgsConstructor
public class ItemClickManager {

    private final ItemStack red = new ItemStack(Material.INK_SACK, 1, (short) 1);
    private final MaterialData redData = red.getData();

    private final CheckPointManager checkPointManager;
    private final PlayerObjectManager playerObjectManager;

    /**
     * Is executed whenever the player right clicks red dye with the a certain metadata.
     * The player then gets teleported to the checkpoint position that is saved.
     * @param event The event that is triggered when the player presses right mouse button
     */
    public void redDyeRight(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if ((getItemInHandData(player).equals(redData))) {

            event.setCancelled(true);
            Location loc = checkPointManager.getCheckpoints().get(player).getLocation();

            if (loc != null) player.teleport(loc);
            else player.sendMessage("No Checkpoint set!");
        }
    }

    /**
     * Is executed whenever the player left clicks red dye with the a certain metadata.
     * The player then sets a checkpoint position that is saved.
     * @param event The event that is triggered when the player presses left mouse button
     */
    public void redDyeLeft(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if ((getItemInHandData(player).equals(redData))) {

            CheckpointObject cpo = FPKPlugin.getInstance().checkPointManager.getCheckpoints().get(player);
            cpo.setLocation(player.getLocation());
            player.sendMessage("Checkpoint set!");
        }
    }

    public void rightSignClick(PlayerInteractEvent event) {

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        if(block == null) return;
        if(player.isSneaking()) return;

        if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {

            Sign sign = (Sign) block.getState();

            try {
                double x = Double.parseDouble(sign.getLine(0));
                double y = Double.parseDouble(sign.getLine(1));
                double z = Double.parseDouble(sign.getLine(2));

                String[] s = sign.getLine(3).split(";");
                float yaw = 90.0f;
                float pitch = 50.0f;

                if(s.length == 2) {
                    yaw = Float.parseFloat(s[0]);
                    pitch = Float.parseFloat(s[1]);
                }
                else if(s.length == 1) yaw = Float.parseFloat(s[0]);

                player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
            } catch (NumberFormatException ignored) { }
        }
    }

    public void leftSignClick(PlayerInteractEvent event) {

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        PlayerObject playerObject = playerObjectManager.getPlayerObjects().get(player);

        if(block == null) return;
        if(!player.hasPermission("td2.sign")) return;
        if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {

            Sign sign = (Sign) block.getState();

            if(playerObject.getSignPos() == null) {
                sign.setLine(0, String.format("%.4f", player.getLocation().getX()).replace(",", "."));
                sign.setLine(1, String.format("%.4f", player.getLocation().getY()).replace(",", "."));
                sign.setLine(2, String.format("%.4f", player.getLocation().getZ()).replace(",", "."));
                String pitchYaw = (String.format("%.4f", player.getLocation().getYaw()).replace(",", ".") + ";" +
                        String.format("%.4f", player.getLocation().getPitch()).replace(",", ".") + ";");
                sign.setLine(3, pitchYaw);
            } else {
                sign.setLine(0, String.format("%.4f", playerObject.getSignPos().getX()).replace(",", "."));
                sign.setLine(1, String.format("%.4f", playerObject.getSignPos().getY()).replace(",", "."));
                sign.setLine(2, String.format("%.4f", playerObject.getSignPos().getZ()).replace(",", "."));
                String pitchYaw = (String.format("%.4f", playerObject.getSignPos().getYaw()).replace(",", ".") + ";" +
                        String.format("%.4f", playerObject.getSignPos().getPitch()).replace(",", ".") + ";");
                sign.setLine(3, pitchYaw);
            }

            sign.update();
            playerObject.setSignPos(null);

            if(!player.isSneaking()) event.setCancelled(true);
        }
    }

    public MaterialData getItemInHandData(Player player) {

        PlayerInventory inventory = player.getInventory();
        return inventory.getItem(inventory.getHeldItemSlot()).getData();
    }

    public boolean isPracticeItem(Player player) {

        ArrayList<String> lore = new ArrayList<String>();
        PlayerInventory inventory = player.getInventory();
        lore.add("§l§6Practice");
        return Objects.equals(inventory.getItem(inventory.getHeldItemSlot()).getItemMeta().getLore(), lore);
    }

    public boolean isHandEmpty(Player player) {

        PlayerInventory inventory = player.getInventory();
        ItemStack itemstack = inventory.getItem(inventory.getHeldItemSlot());
        return itemstack == null;
    }

    public boolean isCorrectItem(Player player) {

        if (!(isHandEmpty(player))) return isPracticeItem(player);
        return false;
    }
}
