package com.github.tacticaldevmc.ddgkitpvp.settings;

import lombok.Getter;

/**
 * @AUTHOR: TacticalDev
 * Copyright © 2021, Joran Huibers, All rights reserved.
 */
@Getter
public class SQLSettings {

    private static SQLSettings instance;

    public static SQLSettings getInstance() {
        return instance == null ? new SQLSettings() : instance;
    }

    private final String host = "176.9.123.6";
    private final String user = "u2_O445pHX5tz";
    private final String password = "yqj0.@7yRW=nQFq4h.pLRCxt";
    private final String database = "s2_ddgkitpvp";
    private Integer port = 3306;

}
