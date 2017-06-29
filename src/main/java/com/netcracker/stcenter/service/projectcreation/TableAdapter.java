package com.netcracker.stcenter.service.projectcreation;

import com.netcracker.stcenter.testrequest.TableType;

public class TableAdapter {
    private TableType tableType;

    public TableAdapter(TableType tableType) {
        this.tableType = tableType;
    }

    public TableType getTableType() {
        return tableType;
    }

    public String getTableXml() {
        return tableType.xmlText();
    }
}
