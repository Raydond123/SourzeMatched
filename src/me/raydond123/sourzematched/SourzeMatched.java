package me.raydond123.sourzematched;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class SourzeMatched extends JavaPlugin {

    Logger logger = Logger.getLogger("Minecraft");
    GUIManager guiManager;
    ArenaCreation arenaCreation;
    public QueueManager queueManager;

    public String prefix = ChatColor.DARK_PURPLE + "[" + ChatColor.AQUA + "SourzeMatched" + ChatColor.DARK_PURPLE + "] " + ChatColor.AQUA;

    public void onEnable() {

        guiManager = new GUIManager(this);
        arenaCreation = new ArenaCreation(this);
        queueManager = new QueueManager(this);

        Bukkit.getPluginManager().registerEvents(guiManager, this);

        getCommand("sourzematched").setExecutor(arenaCreation);
        getCommand("sm").setExecutor(arenaCreation);

        guiManager.setupGUI();

        saveArenas();

        logger.info("[SourzeMatched] The plugin has been enabled!");

    }

    public void onDisable() {

        logger.info("[SourzeMatched] The plugin has been disabled!");

    }

    File arenaFile = new File("/plugins/SourzeMatched/arenas.yml");
    public YamlConfiguration arenaYaml = YamlConfiguration.loadConfiguration(arenaFile);
    public void saveArenas() {

        try {

            arenaYaml.save(arenaFile);

        } catch(Exception e) {

            logger.info("[SourzeMatched] There was an error whilst saving arenas.yml!");
            e.printStackTrace();

        }

    }

}
