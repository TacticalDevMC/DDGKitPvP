package com.github.tacticaldevmc.ddgkitpvp.commands;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
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
public class LeaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        User user = DDGKitpvp.getInstance().getPlayer(player);
        IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();
        Spawns spawns = new Spawns(player, "lobby");

        if (user.isInGame()) {
            user.setInGame(false);
            iPlayer.save(user);

            spawns.teleport();
            player.getInventory().clear();
            player.getInventory().setItem(4, DDGKitpvp.getInstance().getItem());
        } else {
            player.sendMessage(replaceColor("&cYou are not in-game! Use the KitPvP sign to join a game!"));
        }
        return false;
    }
}
