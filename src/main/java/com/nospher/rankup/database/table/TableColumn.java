package com.nospher.rankup.database.table;

import lombok.Getter;
import lombok.Setter;

public class TableColumn {

    public static final TableColumn
        ID = new TableColumn("INT NOT NULL AUTO_INCREMENT"),
        UUID = new TableColumn("VARCHAR(%d) NOT NULL"),
        STRING = new TableColumn("VARCHAR(%d)"),
        BYTE = new TableColumn("TINYINT"),
        BOOLEAN = BYTE.clone(),
        SHORT = new TableColumn("MEDIUMINT"),
        INTEGER = new TableColumn("INT"),
        DOUBLE = new TableColumn("DOUBLE"),
        LONG = new TableColumn("BIGINT"),
        TEXT = new TableColumn("TEXT");


    static {
        ID.setPrimaryKey(true);
        UUID.setPrimaryKey(true);
    }

    @Getter
    @Setter
    private String syntax;
    @Getter
    @Setter
    private String defaultValue;
    @Getter
    @Setter
    private boolean primaryKey = false;

    public TableColumn(String syntax) {
        this.syntax = syntax;
    }

    public TableColumn(TableColumn clone) {
        this.syntax = clone.syntax;
        this.defaultValue = clone.defaultValue;
        this.primaryKey = clone.primaryKey;
    }

    public TableColumn value(Integer length) {
        String syntax = this.syntax = String.format(
                this.syntax,
                length
        );

        TableColumn tableColumn = this.clone();
        tableColumn.setSyntax(syntax);

        return tableColumn;
    }

    @Override
    protected TableColumn clone(){
        return new TableColumn(this);
    }
}
