package com.github.tacticaldevmc.ddgkitpvp.players.users;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
@Getter
@Setter
public class User {

    private Player player;
    private UUID uuid;
    private String name, kitSelected;
    private Integer points, kills, deaths, killStreaks, currentKillStreak;
    private boolean inGame;

    public User(UUID uuid, String name, String kitSelected, Integer points, Integer kills, Integer deaths, Integer killStreaks, Integer currentKillStreak, boolean inGame) {
        this.uuid = uuid;
        this.name = name;
        this.kitSelected = kitSelected;
        this.points = points;
        this.kills = kills;
        this.deaths = deaths;
        this.killStreaks = killStreaks;
        this.currentKillStreak = currentKillStreak;
        this.inGame = inGame;
    }
}
