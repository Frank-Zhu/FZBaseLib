package com.frankzhu.appbaselibrary.dao.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-11-22 14:31
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 14-11-22      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZSQLiteTable {
    String mTableName;

    ArrayList<FZColumn> mColumnsDefinitions = new ArrayList<>();

    public String getTableName() {
        return mTableName;
    }

    /**
     * 会自动添加主键 BaseColumns._ID
     *
     * @param tableName 表名
     */
    public FZSQLiteTable(String tableName) {
        mTableName = tableName;
        mColumnsDefinitions.add(new FZColumn(BaseColumns._ID, FZColumn.Constraint.PRIMARY_KEY,
                FZColumn.DataType.INTEGER));
    }

    public FZSQLiteTable addColumn(FZColumn columnsDefinition) {
        mColumnsDefinitions.add(columnsDefinition);
        return this;
    }

    public FZSQLiteTable addColumn(String columnName, FZColumn.DataType dataType) {
        mColumnsDefinitions.add(new FZColumn(columnName, null, dataType));
        return this;
    }

    public FZSQLiteTable addColumn(String columnName, FZColumn.Constraint constraint,
                                   FZColumn.DataType dataType) {
        mColumnsDefinitions.add(new FZColumn(columnName, constraint, dataType));
        return this;
    }

    public void create(SQLiteDatabase db) {
        String formatter = " %s";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(mTableName);
        stringBuilder.append("(");
        int columnCount = mColumnsDefinitions.size();
        int index = 0;
        for (FZColumn columnsDefinition : mColumnsDefinitions) {
            stringBuilder.append(columnsDefinition.getColumnName()).append(
                    String.format(formatter, columnsDefinition.getDataType().name()));
            FZColumn.Constraint constraint = columnsDefinition.getConstraint();

            if (constraint != null) {
                stringBuilder.append(String.format(formatter, constraint.toString()));
            }
            if (index < columnCount - 1) {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append(");");
        db.execSQL(stringBuilder.toString());
    }

    public void delete(final SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + mTableName);
    }
}
