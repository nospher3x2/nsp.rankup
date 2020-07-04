package com.nospher.rankup;

import com.nospher.rankup.point.storage.cache.PointCache;
import com.nospher.rankup.point.storage.loader.PointLoader;
import com.nospher.rankup.rank.storage.cache.RankCache;
import com.nospher.rankup.rank.storage.loader.RankLoader;
import com.nospher.rankup.user.entity.RankupUser;
import com.nospher.rankup.user.storage.cache.RankupUserCache;
import com.nospher.rankup.user.storage.repository.RankupUserRepository;

/**
 * @author oNospher
 **/
public class RankupProvider {

    public static class Message {

    }

    public static class Repository {
        public static final RankupUserRepository<RankupUser> RANKUP_USER_REPOSITORY = new RankupUserRepository<>();
    }

    public static class Cache {
        public static final PointCache POINT_CACHE = new PointCache();
        public static final RankCache RANK_CACHE = new RankCache();
        public static final RankupUserCache RANKUP_USER_CACHE = new RankupUserCache();
    }
}
