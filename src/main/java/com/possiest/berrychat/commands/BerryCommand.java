package com.possiest.berrychat.commands;

import com.possiest.berrychat.BerryChat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BerryCommand implements CommandExecutor, TabCompleter{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            return false;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            if (sender.hasPermission("berry.clearchat")) {
                for (int i = 0; i < 100; i++) {
                    Bukkit.broadcastMessage(" ");
                }
                sender.sendMessage(ChatColor.of("#D60247") + "Chat was cleared by " + sender.getName());
            }else {
                sender.sendMessage(ChatColor.of("#FF2626") + "You don't have permission to use this command!");
            }return true;

        } else if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("berry.reload")) {
                sender.sendMessage(ChatColor.of("#D0D0D0") + "Reloading...");
                BerryChat.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.of("#D60247") + "Berry was Reloaded!");
            }else {
                sender.sendMessage(ChatColor.of("#FF2626") + "You don't have permission to use this command!");
            }return true;
        }
        else if (args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission("berry.help")) {
                sender.sendMessage(ChatColor.of("#D60247") + "Welcome to Berry Help!");
                sender.sendMessage(ChatColor.of("#D0D0D0") + "Commands:");
                sender.sendMessage(ChatColor.of("#D60247") + "/bc clear - Clears the chat");
                sender.sendMessage(ChatColor.of("#D60247") + "/bc reload - Reloads the plugin");
                sender.sendMessage(ChatColor.of("#D60247") + "/bc help - Shows this message");
                sender.sendMessage(ChatColor.of("#D0D0D0") + "Thanks for using Berry!");
            } else {
                sender.sendMessage(ChatColor.of("#FF2626") + "You don't have permission to use this command!");
            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("clear");
            completions.add("reload");
            completions.add("help");
        }

        return completions;
    }
}
