//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.tacticaldevmc.ddgkitpvp.utils;

public class InvalidWorldException extends Exception {
    private final String world;

    public InvalidWorldException(String world) {
        super("Invalid world!");
        this.world = world;
    }

    public String getWorld() {
        return this.world;
    }
}
