package com.possiest.berrychat;

import com.possiest.berrychat.commands.BerryCommand;
import com.possiest.berrychat.listeners.ChatSystem;
import com.possiest.berrychat.listeners.JoinLeaveMessages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class BerryChat extends JavaPlugin {

    public static BerryChat instance;
    private final YamlConfiguration conf = new YamlConfiguration();
    @Override
    public void onEnable() {
        instance = this;
        startup();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("berry").setExecutor(new BerryCommand());
    }

    private void configuration(){
        File co = new File(getDataFolder(), "config.yml");
        if(!co.exists()) saveResource("config.yml", false);

        Bukkit.getPluginManager().registerEvents(new ChatSystem(), this);
        Bukkit.getPluginManager().registerEvents(new JoinLeaveMessages(), this);


        try{
            this.conf.load(co);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void startup(){
        registerCommands();
        configuration();
        startMessage();
    }

    private void startMessage(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[]=================[" + ChatColor.of("#D60247") + ChatColor.BOLD + "BerryChat" + ChatColor.GRAY + "]=================[]");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "|" + ChatColor.of("#D0D0D0") + " Made by:" + ChatColor.of("#D60247") + " MathiasClari (CEO of Possiest)");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "|" + ChatColor.of("#D0D0D0") + " My GitHub:" + ChatColor.of("#D60247") + "https://github.com/MathiasClari");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[]=============================================[]");
    }

    public static BerryChat getInstance() {
        return instance;
    }
}
