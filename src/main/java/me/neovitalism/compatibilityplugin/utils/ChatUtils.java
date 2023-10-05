package me.neovitalism.compatibilityplugin.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtils {
    private static final String PREFIX = "&#696969[&#7e50c7C&#8355c9o&#875acbm&#8c5fccp&#9064cea&#9569d0t&#996ed2i&#9e73d3b&#a278d5i&#a77dd7l&#ab82d9i&#b087dbt&#b48cdcy&#b991deP&#bd96e0l&#c29be2u&#c6a0e3g&#cba5e5i&#cfaae7n&#696969]&f ";
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]){6}");
    private static final String PLACEHOLDER = "\udcba\udcba\udcba\udcba\udcba";
    public static String parseColour(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        while (matcher.find()) {
            final ChatColor hexColor = ChatColor.of(matcher.group().substring(1));
            final String before = message.substring(0, matcher.start());
            final String after = message.substring(matcher.end());
            message = before + hexColor + after;
            matcher = HEX_PATTERN.matcher(message);
        }
        message = insertNewLines(message);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String insertNewLines(String string) {
        return string.replace("\\\\n", PLACEHOLDER).replace("\\n", "\n").replace(PLACEHOLDER, "\\n");
    }

    public static void sendPrettyMessage(CommandSender sender, String message) {
        if(message != null && !message.isEmpty() && !message.isBlank()) {
            sender.sendMessage(parseColour(PREFIX + message));
        }
    }
}
