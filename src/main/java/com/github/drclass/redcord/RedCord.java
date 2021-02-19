package com.github.drclass.redcord;

import java.util.Optional;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.drclass.redcord.config.Config;
import com.github.drclass.redcord.config.ConfigManager;
import com.github.drclass.redcord.listeners.minecraft.ChatListener;
import com.github.drclass.redcord.listeners.minecraft.JoinEventListener;
import com.github.drclass.redcord.listeners.minecraft.LeaveEventListener;
import com.github.drclass.redcord.message.Message;
import com.github.drclass.redcord.message.MessageQueue;

import net.md_5.bungee.api.ChatColor;

public class RedCord extends JavaPlugin {

	public static Config config;
	public static BotHandler botHandler;
	public static MessageQueue messageQueue;
	
	public static Logger logger;

	@Override
	public void onEnable() {
		getLogger().info("Loading config...");
		Optional<Config> conf = ConfigManager.loadConfig(this);
		if (conf.isPresent()) {
			config = conf.get();
		} else {
			setEnabled(false);
			return;
		}
		
		logger = getPlugin(RedCord.class).getLogger();
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginManager().registerEvents(new JoinEventListener(), this);
		getServer().getPluginManager().registerEvents(new LeaveEventListener(), this);
		
		botHandler = new BotHandler();
		try {
			botHandler.initDiscord();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		messageQueue = new MessageQueue();
		new Thread(messageQueue).start();
	}
	
	@Override
    public void onDisable() {
		
	}
	
	public static void postMessage(Message message) {
		getPlugin(RedCord.class).getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.formatForMinecraft()));
	}
}
