package com.nospher.rankup.manager;

import com.nospher.rankup.cache.config.RankupPointConfigCache;
import com.nospher.rankup.cache.config.RankupRankConfigCache;

/**
 * @author oNospher
 **/
public class StartManager {

    public StartManager() {
        new CacheManager();
    }
}

class CacheManager {
    public CacheManager() {
        RankupPointConfigCache.fetchAll();
        RankupRankConfigCache.fetchAll();
    }
}
