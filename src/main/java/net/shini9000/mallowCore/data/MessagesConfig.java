package net.shini9000.mallowCore.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessagesConfig {
    private final JavaPlugin plugin;
    private final File file;
    private FileConfiguration config;

    public MessagesConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "Messages/Messages.yml");

        // Ensure folder exists
        file.getParentFile().mkdirs();

        // Load existing config or create empty one
        this.config = YamlConfiguration.loadConfiguration(file);

        // Load defaults from inside the JAR
        FileConfiguration defaults = YamlConfiguration.loadConfiguration(
                new InputStreamReader(plugin.getResource("Messages/Messages.yml"))
        );

        // Merge defaults into existing config (only missing keys)
        config.setDefaults(defaults);
        config.options().copyDefaults(true);

        save();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public String getMessage(String path) {
        return config.getString(path);
    }

    public java.util.List<String> getStringList(String path) {
        return config.getStringList(path);
    }
}
