package com.nospher.rankup.cache.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.nospher.rankup.RankupPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.material.MaterialData;

/**
 * @author oNospher
 **/
public class RankupPointConfigCache {

    private static final Cache<MaterialData, Double> CACHE_BY_ITEM_STACK = Caffeine
            .newBuilder()
            .build();

    public static Double get(MaterialData materialData) {
        return RankupPointConfigCache.CACHE_BY_ITEM_STACK.getIfPresent(materialData);
    }

    public static void fetchAll() {
        FileConfiguration configuration = RankupPlugin.getInstance().getConfig();

        configuration.getConfigurationSection("settings.points").getKeys(false).forEach(key -> {
            ConfigurationSection section = RankupPlugin.getInstance().getConfig().getConfigurationSection(
                    "settings.points." + key
            );

            String preBlock = section.getString("id");
            Double value = section.getDouble("value");
            int id,data;
            if (preBlock.contains(":")) {
                String[] split = preBlock.split(":");

                id = Integer.parseInt(split[0]);
                data = Integer.parseInt(split[1]);
            } else {
                id = Integer.parseInt(preBlock);
                data = 0;
            }

            MaterialData materialData = new MaterialData(id, (byte) data);
            RankupPointConfigCache.CACHE_BY_ITEM_STACK.put(materialData, value);
        });

    }

}
