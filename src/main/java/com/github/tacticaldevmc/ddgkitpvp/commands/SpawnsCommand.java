package com.github.tacticaldevmc.ddgkitpvp.commands;

import com.github.tacticaldevmc.ddgkitpvp.spawns.Spawns;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class SpawnsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Spawns spawns = new Spawns(player, args[0]);

        if(spawns.exists()){
            player.sendMessage(replaceColor("&cThe spawn with name &e" + args[0] + " &calready exists!"));
            return false;
        }

        spawns.create();

        player.sendMessage(replaceColor("&aSpawn with name &e" + args[0] + " &aset!"));
        return false;
    }
}
