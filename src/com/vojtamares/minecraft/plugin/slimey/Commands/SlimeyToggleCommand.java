package com.vojtamares.minecraft.plugin.slimey.Commands;

import com.vojtamares.minecraft.plugin.slimey.Slimey;
import com.vojtamares.minecraft.plugin.slimey.Utils.SlimeChunkHelper;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlimeyToggleCommand implements CommandExecutor
{

    private final Slimey plugin;

    public SlimeyToggleCommand(Slimey plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player))
        {
            Bukkit.getLogger().info(ChatColor.RED + "This command can only be executed by a player");
            return true;
        }

        if (!sender.hasPermission("slimey.toggle") && !sender.isOp())
        {
            sender.sendMessage("§cYou don't have permission to do execute this command");

            return true;
        }

        Player p = (Player) sender;

        if (!p.getWorld().getEnvironment().equals(World.Environment.NORMAL))
        {
            p.sendMessage("§cSlimey can only be enabled in Overworld");

            return true;
        }

        if (!this.plugin.hasPlayerEnabled(p))
        {
            this.plugin.addPlayer(p);

            if (SlimeChunkHelper.isInsideASlimeChunk(p))
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§a§LYou are inside of a slime chunk!"));
            else
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cYou are not inside of a slime chunk!"));
        }
        else
        {
            this.plugin.removePlayer(p);
        }

        return true;
    }

}
