package com.github.tacticaldevmc.ddgkitpvp.signs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class GameSign implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getLine(0).equalsIgnoreCase("[KitPvP]")) {
            if (event.getLine(1).equalsIgnoreCase("join")) {
                event.setLine(0, replaceColor("&7[&6KitPvP&7]"));
                event.setLine(1, replaceColor("&3&lJoin Game"));
                event.getBlock().getState().update();
            }
        }
    }
}
