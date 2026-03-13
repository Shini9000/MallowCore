package net.shini9000.mallowCore.utils;


import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("&#([a-fA-F0-9]{6})");

    public static String colorize(String text) {
        if (text == null) {
            return "";
        }

        Matcher matcher = HEX_COLOR_PATTERN.matcher(text);
        while (matcher.find()) {
            String hexColor = matcher.group(1); // Extract hex code (e.g., "00FF00")
            StringBuilder chatColor = new StringBuilder("§x"); // Minecraft's hex color prefix

            // Convert each character of the hex code into the Minecraft §R format
            for (char c : hexColor.toCharArray()) {
                chatColor.append('§').append(c);
            }
            text = text.replace("&#" + hexColor, chatColor.toString());
        }

        // Handle legacy color codes (e.g., &c -> §c)
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String stripColor(String text) { // Strip a string of all color to it
        if (text == null) { // string is "" then do nothing
            return "";
        }

        // Remove legacy color codes and hex codes
        text = ChatColor.stripColor(text); // found string to strip
        return text.replaceAll(HEX_COLOR_PATTERN.pattern(), "");
    }
}