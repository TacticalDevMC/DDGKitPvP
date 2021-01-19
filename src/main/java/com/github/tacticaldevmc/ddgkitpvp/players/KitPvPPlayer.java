package com.github.tacticaldevmc.ddgkitpvp.players;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.handlers.LogHandler;
import com.github.tacticaldevmc.ddgkitpvp.players.impl.IPlayer;
import com.github.tacticaldevmc.ddgkitpvp.players.users.User;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class KitPvPPlayer implements IPlayer {

    // Variables
    private final HikariDataSource hikari = DDGKitpvp.getInstance().getSqlBuilder().getHikari();
    private final String TABLE = "stats";

    /**
     * Create a player into the database.
     *
     * @param player the player to create
     */
    @Override
    public void create(Player player) {
        try (Connection connection = hikari.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `" + TABLE + "` " +
                    "(uuid, " +
                    "name, " +
                    "kitSelected, " +
                    "points, " +
                    "kills, " +
                    "deaths, " +
                    "killstreaks, " +
                    "currentKillStreak, " +
                    "inGame)" +
                    " VALUES " +
                    "(?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?)")) {
                statement.setString(1, player.getUniqueId().toString());
                statement.setString(2, player.getName());
                statement.setString(3, "None");
                statement.setInt(4, 0);
                statement.setInt(5, 0);
                statement.setInt(6, 0);
                statement.setInt(7, 0);
                statement.setInt(8, 0);
                statement.setBoolean(9, false);

                statement.execute();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException("KitPvPPlayer:create", e);
        }
    }

    /**
     * This deletes the player from the database. ALL THE DATA!
     *
     * @param value uuid of the player
     */
    @Override
    public void delete(UUID value) {
        try (Connection connection = hikari.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE * FROM `" + TABLE + "` WHERE uuid=?")) {
                statement.setString(1, value.toString());
                statement.executeUpdate();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException("KitPvPPlayer:delete", e);
        }
    }

    /**
     * This loads the player from the database, when player isn't registered to the database
     * the player will be created.
     *
     * @param value uuid of the player
     * @return user
     */
    @Override
    public User load(UUID value) {
        return CompletableFuture.supplyAsync(() -> {
            Player player = Bukkit.getPlayer(value);

            if (!exists(value) && player == null)
                create(Bukkit.getPlayer(value));

            try (Connection connection = hikari.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + TABLE + "` WHERE uuid=?")) {
                    statement.setString(1, value.toString());

                    try (ResultSet resultSet = statement.executeQuery()) {

                        while (resultSet.next()) {
                            User user = new User(value, resultSet.getString("name"), resultSet.getString("kitSelected"), resultSet.getInt("points"), resultSet.getInt("kills"), resultSet.getInt("deaths"), resultSet.getInt("killstreaks"), resultSet.getInt("currentKillStreak"), resultSet.getBoolean("inGame"));
                            user.setPlayer(player);

                            return user;
                        }
                        resultSet.close();
                        statement.close();
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                LogHandler.getHandler().logException("KitPvPPlayer:delete", e);
            }
            return null;
        }).join();
    }

    /**
     * This saves the user data into the database.
     *
     * @param user the user to save
     */
    @Override
    public void save(User user) {
        try (Connection connection = hikari.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE `" + TABLE + "` SET name=?, kitSelected=?, points=?, kills=?, deaths=?, killstreaks=?, currentKillStreak=?, inGame=? WHERE uuid=?")) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getKitSelected());
                statement.setInt(3, user.getPoints());
                statement.setInt(4, user.getKills());
                statement.setInt(5, user.getDeaths());
                statement.setInt(6, user.getKillStreaks());
                statement.setInt(7, user.getCurrentKillStreak());
                statement.setBoolean(8, user.isInGame());
                statement.setString(9, user.getUuid().toString());

                statement.executeUpdate();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException("KitPvPPlayer:save", e);
        }
    }

    /**
     * This is checking if the player is registered
     * into the databases.
     *
     * @param uuid uuid of the player
     * @return boolean
     */
    @Override
    public boolean exists(UUID uuid) {
        try (Connection connection = hikari.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `" + TABLE + "` WHERE uuid=?")) {
                statement.setString(1, uuid.toString());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        return true;
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException("KitPvPPlayer:exists", e);
        }
        return false;
    }
}
