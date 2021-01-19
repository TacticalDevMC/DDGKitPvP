package com.github.tacticaldevmc.ddgkitpvp;

import com.github.tacticaldev.API;
import com.github.tacticaldevmc.ddgkitpvp.board.Boards;
import com.github.tacticaldevmc.ddgkitpvp.commands.JoinCommand;
import com.github.tacticaldevmc.ddgkitpvp.commands.LeaveCommand;
import com.github.tacticaldevmc.ddgkitpvp.commands.SpawnsCommand;
import com.github.tacticaldevmc.ddgkitpvp.kit.Kits;
import com.github.tacticaldevmc.ddgkitpvp.listeners.ListenerManager;
import com.github.tacticaldevmc.ddgkitpvp.mysql.TableCreator;
import com.github.tacticaldevmc.ddgkitpvp.mysql.builder.SQLBuilder;
import com.github.tacticaldevmc.ddgkitpvp.players.KitPvPPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import com.github.tacticaldevmc.ddgkitpvp.settings.SQLSettings;
import com.github.tacticaldevmc.ddgkitpvp.spawns.Spawns;
import com.github.tacticaldevmc.ddgkitpvp.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class DDGKitpvp extends JavaPlugin {

    @Getter
    private static DDGKitpvp instance;
    @Getter
    private SQLBuilder sqlBuilder;

    private final SQLSettings sqlSettings = new SQLSettings();
    private KitPvPPlayer kitPvPPlayer;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        new API(this);

        // Start the hikari database
        sqlBuilder = new SQLBuilder(sqlSettings.getHost(), sqlSettings.getUser(), sqlSettings.getPassword(), sqlSettings.getDatabase(), sqlSettings.getPort());
        sqlBuilder.start();
        new TableCreator().setup(); // Setup the tables in the database.

        // Loading players and scoreboard
        kitPvPPlayer = new KitPvPPlayer();
        for (Player player : Bukkit.getOnlinePlayers()) {
            kitPvPPlayer.load(player.getUniqueId()); // This load's the player data. If the player is not registered on the database, no worries, the player will be created then!
            new Boards().run(player);
        }

        // Loading listeners and commands
        new ListenerManager().setup();
        Bukkit.getPluginCommand("leave").setExecutor(new LeaveCommand());
        Bukkit.getPluginCommand("join").setExecutor(new JoinCommand());
        Bukkit.getPluginCommand("spawns").setExecutor(new SpawnsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;

        // Check if hikari != null, then stop hikari.
        if (sqlBuilder.getHikari() != null) {
            sqlBuilder.stop();
        }

        // Saving player
        for (Player player : Bukkit.getOnlinePlayers()) {
            kitPvPPlayer.save(getPlayer(player)); // This saves the player data.
        }
    }

    /**
     * This replaces the message into a color.
     *
     * @param msg message
     * @return string
     */
    public static String replaceColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * This get the player from the 'user' class.
     *
     * @param player the player
     * @return user
     */
    public User getPlayer(Player player) {
        return kitPvPPlayer.load(player.getUniqueId());
    }

    public IPlayer getPlayer() {
        return kitPvPPlayer;
    }

    public ItemStack getItem() {
        return new ItemBuilder(Material.REDSTONE_TORCH).setName("&b&lKit Selector").toItemStack();
    }


    public void giveKit(Player player, Kits kit) {
        switch (kit) {
            case SWORDSMAN:
                player.getInventory().addItem(kit.getItems().get(0).toItemStack());
                player.getInventory().setHelmet(kit.getArmor().get(0));
                player.getInventory().setChestplate(kit.getArmor().get(1));
                break;
            default:
        }
    }

}
