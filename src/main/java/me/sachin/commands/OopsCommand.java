package me.sachin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.sachin.Oops;
import me.sachin.configuration.ConfigUtils;
import me.sachin.configuration.OConstants;
import net.md_5.bungee.api.ChatColor;

public class OopsCommand implements CommandExecutor{

    private Oops instance;
    private ConfigUtils config;

    public OopsCommand(Oops plugin){
        this.instance = plugin;
        this.config = new ConfigUtils(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"requires player to execute the command");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission(OConstants.COMMAND_PERMISSION_RELOAD)){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"You dont have permission to run the command");
            return true;
        }
        if(args.length == 0){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"invalid arguments");
            return true;
        }
        if(args[0].equals("reload")){
            instance.saveDefaultConfig();
            instance.reloadConfig();
            p.sendMessage(ChatColor.GREEN+"Oops config successfully reloaded");
        }

        return false;
    }
    
}
