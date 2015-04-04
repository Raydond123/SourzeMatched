package me.raydond123.sourzematched;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ArenaCreation implements CommandExecutor {

    SourzeMatched plugin;

    public ArenaCreation(SourzeMatched plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(args.length < 1) {

                player.sendMessage(plugin.prefix + "Available Commands:");
                player.sendMessage(ChatColor.AQUA + "/sm createarena [name]");
                player.sendMessage(ChatColor.DARK_PURPLE + "/sm setspawn [mode: 1v1, 2v2, 3v3] [arena] [spawn number]");

            } else if(args[0].equals("createarena")) {

                if(args.length == 1) {

                    String arena = args[1];

                    plugin.arenaYaml.set("pending-arenas." + arena, arena);
                    plugin.saveArenas();

                    player.sendMessage(plugin.prefix + "The arena, " + arena + ", has been created!");

                } else {

                    player.sendMessage(plugin.prefix + "Correct Usage: /sm createarena [name]");

                }

            } else if(args[0].equalsIgnoreCase("setspawn")) {

                if(args.length == 3) {

                    String mode = args[1];
                    String arena = args[2];
                    int number = Integer.valueOf(args[3]);

                    if(plugin.arenaYaml.get("pending-arena." + arena) == null) {

                        player.sendMessage(plugin.prefix + "That arena does not exist!");
                        return false;

                    }

                    plugin.arenaYaml.set("pending-arena." + arena, null);
                    plugin.saveArenas();

                    Location loc = player.getLocation();

                    plugin.arenaYaml.set("arenas." + arena + "." + mode + "." + number, loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ());
                    plugin.saveArenas();

                    player.sendMessage(plugin.prefix + "The spawn location," + number + ", for the arena, " + arena + ", in the mode of, " + mode + ", has been set!");

                } else {

                    player.sendMessage(plugin.prefix + "/sm setspawn [mode: 1v1, 2v2, 3v3] [arena] [spawn number]");

                }

            }

        } else {

            plugin.logger.info("[SourzeMatched] That command can only be used by a player!");

        }

        return false;

    }

}
