package com.netcracker.stcenter.project.dao;

import com.netcracker.stcenter.project.models.DataVisualizationProject;

import java.math.BigInteger;

public interface ProjectCreationDAO {
    DataVisualizationProject saveProject(DataVisualizationProject project);
    DataVisualizationProject getProjectByIdCut(BigInteger id);
}
