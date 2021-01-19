package com.github.tacticaldevmc.ddgkitpvp.spawns;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.handlers.LogHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class Spawns {

    private String name;
    private Player player;

    public Spawns(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    public Spawns() {

    }

    public void create() {
        try (Connection connection = DDGKitpvp.getInstance().getSqlBuilder().getHikari().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `spawns` " +
                    "(name, " +
                    "loc_world, " +
                    "loc_x, " +
                    "loc_y, " +
                    "loc_z, " +
                    "loc_yaw, " +
                    "loc_pitch)" +
                    " VALUES " +
                    "(?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?, " +
                    "?)")) {
                statement.setString(1, name);
                statement.setString(2, player.getLocation().getWorld().getName());
                statement.setInt(3, player.getLocation().getBlockX());
                statement.setInt(4, player.getLocation().getBlockY());
                statement.setInt(5, player.getLocation().getBlockZ());
                statement.setFloat(6, player.getLocation().getYaw());
                statement.setFloat(7, player.getLocation().getPitch());
                statement.executeUpdate();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException(e);
        }
    }

    public Location getLocation() {
        try (Connection connection = DDGKitpvp.getInstance().getSqlBuilder().getHikari().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `spawns` WHERE name=?")) {
                statement.setString(1, name);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String world = resultSet.getString("loc_world");
                        int x = resultSet.getInt("loc_x");
                        int y = resultSet.getInt("loc_y");
                        int z = resultSet.getInt("loc_z");
                        float yaw = resultSet.getFloat("loc_yaw");
                        float pitch = resultSet.getFloat("loc_pitch");

                        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException(e);
        }
        return null;
    }

    public Location getLocation(String spawn) {
        try (Connection connection = DDGKitpvp.getInstance().getSqlBuilder().getHikari().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `spawns` WHERE name=?")) {
                statement.setString(1, spawn);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String world = resultSet.getString("loc_world");
                        int x = resultSet.getInt("loc_x");
                        int y = resultSet.getInt("loc_y");
                        int z = resultSet.getInt("loc_z");
                        float yaw = resultSet.getFloat("loc_yaw");
                        float pitch = resultSet.getFloat("loc_pitch");

                        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException(e);
        }
        return null;
    }

    public void teleport() {
        player.teleport(getLocation());
    }

    private ArrayList<Location> locations = new ArrayList<>();

    public ArrayList<Location> getSpawns() {
        try (Connection connection = DDGKitpvp.getInstance().getSqlBuilder().getHikari().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `spawns`")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String world = resultSet.getString("loc_world");
                        int x = resultSet.getInt("loc_x");
                        int y = resultSet.getInt("loc_y");
                        int z = resultSet.getInt("loc_z");
                        float yaw = resultSet.getFloat("loc_yaw");
                        float pitch = resultSet.getFloat("loc_pitch");

                        locations.add(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException(e);
        }
        return locations;
    }

    public void remove() {
        try (Connection connection = DDGKitpvp.getInstance().getSqlBuilder().getHikari().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE * FROM `spawns` WHERE name=?")) {

                statement.setString(1, name);
                statement.executeUpdate();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException(e);
        }
    }

    public boolean exists() {
        try (Connection connection = DDGKitpvp.getInstance().getSqlBuilder().getHikari().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `spawns` WHERE name=?")) {
                statement.setString(1, name);

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
            LogHandler.getHandler().logException(e);
        }
        return false;
    }
}
