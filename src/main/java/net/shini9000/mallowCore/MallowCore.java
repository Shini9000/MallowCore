package net.shini9000.mallowCore;

import net.shini9000.mallowCore.commands.MUCommand;
import net.shini9000.mallowCore.data.MessagesConfig;
import net.shini9000.mallowCore.listeners.JoinLeave;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

public final class MallowCore extends JavaPlugin {
    public static MallowCore THIS;
    private BukkitTask task;
    private MessagesConfig messagesConfig;


    @Override
    public void onEnable() {
        messagesConfig = new MessagesConfig(this);

        PluginDescriptionFile pdfFile = getDescription();
        Bukkit.getConsoleSender().sendMessage("MallowCore loading version " + pdfFile.getVersion());
        getLogger().info("MallowCore enabling!");

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        String[] folders = { "PlayerData", "Messages" };
        for (String folderName : folders) {
            File dir = new File(getDataFolder(), folderName);
            if (!dir.exists()) {
                dir.mkdirs();
                getLogger().info("Created directory: " + dir.getPath());
            } else {
                getLogger().info("Directory already exists " + dir.getPath());
            }
        }
        // Listeners
        getServer().getPluginManager().registerEvents(new JoinLeave(this), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Commands
        getCommand("rpg").setExecutor(new MUCommand(this));
//        new Feed(this);
//        new Flight(this);
        getLogger().info("MallowCore enabled!");
        THIS = this;
    }

    public MessagesConfig getMessagesConfig() {
        return messagesConfig;
    }


    @Override
    public void onDisable() {
        getLogger().info("MallowCore disabling!");
        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
        getLogger().info("MallowCore disabled!");
    }

    public static MallowCore getInstance() {
        return getPlugin(MallowCore.class);
    }
}