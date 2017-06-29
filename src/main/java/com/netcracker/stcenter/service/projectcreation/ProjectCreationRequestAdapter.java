package com.netcracker.stcenter.service.projectcreation;

import com.netcracker.stcenter.testrequest.ProjectCreationRequestDocument;
import com.netcracker.stcenter.testrequest.ProjectCreationRequestType;

public class ProjectCreationRequestAdapter {

    ProjectCreationAdapter projectCreationAdapter;

    public ProjectCreationRequestAdapter(ProjectCreationRequestDocument requestDocument) {
        ProjectCreationRequestType projectCreationRequestType = requestDocument.getProjectCreationRequest();
        projectCreationAdapter = new ProjectCreationAdapter(projectCreationRequestType);
    }

    public ProjectCreationAdapter getProjectCreationAdapter() {
        return projectCreationAdapter;
    }
}
