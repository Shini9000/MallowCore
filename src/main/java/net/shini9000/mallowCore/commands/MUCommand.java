package net.shini9000.mallowCore.commands;

import net.shini9000.mallowCore.MallowCore;
import net.shini9000.mallowCore.data.MessagesConfig;
import net.shini9000.mallowCore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MUCommand implements CommandExecutor {

    private final MallowCore plugin;
    private enum Subcommand { set, get, add, subtract; }

    public MUCommand(MallowCore plugin) {
        this.plugin = plugin;
    }

    private Subcommand parseSub(String input) {
        try {
            return Subcommand.valueOf(input.toLowerCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        MessagesConfig mc = plugin.getMessagesConfig();


            sender.sendMessage(Utils.colorize("&6Use /rpg <subcommand>"));
            sender.sendMessage(Utils.colorize(mc.getMessage("Subcommands.Title")));

        if (args.length == 0) {
            sender.sendMessage(Utils.colorize("&6Use /rpg <subcommand>"));
            sender.sendMessage(Utils.colorize(mc.getMessage("Subcommands.Title")));

            for (String s : mc.getStringList("Subcommands.Commands")) {
                sender.sendMessage(Utils.colorize("- " + s));
            }

            return true;
        }

        // /rpg reload
        if (args[0].equalsIgnoreCase("reload")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(mc.getMessage("Commands.Reload"));
                mc.reload();
            } else {
                Player p = (Player) sender;
                if (p.hasPermission("murpg.command.reload") || p.hasPermission("murpg.*")) {
                    mc.reload();
                    p.sendMessage(Utils.colorize(mc.getMessage("Commands.Reload")));
                } else {
                    p.sendMessage(Utils.colorize(mc.getMessage("Commands.NoPerm")));
                }
            }
            return true;
        }

        // /rpg eco
        if (args[0].equalsIgnoreCase("eco")) {
            // eco logic command format /rpg eco <user> <add:remove:give:set> <amount> < target if applicable >
            sender.sendMessage(Utils.colorize(mc.getMessage("Commands.Help.Eco")));
            return true;
        }

        // /rpg stats
        if (args[0].equalsIgnoreCase("stats")) {

            if (args.length == 1) {
                // /rpg stats
                //sender.sendMessage(args[0]);
                sender.sendMessage(Utils.colorize(mc.getMessage("Commands.Help.Stats")));
                //showStats((Player) sender);
                return true;
            }

            if (args.length == 2) {
                Subcommand sub = parseSub(args[1]);
                switch (sub) {
                    case set:            // /rpg stats set <attribute> <value>
                        if (args.length < 4) {
                            sender.sendMessage("Usage: /rpg stats set <attribute> <value>");
                            return true;
                        }

//                        MUAttribute attr = MUAttribute.fromString(args[2]);
//                        if (attr == null) {
//                            sender.sendMessage("Unknown attribute: " + args[2]);
//                            return true;
//                        }

                        int value;
                        try {
                            value = Integer.parseInt(args[3]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage("Value must be a number.");
                            return true;
                        }

                        // Now you have:
                        // attr  → the attribute enum
                        // value → the integer value

                        //sender.sendMessage("Setting " + attr + " to " + value);
                        // TODO: apply stat to player

                        break;

                    case get:
                        // handle get
                        break;

                    case add:
                        // handle add
                        break;

                    case subtract:
                        // handle subtract
                        break;
                }

                return true;
            }

//        } else {
//            String userName = args[1];
//            if (Bukkit.getOnlinePlayers().contains(userName)) {
//                sender.sendMessage(userName);
//            }

            return true;
        }


        // /rpg clan
        if (args[0].equalsIgnoreCase("clan")) {
            if (args.length == 1) {
                // /rpg clan
                sender.sendMessage(Utils.colorize(mc.getMessage("Commands.Help.Clan")));
                //showStats((Player) sender);
                return true;
            }

            return true;
        }

        // /rpg somethingElse
        if (args[0].equalsIgnoreCase("somethingElse")) {
            // logic
            return true;
        }

        sender.sendMessage("Unknown subcommand.");
        return true;
    }


}