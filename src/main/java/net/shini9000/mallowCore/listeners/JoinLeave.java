package net.shini9000.mallowCore.listeners;

import net.shini9000.mallowCore.MallowCore;
import net.shini9000.mallowCore.data.MessagesConfig;
import net.shini9000.mallowCore.data.PlayerConfig;
import net.shini9000.mallowCore.utils.Utils;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {
    private final MallowCore plugin;
    public JoinLeave(MallowCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        MessagesConfig mc = plugin.getMessagesConfig();
        PlayerConfig pc = new PlayerConfig(plugin, p.getUniqueId());
        if (!(p.hasPlayedBefore())) {
            pc.createFile(p.getName(), p.getUniqueId());
            e.setJoinMessage(Utils.colorize(
                    mc.getMessage("Listeners.FirstJoin").
                            replace("%player%", p.getName())));
        } else {

            int joinAmount = p.getStatistic(Statistic.LEAVE_GAME);
            switch (joinAmount) {
                case 999:
                    e.setJoinMessage(Utils.colorize("&6%player% has joined 1000 times! They are true fans").
                            replace("%player%", p.getName()));
                    break;
                case 499:
                    e.setJoinMessage(Utils.colorize("&6%player% has joined 500 times! Let's celebrate").
                            replace("%player%", p.getName()));
                    break;
                case 249:
                    e.setJoinMessage(Utils.colorize("&6%player% has joined 250 times! Serious playtime").
                            replace("%player%", p.getName()));
                    break;
                case 99:
                    e.setJoinMessage(Utils.colorize("&6%player% has joined 100 times!").
                            replace("%player%", p.getName()));
                    break;
                case 49:
                    e.setJoinMessage(Utils.colorize("&6%player% has joined 50 times!").
                            replace("%player%", p.getName()));
                    break;
                case 24:
                    e.setJoinMessage(Utils.colorize("&6%player% has joined 25 times!").
                            replace("%player%", p.getName()));
                    break;
                case 9:
                    e.setJoinMessage(Utils.colorize("&6%player% has joined 10 times!").
                            replace("%player%", p.getName()));
                    break;
                default:
                    e.setJoinMessage(Utils.colorize(pc.getJoinMessage().
                            replace("%player%", p.getName())));
            }

        }

    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        MessagesConfig mc = plugin.getMessagesConfig();
        if (!(p.hasPlayedBefore())) {
            PlayerConfig pc = new PlayerConfig(plugin, p.getUniqueId());
            pc.createFile(p.getName(), p.getUniqueId());
            e.setQuitMessage(Utils.colorize(
                    mc.getMessage("Listeners.Leave").
                            replace("%player%", p.getName())));
        } else {
            PlayerConfig pc = new PlayerConfig(plugin, p.getUniqueId());
            e.setQuitMessage(Utils.colorize(
                    pc.getJoinMessage().
                            replace("%player%", p.getName())));

        }
    }
}
