package me.sachin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.sachin.commands.OopsCommand;
import me.sachin.listeners.BlockLeftClickListener;
import me.sachin.listeners.BlockPlaceListener;

import java.util.HashMap;
import java.util.List;

public final class Oops extends JavaPlugin implements Listener {

    private HashMap<Location , ItemStack> oopsLocStackMap = new HashMap<>();
    private HashMap<Location, Player> oopsLocPlayerMap = new HashMap<>();

    public HashMap<Location, ItemStack> getOopsLocStackMap() {
        return oopsLocStackMap;
    }

    public HashMap<Location, Player> getOopsLocPlayerMap() {
        return oopsLocPlayerMap;
    }

    @Override
    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();

        // register events
        pm.registerEvents(new BlockLeftClickListener(this), this);
        pm.registerEvents(new BlockPlaceListener(this), this);

        // save config if dosnt exsit
        this.saveDefaultConfig();
        this.reloadConfig();

        // register commands
        this.getCommand("oops").setExecutor(new OopsCommand(this));
    }

    

    

}
