package com.nospher.rankup.point.listener;

import com.nospher.rankup.RankupProvider;
import com.nospher.rankup.point.entity.Point;
import com.nospher.rankup.user.entity.RankupUser;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.MaterialData;

/**
 * @author oNospher
 **/
public class GivePointOnBreakBlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        MaterialData materialData = new MaterialData(
                block.getTypeId(),
                block.getData()
        );

        Point point = RankupProvider.Cache.POINT_CACHE.fetch(point1 -> point1.getMaterialData().equals(materialData));
        if(point == null) return;

        Player player = event.getPlayer();
        RankupUser rankupUser = RankupProvider.Cache.RANKUP_USER_CACHE.fetch(player.getUniqueId());
        rankupUser.addPoints(point.getValue());
    }
}
