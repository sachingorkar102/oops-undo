package me.sachin.configuration;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import me.sachin.Oops;

public class ConfigUtils {

    private Oops plugin;
    // private List<String> matNames = new ArrayList<>();

    
    public ConfigUtils(Oops plugin){
        this.plugin = plugin;
    }
    
    
    
    
    public List<String> getMatNames() {
        return plugin.getConfig().getStringList(OConstants.BLACK_LIST_BLOCKS_CONFIG);
    }

    public int getTimeOutTicks(){
        return plugin.getConfig().getInt(OConstants.TIME_OUT_TICKS_CONFIG,30);
    }

    // public boolean dropTileEntities(){
    //     return plugin.getConfig().getBoolean(OConstants.DROP_TILE_ENTITIES_CONFIG,true);
    // }

}
