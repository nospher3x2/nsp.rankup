package com.nospher.rankup.user.data;

import com.nospher.rankup.rank.data.Rank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class RankupUser {

    private final Integer id;
    private final UUID uniqueId;

    @Setter
    private Rank rank;

    @Setter
    private Double points;

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uniqueId);
    }
}
