//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.tacticaldevmc.ddgkitpvp.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.github.tacticaldevmc.ddgkitpvp.DDGKitpvp;
import com.github.tacticaldevmc.ddgkitpvp.handlers.LogHandler;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Config {
    private final File folder;
    private final String fileName;
    private boolean def;
    public FileConfiguration configuration;
    private File configFile;

    public Config(File dir, String name, boolean def) {
        this.folder = dir;
        if (name == null) {
            this.fileName = "Unknown";
            name = "Unknown";
        } else {
            this.fileName = name + (name.endsWith(".yml") ? "" : ".yml");
            this.def = def;
            this.reload();
        }
    }

    public Config(String name, boolean isDef) {
        this(DDGKitpvp.getInstance().getDataFolder(), name, isDef);
    }

    public Config(String name) {
        this(DDGKitpvp.getInstance().getDataFolder(), name, true);
    }

    public Config(File file) {
        this(file, (String) null, true);
    }

    public Config(File dir, String name) {
        this(dir, name, true);
    }

    public FileConfiguration getConfiguration() {
        if (this.configuration == null) {
            this.reload();
        }

        return this.configuration;
    }

    public Location getLocation(String path, Server server) {
        String worldString = (path == null ? "" : path + ".") + "world";
        String worldName = this.getConfiguration().getString(worldString);
        if (worldName != null && !worldName.isEmpty()) {
            World world = server.getWorld(worldName);
            if (world == null) {
                try {
                    throw new InvalidWorldException(worldName);
                } catch (InvalidWorldException var7) {
                    var7.printStackTrace();
                }
            }

            return new Location(world, this.getConfiguration().getDouble((path == null ? "" : path + ".") + "x", 0.0D), this.getConfiguration().getDouble((path == null ? "" : path + ".") + "y", 0.0D), this.getConfiguration().getDouble((path == null ? "" : path + ".") + "z", 0.0D), (float) this.getConfiguration().getDouble((path == null ? "" : path + ".") + "yaw", 0.0D), (float) this.getConfiguration().getDouble((path == null ? "" : path + ".") + "pitch", 0.0D));
        } else {
            return null;
        }
    }

    public Set<String> getKeys(boolean deep) {
        return this.getConfiguration().getKeys(deep);
    }

    public Map<String, Object> getValues(boolean deep) {
        return this.getConfiguration().getValues(deep);
    }

    public boolean contains(String path) {
        return this.getConfiguration().contains(path);
    }

    public boolean contains(String path, boolean ignoreDefault) {
        return this.getConfiguration().contains(path, ignoreDefault);
    }

    public boolean isSet(String path) {
        return this.getConfiguration().isSet(path);
    }

    public String getCurrentPath() {
        return this.getConfiguration().getCurrentPath();
    }

    public void addDefault(String path, Object value) {
        this.getConfiguration().addDefault(path, value);
    }

    public ConfigurationSection getDefaultSection() {
        return this.getConfiguration().getDefaultSection();
    }

    public void set(String path, Object value) {
        this.getConfiguration().set(path, value);
    }

    public Object get(String path) {
        return this.getConfiguration().get(path);
    }

    public Object get(String path, Object def) {
        return this.get(path, def);
    }

    public ConfigurationSection createSection(String path) {
        return this.getConfiguration().createSection(path);
    }

    public ConfigurationSection createSection(String path, Map<?, ?> map) {
        return this.getConfiguration().createSection(path, map);
    }

    public String getString(String path) {
        return this.getConfiguration().getString(path);
    }

    public String getString(String path, String def) {
        return this.getConfiguration().getString(path, def);
    }

    public boolean isString(String path) {
        return this.getConfiguration().isString(path);
    }

    public int getInt(String path) {
        return this.getConfiguration().getInt(path);
    }

    public int getInt(String path, int def) {
        return this.getConfiguration().getInt(path, def);
    }

    public boolean isInt(String path) {
        return this.getConfiguration().isInt(path);
    }

    public boolean getBoolean(String path) {
        return this.getConfiguration().getBoolean(path);
    }

    public boolean getBoolean(String path, boolean def) {
        return this.getConfiguration().getBoolean(path, def);
    }

    public boolean isBoolean(String path) {
        return this.getConfiguration().isBoolean(path);
    }

    public double getDouble(String path) {
        return this.getConfiguration().getDouble(path);
    }

    public double getDouble(String path, double def) {
        return this.getConfiguration().getDouble(path, def);
    }

    public boolean isDouble(String path) {
        return this.getConfiguration().isDouble(path);
    }

    public long getLong(String path) {
        return this.getConfiguration().getLong(path);
    }

    public long getLong(String path, long def) {
        return this.getConfiguration().getLong(path, def);
    }

    public boolean isLong(String path) {
        return this.getConfiguration().isLong(path);
    }

    public List<?> getList(String path) {
        return this.getConfiguration().getList(path);
    }

    public List<?> getList(String path, List<?> def) {
        return this.getConfiguration().getList(path, def);
    }

    public boolean isList(String path) {
        return this.getConfiguration().isList(path);
    }

    public List<String> getStringList(String path) {
        return this.getConfiguration().getStringList(path);
    }

    public List<Integer> getIntegerList(String path) {
        return this.getConfiguration().getIntegerList(path);
    }

    public List<Boolean> getBooleanList(String path) {
        return this.getConfiguration().getBooleanList(path);
    }

    public List<Double> getDoubleList(String path) {
        return this.getConfiguration().getDoubleList(path);
    }

    public List<Float> getFloatList(String path) {
        return this.getConfiguration().getFloatList(path);
    }

    public List<Long> getLongList(String path) {
        return this.getConfiguration().getLongList(path);
    }

    public List<Byte> getByteList(String path) {
        return this.getConfiguration().getByteList(path);
    }

    public List<Character> getCharacterList(String path) {
        return this.getConfiguration().getCharacterList(path);
    }

    public List<Short> getShortList(String path) {
        return this.getConfiguration().getShortList(path);
    }

    public List<Map<?, ?>> getMapList(String path) {
        return this.getConfiguration().getMapList(path);
    }

    public Vector getVector(String path) {
        return this.getConfiguration().getVector(path);
    }

    public Vector getVector(String path, Vector def) {
        return this.getConfiguration().getVector(path, def);
    }

    public boolean isVector(String path) {
        return this.getConfiguration().isVector(path);
    }

    public OfflinePlayer getOfflinePlayer(String path) {
        return this.getConfiguration().getOfflinePlayer(path);
    }

    public OfflinePlayer getOfflinePlayer(String path, OfflinePlayer def) {
        return this.getConfiguration().getOfflinePlayer(path, def);
    }

    public boolean isOfflinePlayer(String path) {
        return this.getConfiguration().isOfflinePlayer(path);
    }

    public ItemStack getItemStack(String path) {
        return this.getConfiguration().getItemStack(path);
    }

    public ItemStack getItemStack(String path, ItemStack def) {
        return this.getConfiguration().getItemStack(path, def);
    }

    public boolean isItemStack(String path) {
        return this.getConfiguration().isItemStack(path);
    }

    public Color getColor(String path) {
        return this.getConfiguration().getColor(path);
    }

    public Color getColor(String path, Color def) {
        return this.getConfiguration().getColor(path, def);
    }

    public boolean isColor(String path) {
        return this.getConfiguration().isColor(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return this.getConfiguration().getConfigurationSection(path);
    }

    public boolean isConfigurationSection(String path) {
        return this.getConfiguration().isConfigurationSection(path);
    }

    public void setProperty(String path, Location loc) {
        this.getConfiguration().set((path == null ? "" : path + ".") + "world", loc.getWorld().getName());
        this.getConfiguration().set((path == null ? "" : path + ".") + "x", loc.getX());
        this.getConfiguration().set((path == null ? "" : path + ".") + "y", loc.getY());
        this.getConfiguration().set((path == null ? "" : path + ".") + "z", loc.getZ());
        this.getConfiguration().set((path == null ? "" : path + ".") + "yaw", loc.getYaw());
        this.getConfiguration().set((path == null ? "" : path + ".") + "pitch", loc.getPitch());
    }

    public boolean hasProperty(String path) {
        return this.getConfiguration().isSet(path);
    }

    public void setProperty(String path, ItemStack stack) {
        Map<String, Object> map = new HashMap();
        map.put("type", stack.getType().toString());
        map.put("amount", stack.getAmount());
        map.put("damage", stack.getDurability());
        Map<Enchantment, Integer> enchantments = stack.getEnchantments();
        if (!enchantments.isEmpty()) {
            Map<String, Integer> enchant = new HashMap();
            Iterator var6 = enchantments.entrySet().iterator();

            while (var6.hasNext()) {
                Entry<Enchantment, Integer> entry = (Entry) var6.next();
                enchant.put(((Enchantment) entry.getKey()).getName().toLowerCase(Locale.ENGLISH), (Integer) entry.getValue());
            }

            map.put("enchant", enchant);
        }

        this.getConfiguration().set(path, map);
    }

    public void setProperty(String path, List object) {
        this.getConfiguration().set(path, new ArrayList(object));
    }

    public void setProperty(String path, Map object) {
        this.getConfiguration().set(path, new LinkedHashMap(object));
    }

    public Object getProperty(String path) {
        return this.getConfiguration().get(path);
    }

    public void setProperty(String path, BigDecimal bigDecimal) {
        this.getConfiguration().set(path, bigDecimal.toString());
    }

    public void setProperty(String path, Object object) {
        this.getConfiguration().set(path, object);
    }

    public void removeProperty(String path) {
        this.getConfiguration().set(path, (Object) null);
    }

    public synchronized BigDecimal getBigDecimal(String path, BigDecimal def) {
        String input = this.getConfiguration().getString(path);
        return toBigDecimal(input, def);
    }

    public static BigDecimal toBigDecimal(String input, BigDecimal def) {
        if (input != null && !input.isEmpty()) {
            try {
                return new BigDecimal(input, MathContext.DECIMAL128);
            } catch (ArithmeticException | NumberFormatException var3) {
                return def;
            }
        } else {
            return def;
        }
    }

    public File getFile() {
        return this.configFile;
    }

    public boolean reload() {
        if (!this.folder.exists()) {
            try {
                if (this.folder.mkdir()) {
                    LogHandler.getHandler().logMessage("- Created a new configuration file!");
                } else {
                    LogHandler.getHandler().logMessage("- §cUnable to create folder " + this.folder.getName());
                }
            } catch (Exception var4) {
                LogHandler.getHandler().logMessage("Failed to reload " + this.fileName + " config.yml.");
                return false;
            }
        }

        this.configFile = new File(this.folder, this.fileName);
        this.configuration = YamlConfiguration.loadConfiguration(this.configFile);
        if (this.def) {
            InputStream defaultConfigStream = DDGKitpvp.getInstance().getResource(this.fileName);
            if (defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultConfigStream));
                this.configuration.setDefaults(defaultConfig);
                this.saveDefaultConfig();
            }
        } else if (!this.configFile.exists()) {
            try {
                this.configFile.createNewFile();
            } catch (Exception var3) {
                LogHandler.getHandler().logMessage("Failed to reload " + this.fileName + " config.yml.");
            }
        }

        return true;
    }

    public boolean save() {
        if (this.configuration != null && this.configFile != null) {
            try {
                this.getConfiguration().save(this.configFile);
                return true;
            } catch (IOException var2) {
                LogHandler.getHandler().logMessage("Failed to save config.yml to " + this.configFile.getName());
                var2.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    private void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(DDGKitpvp.getInstance().getDataFolder(), this.fileName);
        }

        if (!this.configFile.exists()) {
            DDGKitpvp.getInstance().saveResource(this.fileName, false);
        }

    }
}
