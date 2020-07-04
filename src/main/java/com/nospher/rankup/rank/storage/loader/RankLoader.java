package com.nospher.rankup.rank.storage.loader;

import com.nospher.rankup.RankupPlugin;
import com.nospher.rankup.RankupProvider;
import com.nospher.rankup.rank.entity.Rank;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * @author oNospher
 **/
public class RankLoader {
    public RankLoader() {
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

            RankupProvider.Cache.RANK_CACHE.insert(hierarchy, rank);
        });
    }
}
