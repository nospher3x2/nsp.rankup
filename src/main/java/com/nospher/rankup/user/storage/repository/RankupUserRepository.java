package com.nospher.rankup.user.storage.repository;

import com.google.common.collect.Sets;

import com.nospher.rankup.RankupProvider;
import com.nospher.rankup.database.mysql.data.Parameters;
import com.nospher.rankup.util.storage.repository.NspRepository;
import com.nospher.rankup.database.table.TableColumn;
import com.nospher.rankup.database.table.TableRow;
import com.nospher.rankup.user.entity.RankupUser;
import com.nospher.rankup.user.storage.cache.RankupUserCache;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author oNospher
 **/
public class RankupUserRepository<U extends RankupUser> extends NspRepository<U> {

    @Override
    public String getDatabaseName() {
        return "general";
    }

    @Override
    public String getTableName() {
        return "rankup_user";
    }

    @Override
    public void createTable() {
        table.addColumn("id", TableColumn.ID);
        table.addColumn("unique_id", TableColumn.UUID.value(36));
        table.addColumn("rank", TableColumn.INTEGER);
        table.addColumn("points", TableColumn.DOUBLE);
        table.create();
    }

    @Override
    public U insert(U element) throws SQLException {
        Integer result = table.insert(
                "unique_id",
                "rank",
                "points"
        ).one(
                element.getUniqueId().toString(),
                element.getRank().getHierarchy(),
                element.getPoints()
        );

        return (U) new RankupUser(
                result,
                element.getUniqueId(),
                element.getRank(),
                element.getPoints()
        );
    }

    @Override
    public <K extends String, V> void update(List<Parameters<K, V>> parameters, U element) throws SQLException {
        Parameters<K[], V[]> parametersGenerated = this.getParameters(parameters);

        Integer rowAffected = table.update(
                parametersGenerated.getKey()
        ).values(
                (Object) parametersGenerated.getValue()
        ).where("id", element.getId()).execute();

        if(rowAffected <= 0) this.insert(element);
    }

    public <K extends String, V> U fetch(K key, V value) {
        TableRow row = table.query().where(key, value).first();

        if(row == null) {
            U rankupUser = (U) RankupUser.newUser();

            RankupProvider.Cache.RANKUP_USER_CACHE.insert(rankupUser);
            return rankupUser;
        }

        RankupUser rankupUser = RankupUser.toUser(row);
        RankupProvider.Cache.RANKUP_USER_CACHE.insert(rankupUser);
        return (U) rankupUser;
    }

    public <K extends String, V> Set<U> fetchAll(K key, V value) {
        List<TableRow> rows = table.query().where(key, value).get();
        Set<U> rankupUsers = Sets.newConcurrentHashSet();

        rows.forEach(RankupUser::toUser);
        return rankupUsers;
    }
}
