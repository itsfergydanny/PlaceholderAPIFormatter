package com.sysadamant.placeholderapiformatter;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public final class PlaceholderAPIFormatter extends JavaPlugin {
    private static final Map<String, DecimalFormat> formatters = new HashMap<>();

    @Override
    public void onEnable() {
        int count = 0;
        for (String line : getConfig().getStringList("formats")) {
            String[] args = line.split(":");
            if (args.length != 2) {
                getLogger().warning("Formatter \"" + line + "\" is not configured properly, it will be skipped!");
                continue;
            }

            try {
                formatters.put(args[0].replace(" ", "_"), new DecimalFormat(args[1]));
                count++;
            } catch (Exception exception) {
                getLogger().warning("Formatter \"" + line + "\" contains an invalid decimal format, it will be skipped!");
            }
        }
        getLogger().info("Successfully loaded " + count + " custom formatters.");
        new FormattedPlaceholder().register();
    }

    public static class FormattedPlaceholder extends PlaceholderExpansion {
        @Override
        public @NotNull String getAuthor() {
            return "fergydanny";
        }

        @Override
        public @NotNull String getIdentifier() {
            return "papiformatter";
        }

        @Override
        public @NotNull String getVersion() {
            return "1.0.0";
        }

        @Override
        public boolean persist() {
            return true;
        }

        @Override
        public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
            for (String key : formatters.keySet()) {
                if (params.startsWith(key)) {
                    String[] args = params.split(key + "_");
                    if (args.length < 2) {
                        return "invalid params";
                    }

                    String placeholder = PlaceholderAPI.setPlaceholders(player, "%" + args[1] + "%");

                    try {
                        return formatters.get(key).format(Double.parseDouble(placeholder));
                    } catch (NumberFormatException ignore) {
                        return "invalid number provided";
                    }
                }
            }
            return null;
        }
    }
}


