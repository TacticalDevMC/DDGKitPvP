package com.github.tacticaldevmc.ddgkitpvp.listeners.players;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.board.Boards;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();

        if (!iPlayer.exists(player.getUniqueId())) {
            iPlayer.create(player);
        }

        player.getInventory().clear();
        player.getInventory().setItem(4, DDGKitpvp.getInstance().getItem());

        new Boards().run(player);

        event.setJoinMessage(replaceColor("&7[&a+&7] " + player.getName() + " joined kitpvp!"));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = DDGKitpvp.getInstance().getPlayer(player);
        IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();

        if (!iPlayer.exists(player.getUniqueId())) return;
        iPlayer.save(user);

        event.setQuitMessage(replaceColor("&7[&c-&7] " + player.getName() + " has left kitpvp!"));
    }
}
