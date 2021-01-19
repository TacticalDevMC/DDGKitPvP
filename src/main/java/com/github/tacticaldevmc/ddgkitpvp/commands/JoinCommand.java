package com.github.tacticaldevmc.ddgkitpvp.commands;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.board.Boards;
import com.github.tacticaldevmc.ddgkitpvp.kit.Kits;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import com.github.tacticaldevmc.ddgkitpvp.spawns.Spawns;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class JoinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        User user = DDGKitpvp.getInstance().getPlayer(player);
        IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();
        Spawns spawns = new Spawns();

        if (user.isInGame()) {
            player.sendMessage(replaceColor("&cYou are already in-game! Use /leave to leave the game!"));
        } else {
            user.setInGame(true);
            iPlayer.save(user);

            player.getInventory().clear();
            DDGKitpvp.getInstance().giveKit(player, Kits.valueOf(user.getKitSelected().toUpperCase()));

            List<Location> locations = spawns.getSpawns();
            locations.remove(spawns.getLocation("lobby"));
            player.teleport(locations.get(new Random().nextInt(locations.size())));
        }
        return false;
    }
}
