package com.conaxgames.practice.kit.command;

import com.conaxgames.rank.Rank;
import com.conaxgames.util.cmd.CommandHandler;
import com.conaxgames.util.cmd.annotation.commandTypes.BaseCommand;
import org.bukkit.entity.Player;

public class KitBaseCommand implements CommandHandler {

    @BaseCommand(name = "kit", rank = Rank.MANAGER)
    public void kitBaseCommand(Player player) {

    }
}
