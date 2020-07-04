package com.nospher.rankup.manager;

import com.nospher.rankup.point.storage.loader.PointLoader;
import com.nospher.rankup.rank.storage.loader.RankLoader;

/**
 * @author oNospher
 **/
public class StartManager {

    public StartManager() {
        new PointLoader();
        new RankLoader();
    }
}
