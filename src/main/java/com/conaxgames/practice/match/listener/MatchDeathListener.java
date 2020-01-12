package com.conaxgames.practice.match.listener;

import com.conaxgames.practice.match.Match;
import com.conaxgames.practice.match.MatchManager;
import com.conaxgames.practice.match.MatchTeam;
import com.conaxgames.practice.util.PlayerUtil;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

@RequiredArgsConstructor
public class MatchDeathListener implements Listener {

    private final MatchManager matchManager;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID uuid = player.getUniqueId();
        if (!matchManager.inMatch(uuid)) {
            return;
        }

        Match match = matchManager.getMatch(uuid);
        MatchTeam team = match.getTeam(uuid);

        respawn(player);
        match.getTeams().forEach(matchTeam
                -> matchTeam.getPlayerList().forEach(teamPlayer -> teamPlayer.hidePlayer(player)));
        match.getSpectatorPlayers().forEach(spectator -> spectator.hidePlayer(player));

        team.killPlayer(uuid);
        match.checkEnd();
    }

    private void respawn(Player player) {
        Location location = player.getLocation();

        player.spigot().respawn();

        PlayerUtil.clearPlayer(player);

        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        entityPlayer.getDataWatcher().watch(6, 0.0F);
        entityPlayer.setFakingDeath(true);

        player.teleport(location.add(0, 1, 0));
    }
}
