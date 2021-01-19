//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.tacticaldevmc.ddgkitpvp.handlers;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class LogHandler {
    private static final Logger LOGGER = Logger.getLogger("DDGKitPvP");
    private static LogHandler handler;

    public LogHandler() {
    }

    public static Logger getLogger() {
        return LOGGER == null ? Logger.getLogger("DDGKitPvP") : LOGGER;
    }

    public static LogHandler getHandler() {
        return handler == null ? new LogHandler() : handler;
    }

    public void logException(Exception e) {
        Bukkit.getConsoleSender().sendMessage("DDGKitPvP" + " ");
        LOGGER.warning("Discovered DDGKitPvP Exception!");
        LOGGER.warning("---------------------------");
        LOGGER.warning("Exception: " + e.toString());
        StackTraceElement[] var2 = e.getStackTrace();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            StackTraceElement s = var2[var4];
            LOGGER.warning(s.getClassName() + " [" + s.getLineNumber() + "/" + s.getMethodName() + "] [" + s.getFileName() + "]");
        }

        LOGGER.warning("---------------------------");
    }

    public void logException(String prefix, Exception e) {
        Bukkit.getConsoleSender().sendMessage("[DDGKitPvP] " + prefix);
        LOGGER.warning("Discovered DDGKitPvP Exception!");
        LOGGER.warning("---------------------------");
        LOGGER.warning("Exception: " + e.toString());
        StackTraceElement[] var3 = e.getStackTrace();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            StackTraceElement s = var3[var5];
            LOGGER.warning(s.getClassName() + " [" + s.getLineNumber() + "/" + s.getMethodName() + "] [" + s.getFileName() + "]");
        }

        LOGGER.warning("---------------------------");
    }

    public void logMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("[DDGKitPvP] [Message]");
        LOGGER.warning("---------------------------");
        LOGGER.warning(message);
        LOGGER.warning("---------------------------");
    }
}
