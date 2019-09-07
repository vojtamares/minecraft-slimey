package com.vojtamares.minecraft.plugin.slimey.Listeners;

import com.vojtamares.minecraft.plugin.slimey.Slimey;
import com.vojtamares.minecraft.plugin.slimey.Utils.SlimeChunkHelper;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener
{

    private final Slimey plugin;

    public PlayerMoveListener(Slimey plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if (!this.plugin.hasPlayersEnabled())
            return;

        if (!this.plugin.hasPlayerEnabled(event.getPlayer()))
            return;

        if (!event.getPlayer().getWorld().getEnvironment().equals(World.Environment.NORMAL))
        {
            this.plugin.removePlayer(event.getPlayer());
            return;
        }

        if (SlimeChunkHelper.isInsideASlimeChunk(event.getPlayer()))
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§a§LYou are inside of a slime chunk!"));
        else
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cYou are not inside of a slime chunk!"));
    }

}
