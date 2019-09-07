package com.vojtamares.minecraft.plugin.slimey;

import com.vojtamares.minecraft.plugin.slimey.Commands.SlimeyToggleCommand;
import com.vojtamares.minecraft.plugin.slimey.Listeners.PlayerMoveListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Slimey extends JavaPlugin
{

    private ArrayList<Player> players;

    @Override
    public void onEnable()
    {
        this.players = new ArrayList<Player>();

        this.init();
    }

    @Override
    public void onDisable()
    {
        this.getLogger().info(ChatColor.YELLOW + "Disabling Slimey for all players, who has Slimey enabled");

        while (this.hasPlayersEnabled())
        {
            Player p = this.players.get(this.players.size() - 1);
            this.removePlayer(p);
        }

        this.getLogger().info(ChatColor.GREEN + "Disabled for all players, safe to shutdown...");
    }

    private void init()
    {
        this.getCommand("slimey").setExecutor(new SlimeyToggleCommand(this));
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
    }


    // ####################################################################


    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    public void addPlayer(Player player)
    {
        this.players.add(player);
        player.sendMessage("§aSlimey has been toggled on");
    }

    public void removePlayer(Player player)
    {
        this.players.remove(player);
        player.sendMessage("§cSlimey has been toggled off");
    }

    public boolean hasPlayersEnabled()
    {
        return this.players.size() != 0;
    }

    public boolean hasPlayerEnabled(Player player)
    {
        return this.players.contains(player);
    }

}
