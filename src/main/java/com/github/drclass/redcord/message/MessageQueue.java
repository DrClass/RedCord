package com.github.drclass.redcord.message;

import java.util.concurrent.LinkedBlockingQueue;

import com.github.drclass.redcord.RedCord;

public class MessageQueue implements Runnable {
	private static volatile LinkedBlockingQueue<Message> mcQueue = new LinkedBlockingQueue<Message>();
	private static volatile LinkedBlockingQueue<Message> discordQueue = new LinkedBlockingQueue<Message>();
	private static boolean shutdown = false;
	
	public static void addToMinecraftQueue(Message message) {
		mcQueue.offer(message);
	}
	
	public static void addToDiscordQueue(Message message) {
		discordQueue.offer(message);
	}

	public static void shutdownBot() {
		shutdown = true;
	}

	@Override
	public void run() {
		while (!shutdown) {
			if (mcQueue.peek() != null) {
				RedCord.postMessage(mcQueue.poll());
			}
			if (discordQueue.peek() != null) {
				RedCord.botHandler.postMessage(discordQueue.poll());
			}
		}
	}
}
