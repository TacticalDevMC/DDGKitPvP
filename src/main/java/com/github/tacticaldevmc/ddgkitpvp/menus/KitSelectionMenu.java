package com.github.tacticaldevmc.ddgkitpvp.menus;

import com.github.tacticaldev.menu.APIMenu;
import com.github.tacticaldev.menu.interfaces.MenuClick;
import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.kit.Kits;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class KitSelectionMenu {

    private String INVENTORY_NAME = replaceColor("&aSelect a kit");
    private APIMenu menu;
    private Player player;
    private User user;
    private IPlayer iPlayer;

    public KitSelectionMenu(Player player) {
        this.player = player;
        user = DDGKitpvp.getInstance().getPlayer(player);
        iPlayer = DDGKitpvp.getInstance().getPlayer();

        menu = new APIMenu(INVENTORY_NAME, 5);
    }

    public void open() {
        menu.setUpdateHandler(() -> {
            if (user.getKitSelected().equals("None")) {
                menu.addMenuClick(Kits.NONE.getIcon().addUnsafeEnchantment(Enchantment.MENDING, 1).toItemStack(), new MenuClick() {
                    @Override
                    public void onItemClick(Player player, ClickType clickType) {
                        player.sendMessage(replaceColor("&cNo kit selected!"));
                    }
                }, Kits.NONE.getSlot());
            }

            for (Kits kit : Kits.values()) {
                menu.addMenuClick(kit.getIcon().toItemStack(), new MenuClick() {
                    @Override
                    public void onItemClick(Player player, ClickType clickType) {
                        user.setKitSelected(kit.getName());
                        iPlayer.save(user);
                        player.sendMessage(replaceColor("&aYou have selected the kit &e" + user.getKitSelected() + "&a!"));
                        player.closeInventory();
                    }
                }, kit.getSlot());
            }

        });

        menu.setAutoUpdate();
        menu.open(player);
    }
}
