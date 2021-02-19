package com.github.drclass.redcord.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static char convertNumberStringToUnicode(String numbers) {
		return (char) Integer.valueOf(numbers).intValue();
	}
	
	public static String replaceAllCodesWithUnicode(String string) {
		Pattern regex = Pattern.compile("\\$\\d+");
		Matcher regexMatcher = regex.matcher(string);
		if (regexMatcher.find()) {
			char unicode = convertNumberStringToUnicode(regexMatcher.group().substring(1));
			string = string.substring(0, regexMatcher.start()) + unicode + string.substring(regexMatcher.end(), string.length());
			string = replaceAllCodesWithUnicode(string);
		}
		return string;
	}
}
