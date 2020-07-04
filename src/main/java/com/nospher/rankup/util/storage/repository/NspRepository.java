package com.nospher.rankup.util.storage.repository;

import com.nospher.rankup.database.mysql.data.MySQL;
import com.nospher.rankup.database.mysql.data.Parameters;
import com.nospher.rankup.database.mysql.manager.MySQLManager;
import com.nospher.rankup.database.table.Table;
import org.omg.CORBA.Object;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author oNospher
 **/
public abstract class NspRepository<T> {

    public Table table = new Table(this.getTableName());

    public NspRepository() {
        MySQL mySQL = MySQLManager.getMySQL(this.getDatabaseName());
        Table.setDefaultConnection(mySQL.getConnection());
    }

    public abstract String getDatabaseName();

    public abstract String getTableName();

    public abstract void createTable();

    public T insert(T element) throws SQLException {
        return null;
    }

    public <K extends String, V> void update(List<Parameters<K, V>> parameters, T element) throws SQLException {
    }

    public <K extends String, V> void delete(K key, V value) throws SQLException {
        table.delete().where(key, value).execute();
    }

    public <K, V> T fetch(K key, V value) { return null; }

    public <K, V> Set<T> fetchAll(K key, V value) { return null; }

    public Set<T> fetchAll() { return null; }

    public Integer count() {
        return table.query().count();
    }

    public <K, V> Parameters<K[], V[]> getParameters(List<Parameters<K, V>> parameters) {
        K[] fields = (K[]) new String[parameters.size()];
        V[] values = (V[]) new Object[parameters.size()];

        parameters.forEach(key -> {
            Arrays.asList(fields).add(key.getKey());
            Arrays.asList(values).add(key.getValue());
        });

        return new Parameters<>(fields, values);
    }
}
