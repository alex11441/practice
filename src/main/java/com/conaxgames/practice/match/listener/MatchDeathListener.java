package com.conaxgames.practice.match.listener;

import com.conaxgames.practice.match.Match;
import com.conaxgames.practice.match.MatchManager;
import com.conaxgames.practice.match.MatchTeam;
import lombok.RequiredArgsConstructor;
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

        team.killPlayer(uuid);
        match.checkEnd();
    }
}
