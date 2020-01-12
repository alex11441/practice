package com.conaxgames.practice.match.task;

import com.conaxgames.practice.match.Match;
import com.conaxgames.practice.match.MatchState;
import com.conaxgames.util.finalutil.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class MatchCountdownTask extends BukkitRunnable {

    private final Match match;

    private int countdown = 5;

    public void run() {
        if (match.getState() != MatchState.COUNTDOWN) {
            cancel();
            return;
        }

        if (countdown == 0) {
            match.broadcast(Sound.NOTE_PLING, 2F);
            match.startMatch();

            cancel();
            return;
        } else {
            match.broadcast(Sound.NOTE_PLING, 1F);
        }

        String seconds = countdown > 1 ? "seconds" : "second";
        match.broadcast(CC.GREEN + "The match will start in " + countdown + " " + seconds + "...");
        countdown--;
    }
}
