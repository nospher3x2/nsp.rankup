package com.nospher.rankup;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author oNospher
 **/
public class RankupPlugin extends JavaPlugin {

    @Getter
    private static RankupPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
    }
}
