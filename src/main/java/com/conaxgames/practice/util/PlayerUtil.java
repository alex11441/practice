package com.conaxgames.practice.util;

import com.conaxgames.spigot.knockback.KnockbackModule;
import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * This class contains various utility methods
 * that can be used on players.
 */
@UtilityClass
public class PlayerUtil {

    /**
     * Resets a player's inventory and resets other
     * data like health, knockback profile, etc.
     *
     * @param player the player to clean up
     */
    public static void clearPlayer(Player player) {
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(12.8F);
        player.setFireTicks(0);
        player.setFallDistance(0F);
        player.setLevel(0);
        player.setExp(0F);
        player.setAllowFlight(false);
        player.setFlying(false);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.updateInventory();

        player.setGameMode(GameMode.SURVIVAL);

        player.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(player::removePotionEffect);

        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        entityPlayer.setKnockback(KnockbackModule.getDefault());
    }
}
