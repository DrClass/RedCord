package com.github.drclass.redcord.config;

import java.util.Optional;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
	public static Optional<Config> loadConfig(JavaPlugin plugin) {
		if (!plugin.getDataFolder().exists()) {
            plugin.getLogger().info("Error: No config.yml found. Creating...");
            plugin.getDataFolder().mkdir();
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
            plugin.getLogger().info("config.yml was created successfully!");
            plugin.getLogger().info("Server restart is required for the plugin to load.");
            plugin.getLogger().info("Disabling RedCord...");
            return Optional.empty();
        } else {
        	Config config = new Config();
        	config.discord.botToken = plugin.getConfig().getString("discord.botToken");
        	config.discord.channelID = plugin.getConfig().getString("discord.channelID");
        	config.discord.serverID = plugin.getConfig().getString("discord.serverID");
        	config.discord.useRoleColor = plugin.getConfig().getBoolean("discord.useRoleColor");
        	config.discord.stripFormatting = plugin.getConfig().getBoolean("discord.stripMessageFormatting");
        	config.discord.chatFormatting = plugin.getConfig().getString("discord.chatFormat");
        	config.discord.joinFormatting = plugin.getConfig().getString("discord.joinFormat");
        	config.discord.leaveFormatting = plugin.getConfig().getString("discord.leaveFormat");
        	config.discord.postConnections = plugin.getConfig().getBoolean("discord.postConnections");
        	
        	config.minecraft.stripFormatting = plugin.getConfig().getBoolean("minecraft.stripMessageFormatting");
        	config.minecraft.maxMessageLength = plugin.getConfig().getInt("minecraft.messsageCharacterLimit");
        	config.minecraft.postRawMessages = plugin.getConfig().getBoolean("minecraft.postRawMessages");
        	config.minecraft.messageFormatting = plugin.getConfig().getString("minecraft.messageFormat");
        	
        	plugin.saveConfig();
        	plugin.getLogger().info("Config loaded successfully!");
        	return Optional.of(config);
        }
	}
}
