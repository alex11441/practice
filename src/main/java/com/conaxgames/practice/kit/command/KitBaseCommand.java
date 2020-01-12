package com.conaxgames.practice.kit.command;

import com.conaxgames.practice.Practice;
import com.conaxgames.practice.kit.Kit;
import com.conaxgames.rank.Rank;
import com.conaxgames.util.cmd.CommandHandler;
import com.conaxgames.util.cmd.annotation.Param;
import com.conaxgames.util.cmd.annotation.commandTypes.BaseCommand;
import com.conaxgames.util.cmd.annotation.commandTypes.SubCommand;
import com.conaxgames.util.finalutil.CC;
import org.bukkit.entity.Player;

public class KitBaseCommand implements CommandHandler {

    @BaseCommand(name = "kit", rank = Rank.MANAGER)
    public void kitBaseCommand(Player player) {

    }

    @SubCommand(baseCommand = "kit", name = "create")
    public void kitCreate(Player player, @Param(name = "name") String id) {
        Kit existingKit = Practice.getInstance().getKitManager().getKitFromId(id);
        if (existingKit != null) {
            player.sendMessage(CC.RED + "A kit with that ID already exists.");
            return;
        }

        Practice.getInstance().getKitManager().createKit(id);
        player.sendMessage(CC.GREEN + "Created and saved a kit with the ID " + id + ".");
    }
}
