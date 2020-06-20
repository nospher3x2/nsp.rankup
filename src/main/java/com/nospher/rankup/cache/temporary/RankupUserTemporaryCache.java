package com.nospher.rankup.cache.temporary;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.nospher.rankup.user.data.RankupUser;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author oNospher
 **/
public class RankupUserTemporaryCache {

    private static final Cache<Integer, RankupUser> CACHE_BY_ID = Caffeine
            .newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .build();

    public static void insert(RankupUser user) {
        RankupUserTemporaryCache.CACHE_BY_ID.put(user.getId(), user);
    }

    public static RankupUser getByID(int id) {
        return RankupUserTemporaryCache.CACHE_BY_ID.getIfPresent(id);
    }

    public static RankupUser getByUUID(UUID uniqueId) {
        return RankupUserTemporaryCache.CACHE_BY_ID.asMap().values().stream()
                .filter(Objects::nonNull)
                .filter(rankupUser -> rankupUser.getUniqueId().equals(uniqueId))
                .findFirst()
                .orElse(null);
    }

    public static Collection<RankupUser> getAll() {
        return RankupUserTemporaryCache.CACHE_BY_ID.asMap().values();
    }
}
