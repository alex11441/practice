package com.conaxgames.practice.lobby;

import com.conaxgames.practice.Practice;
import com.conaxgames.practice.lobby.listener.LobbyGeneralListener;
import com.conaxgames.practice.lobby.listener.LobbyInteractionListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LobbyManager {

    private final Location spawnLocation;

    public LobbyManager() {
        Bukkit.getPluginManager().registerEvents(new LobbyGeneralListener(this), Practice.getInstance());
        Bukkit.getPluginManager().registerEvents(new LobbyInteractionListener(this), Practice.getInstance());

        this.spawnLocation = Bukkit.getWorlds().get(0).getSpawnLocation();
    }

    /**
     * Teleports a player to the spawn and resets their
     * inventory using {@link #giveLobbyInventory(Player)}
     *
     * @param player the player to send to the lobby
     */
    public void sendToLobby(Player player) {
        player.teleport(spawnLocation);

        giveLobbyInventory(player);
    }

    /**
     * Gives a player the appropriate lobby items. Will differ
     * depending on certain factors, including whether a player is
     * in a party, a queue, etc.
     *
     * @param player the player to give items to
     */
    public void giveLobbyInventory(Player player) {
        // TODO: Give appropriate queue, kit editor, leaderboards items when implemented
    }

    /**
     * Checks against all other possible "state"s to determine
     * whether or not a player is in the lobby.
     * 
     * @param player the player to check
     * @return true if the player is in the lobby, otherwise false
     */
    public boolean inLobby(Player player) {
        return true; // TODO: Check all other states once they're written
    }
}
