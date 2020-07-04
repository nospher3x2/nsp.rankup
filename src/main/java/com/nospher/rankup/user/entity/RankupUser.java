package com.nospher.rankup.user.entity;

import com.google.common.collect.Lists;
import com.nospher.rankup.RankupProvider;
import com.nospher.rankup.database.mysql.data.Parameters;
import com.nospher.rankup.rank.storage.cache.RankCache;
import com.nospher.rankup.database.table.TableRow;
import com.nospher.rankup.rank.entity.Rank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @author oNospher
 **/
@AllArgsConstructor
@Getter @Setter
public class RankupUser {

    private final Integer id;

    private UUID uniqueId;
    private Rank rank;
    private Double points;

    public void addPoints(Double points) {
        this.points += points;
        List<Parameters<String, Object>> parametersList = Lists.newArrayList();
        parametersList.add(new Parameters<>("points", points));
        try {
            RankupProvider.Repository.RANKUP_USER_REPOSITORY.update(parametersList, this);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Player getPlayer() {
        if(uniqueId == null) System.out.println("[ERROR] UUID player invalid");
        return Bukkit.getPlayer(this.uniqueId);
    }

    public static RankupUser newUser() {
        return new RankupUser(
                RankupProvider.Repository.RANKUP_USER_REPOSITORY.count(),
                null,
                RankupProvider.Cache.RANK_CACHE.fetch(rank1 -> rank1.getHierarchy().equals(0)),
                0.0
        );
    }

    public static RankupUser toUser(TableRow row) {
        Rank rank = RankupProvider.Cache.RANK_CACHE
                .fetch(rank1 -> rank1.getHierarchy().equals(row.getInt("rank")));

        return new RankupUser(
                row.getInt("id"),
                UUID.fromString(row.getString("unique_id")),
                rank,
                row.getDouble("points")
        );
    }
}
