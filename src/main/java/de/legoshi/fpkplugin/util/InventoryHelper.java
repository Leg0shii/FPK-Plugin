package de.legoshi.fpkplugin.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryHelper {

    public static void setBuild(Player player) {

        player.getInventory().clear();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§l§6Practice");

        ItemStack red = new ItemStack(Material.INK_SACK, 1, (short)1);
        ItemMeta metaRed = red.getItemMeta();
        metaRed.setLore(lore);
        metaRed.setDisplayName("§lCheckpoint");
        red.setItemMeta(metaRed);

        player.getInventory().setItem(0, red);
    }
}
