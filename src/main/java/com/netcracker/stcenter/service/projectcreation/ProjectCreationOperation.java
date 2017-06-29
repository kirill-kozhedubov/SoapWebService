package com.netcracker.stcenter.service.projectcreation;


import com.netcracker.stcenter.common.ProjectCreationResultHolder;
import com.netcracker.stcenter.service.AbstractServiceOperation;
import com.netcracker.stcenter.service.RemoteException;
import com.netcracker.stcenter.testrequest.ProjectCreationRequestDocument;
import com.netcracker.stcenter.testresponse.ProjectCreationResponseDocument;


public class ProjectCreationOperation extends AbstractServiceOperation<ProjectCreationRequestDocument, ProjectCreationResponseDocument> {
    @Override
    protected ProjectCreationResponseDocument invokeOperation(ProjectCreationRequestDocument requestDocument) throws RemoteException {
        ProjectCreationRequestAdapter requestAdapter = new ProjectCreationRequestAdapter(requestDocument);
        ProjectCreationResultHolder resultHolder = ProjectCreator.execute(requestAdapter);
        return new ProjectCreationResponseAdapter().setResultHolder(resultHolder).build();
    }

    public ProjectCreationOperation(ProjectCreationRequestDocument requestDocument) {
        super(requestDocument);
    }
}
