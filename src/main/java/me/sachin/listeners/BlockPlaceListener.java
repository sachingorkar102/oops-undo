package me.sachin.listeners;

import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.sachin.Oops;
import me.sachin.configuration.ConfigUtils;
import me.sachin.configuration.OConstants;


public class BlockPlaceListener implements Listener{

    private Oops instance;
    private ConfigUtils config;

    public BlockPlaceListener(Oops plugin){
        this.instance = plugin;
        this.config = new ConfigUtils(plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(!p.hasPermission(OConstants.PERMISSION_BREAKBLOCK)) return;
        Location loc = e.getBlock().getLocation();
        ItemStack item = e.getItemInHand().clone();
        item.setAmount(1);
        instance.getOopsLocStackMap().put(loc,item);
        instance.getOopsLocPlayerMap().put(loc,p);
        new BukkitRunnable(){
            @Override
            public void run() {
                instance.getOopsLocStackMap().remove(loc);
                instance.getOopsLocPlayerMap().remove(loc);
            }
        }.runTaskLaterAsynchronously(instance, config.getTimeOutTicks());
    }
    
}
