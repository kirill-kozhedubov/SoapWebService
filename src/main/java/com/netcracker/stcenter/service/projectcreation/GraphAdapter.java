package com.netcracker.stcenter.service.projectcreation;

import com.netcracker.stcenter.testrequest.GraphType;

public class GraphAdapter {

    private GraphType[] graphs;


    public GraphAdapter(GraphType[] graphs) {
        this.graphs = graphs;
    }

    public GraphType[] getGraphs() {
        return graphs;
    }
}
