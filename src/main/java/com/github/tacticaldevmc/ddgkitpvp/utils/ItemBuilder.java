//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.tacticaldevmc.ddgkitpvp.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import static com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp.replaceColor;

public class ItemBuilder {
    private ItemStack is;
    private ItemMeta im;
    private String skullOwner;

    public ItemMeta getItemMeta() {
        return this.im;
    }

    public void setItemMeta(ItemMeta itemMeta) {
        this.im = itemMeta;
    }

    public ItemStack getItemstack() {
        return this.is;
    }

    public ItemBuilder(ItemBuilder builder) {
        this(builder.toItemStack().clone());
    }

    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder(ItemStack is) {
        this.is = is.clone();
        this.im = is.getItemMeta();
    }

    public ItemBuilder(Material m, int amount) {
        this.is = new ItemStack(m, amount);
        this.im = this.is.getItemMeta();
    }

    public ItemBuilder(Material m, int amount, byte durability) {
        this.is = new ItemStack(m, amount, (short) durability);
        this.im = this.is.getItemMeta();
    }

    public ItemBuilder clone() {
        return new ItemBuilder(this.is);
    }

    public ItemBuilder setDurability(short dur) {
        this.is.setDurability(dur);
        return this;
    }

    public ItemBuilder setType(Material m) {
        this.is.setType(m);
        return this;
    }

    public String getName() {
        ItemMeta im = this.is.getItemMeta();
        return im.getDisplayName();
    }

    public ItemBuilder setName(String name) {
        ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(replaceColor(name));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.is.setAmount(amount);
        return this;
    }

    public List<String> getLore() {
        return this.is.getItemMeta().getLore();
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta im = this.is.getItemMeta();
        List<String> color = new ArrayList();
        String[] var4 = lore;
        int var5 = lore.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String l = var4[var6];
            color.add(replaceColor(l));
        }

        im.setLore(color);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = this.is.getItemMeta();
        List<String> color = new ArrayList();
        Iterator var4 = lore.iterator();

        while (var4.hasNext()) {
            String l = (String) var4.next();
            color.add(replaceColor(l));
        }

        im.setLore(color);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLore() {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList(im.getLore());
        lore.clear();
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setPotionEffect(PotionEffect effect) {
        try {
            PotionMeta meta = (PotionMeta) this.is.getItemMeta();
            meta.setMainEffect(effect.getType());
            meta.addCustomEffect(effect, false);
            this.is.setItemMeta(meta);
            return this;
        } catch (ClassCastException var3) {
            return this;
        }
    }

    public ItemBuilder hideAttributes() {
        ItemMeta im = this.is.getItemMeta();
        im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_PLACED_ON});
        im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder createPotion(boolean splash) {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        PotionEffect main = null;

        PotionEffect current;
        for (Iterator var5 = ((PotionMeta) this.is.getItemMeta()).getCustomEffects().iterator(); var5.hasNext(); meta.addCustomEffect(current, true)) {
            current = (PotionEffect) var5.next();
            if (main == null) {
                main = current;
            }
        }

        if (main == null) {
            return this;
        } else {
            potion.setItemMeta(meta);
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
            meta.setMainEffect(main.getType());
            Potion po = new Potion(PotionType.getByEffect(main.getType()));
            po.setSplash(splash);
            po.apply(potion);
            return new ItemBuilder(potion);
        }
    }

    public ItemBuilder setSplash(boolean splash) {
        try {
            Potion potion = Potion.fromItemStack(this.is);
            potion.setSplash(splash);
            this.is = potion.toItemStack(this.is.getAmount());
            return this;
        } catch (ClassCastException var3) {
            return this;
        }
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        this.is.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchantGlow(Enchantment ench, int level) {
        ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        this.is.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setInfinityDurability() {
        this.is.setDurability((short) 32767);
        return this;
    }

    public ItemBuilder addLoreLines(List<String> line) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList();
        if (im.hasLore()) {
            lore = new ArrayList(im.getLore());
        }

        Iterator var4 = line.iterator();

        while (var4.hasNext()) {
            String s = (String) var4.next();
            if (s != null) {
                lore.add(replaceColor(s));
            }
        }

        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLines(String... line) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList();
        if (im.hasLore()) {
            lore = new ArrayList(im.getLore());
        }

        String[] var4 = line;
        int var5 = line.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String s = var4[var6];
            lore.add(replaceColor(s));
        }

        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(String line) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList(im.getLore());
        if (!lore.contains(line)) {
            return this;
        } else {
            lore.remove(line);
            im.setLore(lore);
            this.is.setItemMeta(im);
            return this;
        }
    }

    public ItemBuilder removeLoreLine(int index) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList(im.getLore());
        if (index >= 0 && index <= lore.size()) {
            lore.remove(index);
            im.setLore(lore);
            this.is.setItemMeta(im);
            return this;
        } else {
            return this;
        }
    }

    public ItemBuilder addLoreLine(String line) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList();
        if (im.hasLore()) {
            lore = new ArrayList(im.getLore());
        }

        lore.add(replaceColor(line));
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String line, int pos) {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setDyeColor(DyeColor color) {
        this.is.setDurability((short) color.getDyeData());
        return this;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public ItemBuilder setWoolColor(DyeColor color) {
        if (!this.is.getType().equals(Material.LEGACY_WOOL)) {
            return this;
        } else {
            this.is.setDurability((short) color.getDyeData());
            return this;
        }
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) this.is.getItemMeta();
            im.setColor(color);
            this.is.setItemMeta(im);
        } catch (ClassCastException var3) {
        }

        return this;
    }

    public ItemStack toItemStack() {
        return this.is;
    }

    public String getSkullOwner() {
        return this.skullOwner;
    }

    public ItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) this.is.getItemMeta();
            im.setOwner(owner);
            this.is.setItemMeta(im);
        } catch (ClassCastException var3) {
        }

        return this;
    }
}
