package com.github.tacticaldevmc.ddgkitpvp.listeners.players;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.menus.KitSelectionMenu;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import com.github.tacticaldevmc.ddgkitpvp.spawns.Spawns;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class PlayerInteractSignListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Spawns spawns = new Spawns();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.LEGACY_SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(1).equalsIgnoreCase("[KitPvP]")) {
                    User user = DDGKitpvp.getInstance().getPlayer(player);
                    IPlayer iPlayer = DDGKitpvp.getInstance().getPlayer();
                    user.setInGame(true);
                    iPlayer.save(user);

                    List<Location> locations = spawns.getSpawns();
                    player.teleport(locations.get(new Random().nextInt(locations.size())));
                }
            }
        }
    }
}
