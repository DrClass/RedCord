package com.github.drclass.redcord.listeners.minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.drclass.redcord.hooks.VaultHook;
import com.github.drclass.redcord.message.Message;
import com.github.drclass.redcord.message.MessageQueue;
import com.github.drclass.redcord.message.MessageType;

public class LeaveEventListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
    public void onChatEvent(PlayerQuitEvent event) {
		MessageQueue.addToDiscordQueue(new Message(MessageType.LEAVE, VaultHook.getPrimaryGroup(event.getPlayer()), event.getPlayer().getName(), event.getPlayer().getName(), null, null));
	}
}
