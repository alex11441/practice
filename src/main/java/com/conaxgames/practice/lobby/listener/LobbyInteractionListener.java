package com.conaxgames.practice.lobby.listener;

import com.conaxgames.practice.lobby.LobbyManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Slightly ambiguous name, but this class prevents basic
 * stuff like building, placing, etc. while in the lobby.
 */
@RequiredArgsConstructor
public class LobbyInteractionListener implements Listener {

    private final LobbyManager lobbyManager;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        cancelEventIfNecessary(player, event);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        cancelEventIfNecessary(player, event);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        cancelEventIfNecessary(player, event);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        cancelEventIfNecessary(player, event);
    }

    private void cancelEventIfNecessary(Player player, Cancellable cancellable) {
        if (player.getGameMode() != GameMode.CREATIVE && lobbyManager.inLobby(player)) {
            cancellable.setCancelled(true);
        }
    }
}
