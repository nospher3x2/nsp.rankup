package com.nospher.rankup.manager;

import com.nospher.rankup.cache.config.RankupPointConfigCache;
import com.nospher.rankup.cache.config.RankupRankConfigCache;

/**
 * @author oNospher
 **/
public class StartManager {

    public StartManager() {
        RankupPointConfigCache.fetchAll();
        RankupRankConfigCache.fetchAll();

    }
}
