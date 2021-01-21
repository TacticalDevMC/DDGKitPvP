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

    private final String host = "";
    private final String user = "";
    private final String password = "";
    private final String database = "";
    private Integer port = 3306;

}
