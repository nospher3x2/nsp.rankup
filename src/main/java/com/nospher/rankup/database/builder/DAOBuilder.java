package com.nospher.rankup.database.builder;

import com.nospher.rankup.database.MySQL;
import com.nospher.rankup.database.data.Parameters;
import com.nospher.rankup.database.manager.MySQLManager;
import com.nospher.rankup.database.table.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author oNospher
 **/
public abstract class DAOBuilder<T> {

    public Table table = new Table(this.getTableName());

    public DAOBuilder() {
        MySQL mySQL = MySQLManager.getMySQL(this.getDatabaseName());
        Table.setDefaultConnection(mySQL.getConnection());
    }

    public abstract String getDatabaseName();

    public abstract String getTableName();

    public abstract void createTable();

    public T insert(T element) throws SQLException { return null; }

    public <K, V> void update(List<Parameters<K, V>> keys, T element) throws SQLException {}

    public <K, V> void delete(K key, V value) throws SQLException {}

    public <K, V> T fetchOne(K key, V value) { return null; }

    public <K, V> Set<T> fetchAll(K key, V value) { return null; }

    public Set<T> fetchAll() { return null; }
}
