package com.github.tacticaldevmc.ddgkitpvp.players.impl;

import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public interface IPlayer {

    void create(Player player);
    void delete(UUID value);
    User load(UUID value);
    void save(User user);
    boolean exists(UUID uuid);

}
