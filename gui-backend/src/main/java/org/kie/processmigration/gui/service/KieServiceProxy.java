package org.kie.processmigration.gui.service;

import org.kie.processmigration.gui.model.ProcessInstanceList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface KieServiceProxy {

    @GET
    @Path("/services/rest/server/containers/{containerId}/processes/instances")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    ProcessInstanceList getRunningInstances(@PathParam("containerId") String containerId);

    @GET
    @Path("/services/rest/server/containers/{containerId}/images/processes/{processId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_SVG_XML})
    String getProcessDefinitionImage(@PathParam("containerId") String containerId, @PathParam("processId") String processId);

    @GET
    @Path("/services/rest/server/containers/{containerId}/processes/definitions/{processId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String getProcessDefinition(@PathParam("containerId") String containerId, @PathParam("processId") String processId);

}
