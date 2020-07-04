package com.nospher.rankup.rank.storage.cache;

import com.github.benmanes.caffeine.cache.Caffeine;

import com.nospher.rankup.rank.entity.Rank;
import com.nospher.rankup.util.storage.cache.NspCaffeineCache;


/**
 * @author oNospher
 **/
public class RankCache extends NspCaffeineCache<Integer, Rank> {

    public RankCache() {
        super(Caffeine
                .newBuilder()
                .build()
        );
    }

}
