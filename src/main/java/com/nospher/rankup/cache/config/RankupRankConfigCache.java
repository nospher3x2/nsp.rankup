package com.nospher.rankup.cache.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.nospher.rankup.RankupPlugin;
import com.nospher.rankup.rank.data.Rank;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * @author oNospher
 **/
public class RankupRankConfigCache {

    private static final Cache<Integer, Rank> CACHE_BY_HIERARCHY = Caffeine
            .newBuilder()
            .build();

    public static Rank getByHierarchy(Integer hierarchy) {
        return RankupRankConfigCache.CACHE_BY_HIERARCHY.getIfPresent(hierarchy);
    }

    public static void fetchAll() {
        FileConfiguration configuration = RankupPlugin.getInstance().getConfig();
        configuration.getConfigurationSection("settings.ranks").getKeys(false).forEach(key -> {
            ConfigurationSection section = configuration.getConfigurationSection("settings.ranks." + key);

            Integer hierarchy = section.getInt("hierarchy");
            String name = section.getString("name");
            String prefix = section.getString("prefix");
            Double price = section.getDouble("price");
            Double points = section.getDouble("points");
            List<String> commands = section.getStringList("commands");

            Rank rank = new Rank(
                    name,
                    hierarchy,
                    prefix,
                    price,
                    points,
                    commands
            );

            RankupRankConfigCache.CACHE_BY_HIERARCHY.put(hierarchy, rank);
        });
    }
}
