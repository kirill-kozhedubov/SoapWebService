package com.netcracker.stcenter.service.projectcreation;

import com.netcracker.stcenter.common.Builder;
import com.netcracker.stcenter.common.ProjectCreationResultHolder;
import com.netcracker.stcenter.testresponse.ProjectCreationResponseDocument;
import com.netcracker.stcenter.testresponse.ProjectCreationResponseType;


public class ProjectCreationResponseAdapter implements Builder<ProjectCreationResponseDocument> {
    ProjectCreationResultHolder resultHolder;

    public ProjectCreationResponseAdapter setResultHolder(ProjectCreationResultHolder resultHolder) {
        this.resultHolder = resultHolder;
        return this;
    }

    @Override
    public ProjectCreationResponseDocument build() {
        if (resultHolder == null) {
            throw new RuntimeException("There is no result for building response");
        }
        ProjectCreationResponseDocument responseDocument = ProjectCreationResponseDocument.Factory.newInstance();
        ProjectCreationResponseType projectCreationResponseDocumentType = responseDocument.addNewProjectCreationResponse();
        projectCreationResponseDocumentType.setStatus(resultHolder.isStatus());
        projectCreationResponseDocumentType.setProjectname(resultHolder.getProjectName());
        projectCreationResponseDocumentType.setUserlogin(resultHolder.getUserLogin());
        projectCreationResponseDocumentType.setUserpassword(resultHolder.getUserPassword());


        return responseDocument;
    }
}
