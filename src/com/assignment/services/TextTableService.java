package com.assignment.services;

import com.assignment.interfaces.TableRecord;
import com.assignment.models.MyTableColumn;
import com.assignment.utils.Utility;

import java.util.HashMap;

public class TextTableService {
    public static <T extends TableRecord> String create(T[] dataSource, MyTableColumn[] columns){
        StringBuilder table = new StringBuilder();

        setMaxColumnWidth(dataSource, columns);
        setTableHeaders(columns, table);
        setTableRows(dataSource, columns, table);

        return table.toString();
    }

    static <T extends TableRecord> void setMaxColumnWidth(T[] dataSource, MyTableColumn[] columns){
        for(MyTableColumn c : columns){
            for(T p : dataSource){
                HashMap<String, String> tableRecords = p.getTableRecords();
                c.setMaxColumnWidth(Math.max(tableRecords.get(c.getKeyName()).length(), c.getMaxColumnWidth()));
            }
        }
    }

    static void setTableHeaders(MyTableColumn[] columns, StringBuilder table){
        for(int i = 0; i < columns.length; i ++){
            String columnLabel = Utility.rightPadStringWithSpaces(columns[i].getColumnLabel(), columns[i].getMaxColumnWidth());
            table.append(columnLabel);
            if(i < columns.length - 1){
                table.append(" | ");
            }
        }
        table.append("\n");
    }

    static <T extends TableRecord> void setTableRows(T[] dataSource, MyTableColumn[] columns, StringBuilder table){
        for(T p : dataSource){
            for(int i = 0; i < columns.length; i ++){
                HashMap<String, String> tableRecords = p.getTableRecords();
                String columnText = Utility.rightPadStringWithSpaces(tableRecords.get(columns[i].getKeyName()), columns[i].getMaxColumnWidth());
                table.append(columnText);
                if(i < columns.length - 1){
                    table.append(" | ");
                }
            }
            table.append("\n");
        }
    }
}
