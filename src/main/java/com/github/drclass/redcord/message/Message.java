package com.github.drclass.redcord.message;

import java.awt.Color;

import com.github.drclass.redcord.RedCord;
import com.github.drclass.redcord.utils.ColorRoundingUtil;
import com.github.drclass.redcord.utils.StringUtils;

public class Message {
	private MessageType messageType;
	private String message;
	private String username;
	private String nickname;
	private String rank;
	private Color senderColorCode;

	public Message(MessageType messageType, String rank, String username, String nickname, String message, Color senderColorCode) {
		this.messageType = messageType;
		this.message = message;
		this.username = username;
		this.nickname = nickname;
		this.rank = rank;
		this.senderColorCode = senderColorCode;
		if (this.message == null) {
			this.message = "";
		}
		if (this.username == null) {
			this.username = "";
		}
		if (this.nickname == null) {
			this.nickname = "";
		}
		if (this.rank == null) {
			this.rank = "";
		}
		if (this.senderColorCode == null) {
			this.senderColorCode = Color.WHITE;
		}
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public String getMessage() {
		return message;
	}

	public String getUsername() {
		return username;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getRank() {
		return rank;
	}

	public Color getSenderColorCode() {
		return senderColorCode;
	}

	public String formatForDiscord() {
		switch (messageType) {
			case CHAT:
				return buildStringForDiscord(RedCord.config.discord.chatFormatting);
			case JOIN:
				return buildStringForDiscord(RedCord.config.discord.joinFormatting);
			case LEAVE:
				return buildStringForDiscord(RedCord.config.discord.leaveFormatting);
			default:
				RedCord.getPlugin(RedCord.class).getLogger().warning("ERROR: UNKNOWN MESSAGE TYPE DETECTED");
				return null;
		}
	}
	
	public String formatForMinecraft() {
		return buildStringForMC(RedCord.config.minecraft.messageFormatting);
	}
	
	private String buildStringForDiscord(String template) {
		return template.replaceAll("<USERNAME>", username).replaceAll("<NICKNAME>", nickname).replaceAll("<MESSAGE>", message).replaceAll("<RANK>", rank);
	}
	
	private String buildStringForMC(String template) {
		String hex = ColorRoundingUtil.findClosestColor(senderColorCode);
		String formattedTemplate = StringUtils.replaceAllCodesWithUnicode(template);
		return formattedTemplate.replaceAll("<USERNAME>", username).replaceAll("<NICKNAME>", nickname).replaceAll("<MESSAGE>", message).replaceAll("<RANK>", rank).replaceAll("<COLOR>", hex);
	}
}
