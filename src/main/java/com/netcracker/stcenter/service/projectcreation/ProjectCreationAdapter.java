package com.netcracker.stcenter.service.projectcreation;

import com.netcracker.stcenter.testrequest.ProjectCreationRequestType;
import com.netcracker.stcenter.testrequest.GraphType;
import com.netcracker.stcenter.testrequest.TableType;
import com.netcracker.stcenter.testrequest.GraphsType;


public class ProjectCreationAdapter {


    private GraphAdapter graphAdapter;
    private TableAdapter tableAdapter;
    private String projectName;
    private String dateFormat;

    public ProjectCreationAdapter(ProjectCreationRequestType projectCreationRequestType) {
        GraphType graphType;
        TableType tableType;

        GraphsType graphsType = projectCreationRequestType.getGraphs();
        tableType = projectCreationRequestType.getTable();


        graphAdapter = new GraphAdapter(graphsType.getGraphArray());
        tableAdapter = new TableAdapter(tableType);
        projectName = projectCreationRequestType.getProjectname();
        dateFormat = projectCreationRequestType.getDateformat();
    }

    public GraphAdapter getGraphAdapter() {
        return graphAdapter;
    }

    public TableAdapter getTableAdapter() {
        return tableAdapter;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
