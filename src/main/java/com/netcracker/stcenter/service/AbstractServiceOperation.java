package com.netcracker.stcenter.service;


import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

public abstract class AbstractServiceOperation<Request extends XmlObject, Response extends XmlObject> implements Callable<Response> {
    private static final Logger log = Logger.getLogger(AbstractServiceOperation.class);

    private final Request requestDocument;
    private Response responseDocument;
    private long processingTime;

    @Override
    public final Response call() throws Exception {
        logRequest();
        processingTime = System.currentTimeMillis();
        responseDocument = invokeOperation(requestDocument);
        processingTime = System.currentTimeMillis() - processingTime;
        logResponse();
        return responseDocument;
    }

    public AbstractServiceOperation(final Request requestDocument) {
        this.requestDocument = requestDocument;
    }

    private void logRequest() {
        if (log.isDebugEnabled()) {
            log.debug("Start processing request\n" + requestDocument);
        }
    }

    protected abstract Response invokeOperation(Request request) throws RemoteException;

    private void logResponse() {
        if (log.isDebugEnabled()) {
            log.debug("Finish processing request. Processing time = " + processingTime + "ms. Response returned\n" + responseDocument);
        }
    }


}