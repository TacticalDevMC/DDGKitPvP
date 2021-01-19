package com.github.tacticaldevmc.ddgkitpvp.board;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class Boards {

    ScoreboardBuilder lobbyBoard, pvpBoard;

    private void lobbyBoard(Player player) {
        User user = DDGKitpvp.getInstance().getPlayer(player);
        IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();

        lobbyBoard = new ScoreboardBuilder("&6&lLobby", 10);

        lobbyBoard.addBlankLine();

        if (iPlayer.exists(player.getUniqueId())) {
            lobbyBoard.addLine(replaceColor("&7Points: &f" + user.getPoints()));
            lobbyBoard.addLine(replaceColor("&7KitSelected: &f" + user.getKitSelected()));
        } else {
            lobbyBoard.addLine(replaceColor("&7Points: &c?"));
            lobbyBoard.addLine(replaceColor("&7KitSelected: &c?"));
        }

        lobbyBoard.addLine(ChatColor.RED.toString());

        player.setScoreboard(lobbyBoard.getBoard());
    }

    private void pvpBoard(Player player) {
        User user = DDGKitpvp.getInstance().getPlayer(player);
        IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();

        int points = user.getPoints();
        int kills = user.getKills();
        int deaths = user.getDeaths();
        int killstreaks = user.getKillStreaks();
        int currentKillStreak = user.getCurrentKillStreak();
        String killDeathRatio;

        pvpBoard = new ScoreboardBuilder("&6&lKitPvP", 10);

        pvpBoard.addBlankLine();

        if (iPlayer.exists(player.getUniqueId())) {
            if (kills == 0 && deaths == 0) {
                killDeathRatio = String.valueOf(0.00);
            } else if (kills != 0 && deaths == 0) {
                killDeathRatio = String.valueOf(kills);
            } else if (kills == 0 && deaths != 0) {
                killDeathRatio = String.valueOf(deaths);
            } else {
                killDeathRatio = String.format(Locale.ENGLISH, "%.2f", (double) kills / deaths);
            }

            pvpBoard.addLine(replaceColor("&7Points: &f" + points));
            pvpBoard.addLine(replaceColor("&7Kills: &f" + kills));
            pvpBoard.addLine(replaceColor("&7Deaths: &f" + deaths));
            pvpBoard.addLine(replaceColor("&7KDR: &f" + killDeathRatio));
            pvpBoard.addLine(replaceColor("&7Killstreaks: &f" + killstreaks));
            pvpBoard.addLine(replaceColor("&7Current Killstreak: &f" + currentKillStreak));
        } else {
            pvpBoard.addLine(replaceColor("&7Points: &c?"));
            pvpBoard.addLine(replaceColor("&7Kills: &c?"));
            pvpBoard.addLine(replaceColor("&7Deaths: &c?"));
            pvpBoard.addLine(replaceColor("&7KDR: &c?"));
            pvpBoard.addLine(replaceColor("&7Killstreaks: &c?"));
            pvpBoard.addLine(replaceColor("&7Current Killstreak: &c?"));
        }

        pvpBoard.addLine(ChatColor.RED.toString());

        player.setScoreboard(pvpBoard.getBoard());
    }

    public void run(Player player) {
        User user = DDGKitpvp.getInstance().getPlayer(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (user.isInGame()) {
                    pvpBoard(player);
                } else {
                    lobbyBoard(player);
                }
            }
        }.runTaskTimer(DDGKitpvp.getInstance(), 100L, 10L);
    }
}
