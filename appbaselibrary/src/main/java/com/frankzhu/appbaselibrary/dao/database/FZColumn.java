package com.frankzhu.appbaselibrary.dao.database;

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
public class FZColumn {
    public enum Constraint {
        UNIQUE("UNIQUE"), NOT("NOT"), NULL("NULL"), CHECK("CHECK"), FOREIGN_KEY("FOREIGN KEY"), PRIMARY_KEY(
                "PRIMARY KEY");

        private String value;

        Constraint(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum DataType {
        NULL, INTEGER, REAL, TEXT, BLOB
    }

    private String mColumnName;

    private Constraint mConstraint;

    private DataType mDataType;

    public FZColumn(String columnName, Constraint constraint, DataType dataType) {
        mColumnName = columnName;
        mConstraint = constraint;
        mDataType = dataType;
    }

    public String getColumnName() {
        return mColumnName;
    }

    public Constraint getConstraint() {
        return mConstraint;
    }

    public DataType getDataType() {
        return mDataType;
    }
}
