package com.github.tacticaldevmc.ddgkitpvp.listeners.players;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import com.github.tacticaldevmc.ddgkitpvp.spawns.Spawns;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class PvPListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Spawns spawns = new Spawns(player, "lobby");
        spawns.teleport();

        player.getInventory().clear();
        player.getInventory().setItem(4, DDGKitpvp.getInstance().getItem());
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        Player killer = event.getEntity().getKiller();
        User user = DDGKitpvp.getInstance().getPlayer(killer);

        User deathUser = DDGKitpvp.getInstance().getPlayer(player);
        IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();

        if (user.isInGame() && deathUser.isInGame()) {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                User onlineUser = DDGKitpvp.getInstance().getPlayer(onlinePlayers);

                onlinePlayers.sendMessage(replaceColor("&7" + player.getName() + " has been removed by " + killer.getName()));

                if (deathUser.getCurrentKillStreak() != 0) {
                    onlinePlayers.sendMessage(replaceColor("&7" + killer.getName() + " ended &e" + player.getName() + "'s &7" + deathUser.getCurrentKillStreak() + " killstreak!"));
                }

                if (onlineUser.isInGame()) {
                    switch (user.getCurrentKillStreak()) {
                        case 3:
                            onlinePlayers.sendMessage(replaceColor("&7" + killer.getName() + " is &6&lON FIRE &7(3 killstreak)!"));
                            break;
                        case 5:
                            onlinePlayers.sendMessage(replaceColor("&7" + killer.getName() + " is &2&lONSTOPPABLE &7(5 killstreak)!"));
                            break;
                        case 10:
                            onlinePlayers.sendMessage(replaceColor("&7" + killer.getName() + " has maybe &c&lHACKS &7(10 killstreak)!"));
                            break;
                        case 20:
                            onlinePlayers.sendMessage(replaceColor("&7" + killer.getName() + " is a pvp &d&lGOD &7(20 killstreak)!"));
                            break;
                        default:
                    }
                }
            }

            // We will change data of the player
            user.setKillStreaks(user.getKillStreaks() + 1);
            user.setCurrentKillStreak(user.getCurrentKillStreak() + 1);
            user.setKills(user.getKills() + 1);
            user.setPoints(user.getPoints() + 1);
            killer.sendMessage(replaceColor("&6+1 point"));
            iPlayer.save(user);

            deathUser.setCurrentKillStreak(0);
            deathUser.setDeaths(deathUser.getDeaths() + 1);
            iPlayer.save(deathUser);
        }
    }
}
