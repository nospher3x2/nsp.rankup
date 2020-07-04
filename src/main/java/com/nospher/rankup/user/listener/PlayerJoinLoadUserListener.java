package com.nospher.rankup.user.listener;

import com.nospher.rankup.RankupProvider;
import com.nospher.rankup.user.entity.RankupUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author oNospher
 **/
public class PlayerJoinLoadUserListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RankupUser user = RankupProvider.Cache.RANKUP_USER_CACHE.fetch(player.getUniqueId());
        if(user.getUniqueId() == null) user.setUniqueId(player.getUniqueId());
    }
}
