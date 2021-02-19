package com.github.drclass.redcord.listeners.discord;

import com.github.drclass.redcord.RedCord;
import com.github.drclass.redcord.message.Message;
import com.github.drclass.redcord.message.MessageQueue;
import com.github.drclass.redcord.message.MessageType;
import com.github.drclass.redcord.utils.DiscordRoleUtils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot() || event.isWebhookMessage()) {
			return;
		}
		if (event.getGuild().getId().equals(RedCord.config.discord.serverID)
				&& event.getChannel().getId().equals(RedCord.config.discord.channelID)) {
			String messageText = event.getMessage().getContentDisplay();
			if (messageText.length() > RedCord.config.minecraft.maxMessageLength && RedCord.config.minecraft.maxMessageLength != 0) {
				messageText = messageText.substring(0, RedCord.config.minecraft.maxMessageLength) + "...";
			}
			MessageQueue.addToMinecraftQueue(new Message(MessageType.CHAT,
					DiscordRoleUtils.getTopMostRole(event.getMember()), event.getAuthor().getName(),
					event.getMember().getEffectiveName(), messageText, event.getMember().getColor()));
		}
	}
}
