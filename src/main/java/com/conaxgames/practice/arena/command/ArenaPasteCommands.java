package com.conaxgames.practice.arena.command;

import com.conaxgames.practice.Practice;
import com.conaxgames.util.cmd.CommandHandler;
import com.conaxgames.util.cmd.annotation.Param;
import com.conaxgames.util.cmd.annotation.Text;
import com.conaxgames.util.cmd.annotation.commandTypes.SubCommand;
import org.bukkit.entity.Player;

/**
 * Commands that deal with pasting arenas (paste, generate)
 */
public class ArenaPasteCommands implements CommandHandler {

    @SubCommand(baseCommand = "arena", name = "paste")
    public void arenaPaste(Player player, @Param(name = "file") String file,
                           @Param(name = "x") int x, @Param(name = "z") int z,
                           @Text(name = "name") String name) {
        Practice.getInstance().getArenaManager().createArena(file, name, x, z);
    }

    @SubCommand(baseCommand = "arena", name = "generate")
    public void arenaGenerate(Player player, @Param(name = "file") String file,
                              @Param(name = "times") int times,
                              @Param(name = "startingX") int startingX,
                              @Param(name = "startingZ") int startingZ,
                              @Param(name = "incrementX") boolean incrementX,
                              @Param(name = "incrementZ") boolean incrementZ) {
        Practice.getInstance().getArenaManager().createArenas(file, times,
                startingX, startingZ,
                incrementX, incrementZ, 1000);
    }

}
