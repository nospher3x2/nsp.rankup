package com.nospher.rankup.user.dao;

import com.google.common.collect.Lists;
import com.nospher.rankup.database.builder.DAOBuilder;
import com.nospher.rankup.database.data.Parameters;
import com.nospher.rankup.database.table.TableColumn;
import com.nospher.rankup.user.data.RankupUser;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author oNospher
 **/
public class RankupUserDAO<U extends RankupUser> extends DAOBuilder<U> {

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
        table.addColumn("rank", TableColumn.STRING.value(255));
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
                element.getRank().getName(),
                    element.getPoints()
        );

        return (U) new RankupUser(
                result,
                element.getUniqueId(),
                element.getRank(),
                element.getPoints()
        );
    }

    public <K extends String, V> void update(List<Parameters<K, V>> parameters, U element) throws SQLException {
        K[] fields = (K[]) new String[parameters.size()];
        Object[] values = new Object[parameters.size()];

        parameters.forEach(key -> {
            Arrays.asList(fields).add(key.getKey());
            Arrays.asList(values).add(key.getValue());
        });

        Integer result = table.update(fields).values(values).where("id", element.getId()).execute();
        if(result <= 0) this.insert(element);
    }

    @Override
    public <K, V> U fetchOne(K key, V value) {
        return super.fetchOne(key, value);
    }
}
