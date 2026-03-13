package net.shini9000.mallowCore.data;

import net.shini9000.mallowCore.MallowCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfig {
    private final MallowCore plugin;
    private final File file;
    private FileConfiguration config;

    public PlayerConfig(MallowCore plugin, UUID uuid) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "PlayerData/" + uuid.toString() + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void createFile(String playerName, UUID playerUUID) {
        Bukkit.getConsoleSender().sendMessage("Player data save request " + playerName + " -- " + playerUUID);
        MessagesConfig mc = plugin.getMessagesConfig();
        try {
            file.createNewFile();
            config.set("Username", playerName);
            config.set("Nickname", "");
            config.set("UUID", playerUUID.toString());
            config.set("JoinMessage", mc.getMessage("Listeners.Join"));
            config.set("LeaveMessage", mc.getMessage("Listeners.Leave"));
            config.set("Class", "");
            config.set("Currency.Platinum", 0);
            config.set("Currency.Gold", 0);
            config.set("Currency.Silver", 0);
            config.set("Currency.Copper", 100);
            config.set("Currency.Gems", 0);
            config.set("#STAT", "");
            config.set("value", "");
            save();
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("Player data failed  to save " + playerName + " -- " + playerUUID);
        }
        Bukkit.getConsoleSender().sendMessage("Player data saved " + playerName + " -- " + playerUUID);
    }


    public String getJoinMessage() {
        return config.getString("JoinMessage").replace("%player%", config.getString("Username"));
    }

    public String getLeaveMessage() {
        return config.getString("LeaveMessage").replace("%player%", config.getString("Username"));
    }

//    public String getCurrency(){
//    //need to make it a string return not a return statement
//        return config.getString("Currency.Platinum","");
//        return config.getString("Currency.Gold","");
//        return config.getString("Currency.Silver","");
//        return config.getString("Currency.Copper","");
//        return config.getString("Currency.Gems","");
//    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}