package com.github.tacticaldevmc.ddgkitpvp.mysql;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.handlers.LogHandler;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
public class TableCreator {

    // Variable
    private final HikariDataSource hikari = DDGKitpvp.getInstance().getSqlBuilder().getHikari();
    private final String TABLE_STATS = "stats";
    private final String TABLE_SPAWNS = "spawns";

    /**
     * This is creating the table 'stats'
     */
    private void statsTable() {
        try (Connection connection = hikari.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + TABLE_STATS + "` " +
                    "(id INT NOT NULL AUTO_INCREMENT UNIQUE, " +
                    "uuid varchar(255) PRIMARY KEY, " +
                    "name varchar(255), " +
                    "kitSelected varchar(255), " +
                    "points INT, " +
                    "kills INT, " +
                    "deaths INT, " +
                    "killstreaks INT, " +
                    "currentKillStreak INT, " +
                    "inGame BOOLEAN)")) {
                statement.execute();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException("TableCreator:statsTable", e);
        }
    }

    private void spawnsTable() {
        try (Connection connection = hikari.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + TABLE_SPAWNS + "` " +
                    "(id INT NOT NULL AUTO_INCREMENT UNIQUE, " +
                    "name varchar(255) PRIMARY KEY, " +
                    "loc_world varchar (255), " +
                    "loc_x INT, " +
                    "loc_y INT, " +
                    "loc_z INT, " +
                    "loc_yaw FLOAT," +
                    "loc_pitch FLOAT)")) {
                statement.execute();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LogHandler.getHandler().logException("TableCreator:spawnsTable", e);
        }
    }

    /**
     * This will be used to setup all the tables. ALL-IN :-)
     */
    public void setup() {
        statsTable();
        spawnsTable();
    }
}
