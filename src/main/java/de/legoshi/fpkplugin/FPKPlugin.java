package de.legoshi.fpkplugin;

import de.legoshi.fpkplugin.commands.SignCommand;
import de.legoshi.fpkplugin.commands.SpeedCommand;
import de.legoshi.fpkplugin.commands.TierCommand;
import de.legoshi.fpkplugin.listener.*;
import de.legoshi.fpkplugin.manager.CheckPointManager;
import de.legoshi.fpkplugin.manager.ItemClickManager;
import de.legoshi.fpkplugin.manager.PlayerObjectManager;
import de.legoshi.fpkplugin.util.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FPKPlugin extends JavaPlugin {

    public static FPKPlugin instance;
    public CheckPointManager checkPointManager;
    public ItemClickManager itemClickManager;
    public PlayerObjectManager playerObjectManager;

    @Override
    public void onEnable() {

        instance = this;

        playerObjectManager = new PlayerObjectManager();
        checkPointManager = new CheckPointManager();
        itemClickManager = new ItemClickManager(checkPointManager, playerObjectManager);

        //TODO: add ItemBuilder or settings to activate outputs (how far away from block you landed)

        //TODO: 45 - Strafe rater (Rating 45 strafes)
        //TODO: auto 45 strafe

        //TODO: add lobbysystem
        //TODO: Setup Perms for World/Spawn

        //TODO: make a Worldcreator
        //TODO: create a new World with command
        //TODO: create a new World max 1 for each player with /world Create
        //TODO: create an invition command for people
        //TODO: create an open world command

        //TODO: add Signs with Location + setup Commands

        //TODO: add a FacingChanger
        //TODO: make armorstands rideable
        //TODO: make armorstands edible from rideposition
        //TODO: change my facing on the tick of the edited armorstands index


        CommandRegistration();
        ListenerRegistration();

        reload();
    }

    @Override
    public void onDisable() {

    }

    private void CommandRegistration() {

        getCommand("tier").setExecutor(new TierCommand());
        getCommand("potspeed").setExecutor(new SpeedCommand());
        getCommand("sign").setExecutor(new SignCommand(playerObjectManager));
    }

    private void ListenerRegistration() {

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(checkPointManager, playerObjectManager), this);
        pm.registerEvents(new QuitListener(checkPointManager), this);
        pm.registerEvents(new PlayerInteractListener(itemClickManager), this);
        pm.registerEvents(new PlayerDropListener(), this);
        pm.registerEvents(new PlayerBlockDestroyListener(itemClickManager), this);
        pm.registerEvents(new PlayerBlockPlaceListener(itemClickManager), this);
        pm.registerEvents(new CancelListener(), this);
        pm.registerEvents(new StatisticListener(), this);
    }

    private void reload() {

        for(Player all : Bukkit.getOnlinePlayers()) {
            InventoryHelper.setBuild(all);
            checkPointManager.joinPlayer(all);
            playerObjectManager.joinPlayer(all);
        }
    }

    public static FPKPlugin getInstance() {
        return instance;
    }

}
