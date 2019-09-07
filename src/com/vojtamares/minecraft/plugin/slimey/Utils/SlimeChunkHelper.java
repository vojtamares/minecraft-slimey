package com.vojtamares.minecraft.plugin.slimey.Utils;

import org.bukkit.entity.Player;

import java.util.Random;

public class SlimeChunkHelper
{

    public static boolean isInsideASlimeChunk(Player p)
    {
        long seed = p.getWorld().getSeed();

        int chunkX = p.getWorld().getChunkAt(p.getLocation()).getX();
        int chunkZ = p.getWorld().getChunkAt(p.getLocation()).getZ();

        Random r = new Random(
                seed +
                        (int) (chunkX * chunkX * 0x4c1906) +
                        (int) (chunkX * 0x5ac0db) +
                        (int) (chunkZ * chunkZ) * 0x4307a7L +
                        (int) (chunkZ * 0x5f24f) ^ 0x3ad8025f
        );

        return r.nextInt(10) == 0;
    }

}
