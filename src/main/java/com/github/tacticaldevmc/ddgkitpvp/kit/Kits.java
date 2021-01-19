package com.github.tacticaldevmc.ddgkitpvp.kit;

import com.github.tacticaldevmc.ddgkitpvp.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
@Getter
@AllArgsConstructor
public enum Kits {

    NONE(new ItemBuilder(Material.BARRIER).setName("&cNo Kit Selected"), "None", replaceColor("&cNo Kit selected!"), null, null, 40),
    SWORDSMAN(new ItemBuilder(Material.IRON_SWORD).setName("&bSwordsman"), "Swordsman", replaceColor("&cWhy we use the Default?"),
            Arrays.asList(new ItemBuilder(Material.IRON_SWORD)
                    .addEnchantGlow(Enchantment.FIRE_ASPECT, 1)
                    .setName("&bSwordsman Sword")), Arrays.asList(new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_CHESTPLATE)), 10);

    private ItemBuilder icon;
    private String name;
    private String description;
    private List<ItemBuilder> items;
    private List<ItemStack> armor;
    private int slot;
}


