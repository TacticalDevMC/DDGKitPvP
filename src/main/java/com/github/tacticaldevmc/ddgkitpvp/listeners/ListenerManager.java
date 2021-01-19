package com.github.tacticaldevmc.ddgkitpvp.listeners;

import com.github.tacticaldev.menu.check.InventoryCheck;
import com.github.tacticaldev.menu.managers.MenuManager;
import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.listeners.players.*;
import com.github.tacticaldevmc.ddgkitpvp.signs.GameSign;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.Arrays;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class ListenerManager {

    public void setup() {
        loadListener(
                new PlayerConnectionListener(),
                new PvPListener(),
                new InventoryCheck(),
                new MenuManager(),
                new PlayerInteractListener(),
                new GameSign(),
                new PlayerInteractSignListener(),
                new PlayerListeners());
    }

    private void loadListener(Listener... listeners) {
        Arrays.asList(listeners).forEach(l -> {
            Bukkit.getPluginManager().registerEvents(l, DDGKitpvp.getInstance());
        });
    }

}
