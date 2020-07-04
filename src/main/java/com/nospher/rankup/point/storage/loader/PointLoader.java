package com.nospher.rankup.point.storage.loader;

import com.nospher.rankup.RankupPlugin;
import com.nospher.rankup.RankupProvider;
import com.nospher.rankup.point.entity.Point;
import com.nospher.rankup.point.storage.cache.PointCache;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.material.MaterialData;

/**
 * @author oNospher
 **/
public class PointLoader {

    public PointLoader() {
        FileConfiguration configuration = RankupPlugin.getInstance().getConfig();

        configuration.getConfigurationSection("settings.points").getKeys(false).forEach(key -> {
            ConfigurationSection section = RankupPlugin.getInstance().getConfig().getConfigurationSection(
                    "settings.points." + key
            );

            String preBlock = section.getString("id");
            Double value = section.getDouble("value");
            int id, data;
            if (preBlock.contains(":")) {
                String[] split = preBlock.split(":");

                id = Integer.parseInt(split[0]);
                data = Integer.parseInt(split[1]);
            } else {
                id = Integer.parseInt(preBlock);
                data = 0;
            }

            MaterialData materialData = new MaterialData(id, (byte) data);
            Point point = new Point(
                    materialData,
                    value
            );

            RankupProvider.Cache.POINT_CACHE.insert(point);
        });
    }
}
