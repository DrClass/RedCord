package com.github.drclass.redcord.listeners.minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.drclass.redcord.hooks.VaultHook;
import com.github.drclass.redcord.message.Message;
import com.github.drclass.redcord.message.MessageQueue;
import com.github.drclass.redcord.message.MessageType;

public class ChatListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
    public void onChatEvent(AsyncPlayerChatEvent event) {
		MessageQueue.addToDiscordQueue(new Message(MessageType.CHAT, VaultHook.getPrimaryGroup(event.getPlayer()), event.getPlayer().getName(), event.getPlayer().getName(), event.getMessage(), null));
	}
}
