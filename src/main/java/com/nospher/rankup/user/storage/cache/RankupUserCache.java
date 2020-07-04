package com.nospher.rankup.user.storage.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.nospher.rankup.RankupProvider;
import com.nospher.rankup.user.entity.RankupUser;
import com.nospher.rankup.util.storage.cache.NspCaffeineCache;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author oNospher
 **/
public class RankupUserCache extends NspCaffeineCache<Integer, RankupUser> {

    public RankupUserCache() {
        super(Caffeine
                .newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build()
        );
    }

    public void insert(RankupUser user) {
        this.insert(user.getId(), user);
    }

    public RankupUser fetch(Integer id) {
        RankupUser rankupUser = super.fetch(rankupUser1 -> rankupUser1.getId().equals(id));
        if(rankupUser == null) return RankupProvider.Repository.RANKUP_USER_REPOSITORY.fetch("id", id);
        return rankupUser;
    }

    public RankupUser fetch(UUID uuid) {
        RankupUser rankupUser = super.fetch(rankupUser1 -> rankupUser1.getUniqueId().equals(uuid));
        if(rankupUser == null) return RankupProvider.Repository.RANKUP_USER_REPOSITORY.fetch("unique_id", uuid);
        return rankupUser;
    }
}
