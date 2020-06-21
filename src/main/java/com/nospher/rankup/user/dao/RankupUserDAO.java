package com.nospher.rankup.user.dao;

import com.nospher.rankup.database.builder.DAOBuilder;
import com.nospher.rankup.database.table.TableColumn;
import com.nospher.rankup.user.data.RankupUser;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

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
        table.addColumn("unique_id", TableColumn.UUID.value(255));
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


}
