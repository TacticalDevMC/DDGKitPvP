//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.tacticaldevmc.ddgkitpvp.mysql.builder;

import com.zaxxer.hikari.HikariDataSource;

public class SQLBuilder implements SQLImpl {
    private HikariDataSource hikari;
    private String host;
    private String user;
    private String password;
    private String database;
    private int port;

    public SQLBuilder(String host, String user, String password, String database, int port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    public SQLBuilder() {
    }

    public SQLBuilder start() {
        this.hikari = new HikariDataSource();
        this.hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        this.hikari.addDataSourceProperty("serverTimezone", "UTC");
        this.hikari.addDataSourceProperty("serverName", this.host);
        this.hikari.addDataSourceProperty("port", this.port);
        this.hikari.addDataSourceProperty("databaseName", this.database);
        this.hikari.addDataSourceProperty("user", this.user);
        this.hikari.addDataSourceProperty("password", this.password);
        this.hikari.setLeakDetectionThreshold(10000L);
        this.hikari.setMaximumPoolSize(10);
        this.hikari.addDataSourceProperty("cachePrepStmts", "true");
        this.hikari.addDataSourceProperty("prepStmtCacheSize", "250");
        this.hikari.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.hikari.setPoolName("DDGKitPvP");
        this.hikari.setConnectionTestQuery("SELECT 1");
        return this;
    }

    public SQLBuilder stop() {
        this.hikari.close();
        return this;
    }

    public HikariDataSource getHikari() {
        return this.hikari;
    }
}
