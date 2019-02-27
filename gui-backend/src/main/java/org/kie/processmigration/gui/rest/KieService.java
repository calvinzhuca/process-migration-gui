package org.kie.processmigration.gui.rest;

import org.kie.processmigration.gui.model.ProcessInstanceList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public interface KieService {

    @GET
    @Path("/services/rest/server/containers/{containerId}/processes/instances")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    ProcessInstanceList getRunningInstances(@PathParam("containerId") String containerId);

            
    
}
