package com.assignment.models;

public class MyTableColumn {
    private String columnLabel;
    private String keyName;
    private int maxColumnWidth;

    public MyTableColumn(String columnLabel, String keyName){
        this.columnLabel = columnLabel;
        this.keyName = keyName;
        this.maxColumnWidth = columnLabel.length();
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getMaxColumnWidth() {
        return maxColumnWidth;
    }

    public void setMaxColumnWidth(int maxColumnWidth) {
        this.maxColumnWidth = maxColumnWidth;
    }
}
