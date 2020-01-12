package com.conaxgames.practice.arena.command;

import com.conaxgames.practice.Practice;
import com.conaxgames.util.cmd.CommandHandler;
import com.conaxgames.util.cmd.annotation.Param;
import com.conaxgames.util.cmd.annotation.commandTypes.SubCommand;
import com.conaxgames.util.finalutil.CC;
import org.bukkit.entity.Player;

/**
 * Commands that deal with whether an arena is used in
 * specific conditions.
 */
public class ArenaStatusCommands implements CommandHandler {

    @SubCommand(baseCommand = "arena", name = "toggle")
    public void arenaToggle(Player player, @Param(name = "arena") String arena) {
        player.sendMessage(CC.GREEN
                + (Practice.getInstance().getArenaManager().toggleArena(arena) ? "Enabled" : "Disabled")
                + " arenas with the prefix " + arena + ".");
    }

}
