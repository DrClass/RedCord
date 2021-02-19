package com.github.drclass.redcord.utils;

import net.dv8tion.jda.api.entities.Member;

public class DiscordRoleUtils {
	public static String getTopMostRole(Member member) {
		if (member.getRoles().size() == 0) {
			return "";
		}
		return member.getRoles().get(0).getName();
	}
}
