package com.nospher.rankup;

import com.avaje.ebean.cache.ServerCacheManager;
import com.nospher.rankup.manager.StartManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author oNospher
 **/
public class RankupPlugin extends JavaPlugin {

    @Getter
    private static RankupPlugin instance;

    private Boolean ACTION_BAR, BROADCAST, TITLE;

    @Override
    public void onEnable() {
        instance = this;
        new StartManager();
    }


}
