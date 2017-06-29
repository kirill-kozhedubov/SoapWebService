
/**
 * SoapSampleWebServiceSkeleton.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */
package com.netcracker.stcenter.service;

import com.netcracker.stcenter.service.projectcreation.ProjectCreationOperation;

/**
 * SoapSampleWebServiceSkeleton java skeleton for the axisService
 */

public class SoapSampleWebServiceSkeleton {

    public com.netcracker.stcenter.testresponse.ProjectCreationResponseDocument createProject(
            com.netcracker.stcenter.testrequest.ProjectCreationRequestDocument projectCreationRequest)
            throws RemoteException, Exception {
        return new ProjectCreationOperation(projectCreationRequest).call();
    }

}
    