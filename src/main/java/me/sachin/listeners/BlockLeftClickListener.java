package me.sachin.listeners;

import org.bukkit.event.Listener;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.sachin.Oops;
import me.sachin.configuration.ConfigUtils;


public class BlockLeftClickListener implements Listener{

    private Oops instance;
    private ConfigUtils config;
    private ItemStack silkItem;

    public BlockLeftClickListener(Oops plugin){
        this.instance = plugin;
        this.config = new ConfigUtils(plugin);
        this.silkItem = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = this.silkItem.getItemMeta();
        meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        this.silkItem.setItemMeta(meta);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockLeftClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(!e.getAction().equals(Action.LEFT_CLICK_BLOCK) || p.getGameMode().equals(GameMode.CREATIVE)) return;
        Location loc = e.getClickedBlock().getLocation();
        String materialName = loc.getBlock().getType().name();
        if(instance.getOopsLocStackMap().containsKey(loc) && instance.getOopsLocPlayerMap().get(loc).equals(p) && !config.getMatNames().contains(materialName)){
            loc.getBlock().breakNaturally(silkItem);
            instance.getOopsLocPlayerMap().remove(loc);
            instance.getOopsLocStackMap().remove(loc);
        }
    }
    
}
