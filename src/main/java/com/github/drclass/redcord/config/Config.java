package com.github.drclass.redcord.config;

/**
 * This is a really shitty way of doing configs but I don't care
 */
public class Config {
	public Discord discord = new Discord();
	public Minecraft minecraft = new Minecraft();
	
	public class Discord {
		public String botToken = "";
		public String serverID = "";
		public String channelID = "";
		public boolean useRoleColor = true;
		public boolean stripFormatting = true;
		public String chatFormatting = "";
		public String joinFormatting = "";
		public String leaveFormatting = "";
		public boolean postConnections = false;
	}
	
	public class Minecraft {
		public boolean stripFormatting = true;
		public int maxMessageLength = 500;
		public boolean postRawMessages = true;
		public String messageFormatting = "";
	}
}
