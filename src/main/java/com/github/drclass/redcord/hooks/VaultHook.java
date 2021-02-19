package com.github.drclass.redcord.hooks;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.github.drclass.redcord.RedCord;

public class VaultHook {
	public static String getPrimaryGroup(Player player) {
		try {
			net.milkbowl.vault.permission.Permission permissionProvider = (net.milkbowl.vault.permission.Permission) Bukkit
					.getServer().getServicesManager()
					.getRegistration(Class.forName("net.milkbowl.vault.permission.Permission")).getProvider();
			if (permissionProvider == null) {
				RedCord.logger.fine("Tried looking up group for player " + player.getName()
						+ " but failed to get the registered service provider for Vault");
				return "";
			}

			String primaryGroup = permissionProvider.getPrimaryGroup(player);
			if (!primaryGroup.equals("default")) {
				return primaryGroup;
			} else {
				RedCord.logger.fine("Tried looking up group for player " + player.getName()
						+ " but the given group was \"default\"");
				return "";
			}
		} catch (Exception e) {
			RedCord.logger.fine("Failed to look up group for player " + player.getName() + ": " + e.getMessage() + "\n"
					+ ExceptionUtils.getStackTrace(e));
			return "";
		}
	}

	public static String[] getPlayersGroups(OfflinePlayer player) {
		try {
			RegisteredServiceProvider<?> service = Bukkit.getServer().getServicesManager()
					.getRegistration(Class.forName("net.milkbowl.vault.permission.Permission"));
			if (service == null)
				return new String[] {};

			// ((net.milkbowl.vault.permission.Permission)
			// service.getProvider()).getPlayerGroups(worldName, OfflinePlayer)

			List<String> playerGroups = new ArrayList<>();
			Method getPlayerGroupsMethod = service.getProvider().getClass().getMethod("getPlayerGroups");
			for (World world : Bukkit.getWorlds())
				for (String group : (String[]) getPlayerGroupsMethod.invoke(service.getProvider(), world.getName(),
						player))
					if (!playerGroups.contains(group))
						playerGroups.add(group);
			for (String group : (String[]) getPlayerGroupsMethod.invoke(service.getProvider(), null, player))
				if (!playerGroups.contains(group))
					playerGroups.add(group);

			return playerGroups.toArray(new String[0]);
		} catch (Exception ignored) {
		}
		return new String[] {};
	}

	public static String[] getGroups() {
		try {
			RegisteredServiceProvider<?> service = Bukkit.getServer().getServicesManager()
					.getRegistration(Class.forName("net.milkbowl.vault.permission.Permission"));
			if (service == null)
				return new String[] {};

			Method getGroupsMethod = service.getProvider().getClass().getMethod("getGroups");
			return (String[]) getGroupsMethod.invoke(service.getProvider());
		} catch (Exception ignored) {
		}
		return new String[] {};
	}

	public Plugin getPlugin() {
		return getPlugin("Vault");
	}

	public static Plugin getPlugin(String pluginName) {
		for (Plugin plugin : Bukkit.getPluginManager().getPlugins())
			if (plugin.getName().toLowerCase().startsWith(pluginName.toLowerCase()))
				return plugin;
		return null;
	}
}
