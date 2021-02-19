package com.github.drclass.redcord.utils;
import java.awt.Color;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColorRoundingUtil {
	public static final Map<String, int[]> LEGACY_COLORS = Stream
			.of(new AbstractMap.SimpleImmutableEntry<>("&0", new int[] { 0, 0, 0 }),
					new AbstractMap.SimpleImmutableEntry<>("&1", new int[] { 0, 0, 170 }),
					new AbstractMap.SimpleImmutableEntry<>("&2", new int[] { 0, 170, 0 }),
					new AbstractMap.SimpleImmutableEntry<>("&3", new int[] { 0, 170, 170 }),
					new AbstractMap.SimpleImmutableEntry<>("&4", new int[] { 170, 0, 0 }),
					new AbstractMap.SimpleImmutableEntry<>("&5", new int[] { 170, 0, 170 }),
					new AbstractMap.SimpleImmutableEntry<>("&6", new int[] { 255, 170, 0 }),
					new AbstractMap.SimpleImmutableEntry<>("&7", new int[] { 170, 170, 170 }),
					new AbstractMap.SimpleImmutableEntry<>("&8", new int[] { 85, 85, 85 }),
					new AbstractMap.SimpleImmutableEntry<>("&9", new int[] { 85, 85, 255 }),
					new AbstractMap.SimpleImmutableEntry<>("&a", new int[] { 85, 255, 85 }),
					new AbstractMap.SimpleImmutableEntry<>("&b", new int[] { 85, 255, 255 }),
					new AbstractMap.SimpleImmutableEntry<>("&c", new int[] { 255, 85, 85 }),
					new AbstractMap.SimpleImmutableEntry<>("&d", new int[] { 255, 85, 255 }),
					new AbstractMap.SimpleImmutableEntry<>("&e", new int[] { 255, 255, 85 }),
					new AbstractMap.SimpleImmutableEntry<>("&f", new int[] { 255, 255, 255 }))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	public static String findClosestColor(Color color) {
		int[] rgbColor = { color.getRed(), color.getGreen(), color.getBlue() };

		String bestColorMatch = "";
		double bestColorDistance = Double.MAX_VALUE;

		for (String key : LEGACY_COLORS.keySet()) {
			int[] hex = LEGACY_COLORS.get(key);
			double distance = Math.pow(Math.pow(hex[0] - rgbColor[0], 2) + Math.pow(hex[1] - rgbColor[1], 2)
					+ Math.pow(hex[2] - rgbColor[2], 2), .5);
			if (distance < bestColorDistance) {
				bestColorDistance = distance;
				bestColorMatch = key;
			}
		}
		return bestColorMatch;
	}
}