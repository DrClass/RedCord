package com.github.drclass.redcord;

import javax.security.auth.login.LoginException;

import com.github.drclass.redcord.listeners.discord.ChatListener;
import com.github.drclass.redcord.message.Message;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotHandler extends ListenerAdapter {
	private JDA jda;
	
	public void initDiscord() throws LoginException {
		jda = JDABuilder.createDefault(RedCord.config.discord.botToken).addEventListeners(new ChatListener()).build();
	}
	
	public void postMessage(Message message) {
		jda.getGuildById(RedCord.config.discord.serverID).getTextChannelById(RedCord.config.discord.channelID)
				.sendMessage(message.formatForDiscord()).queue();
	}
}
