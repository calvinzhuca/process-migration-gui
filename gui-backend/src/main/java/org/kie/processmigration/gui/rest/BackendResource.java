package org.kie.processmigration.gui.rest;

//import org.kie.processmigration.gui.service.WorkbenchServices;
import org.kie.processmigration.gui.model.MigrationDefinition;
import org.kie.processmigration.gui.model.Plan;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import org.kie.processmigration.gui.service.KieService;
import org.kie.processmigration.gui.service.PimService;

@Path("/")
public class BackendResource {

    //@Inject
    //WorkbenchServices workbenchServices;

    @Inject
    KieService kieService;

    @Inject
    PimService pimService;
    
    @GET
    @Path("/both")
    public Response getBothProcessInfo(
            @QueryParam("sourceProcessId") String sourceProcessId, @QueryParam("sourceGroupId") String sourceGroupId, @QueryParam("sourceArtifactId") String sourceArtifactId, @QueryParam("sourceVersion") String sourceVersion,
            @QueryParam("targetProcessId") String targetProcessId, @QueryParam("targetGroupId") String targetGroupId, @QueryParam("targetArtifactId") String targetArtifactId, @QueryParam("targetVersion") String targetVersion
    ) throws URISyntaxException {
        
        //Change from parsing workbench's kjar to invoke KIE service 
     /*  
        String result = workbenchServices.getBothInfoJsonFromKjar(sourceProcessId, sourceGroupId, sourceArtifactId, sourceVersion,
                targetProcessId, targetGroupId, targetArtifactId, targetVersion
        );

        */
     
        String result = kieService.getBothInfoJson("evaluation_1.0.0-SNAPSHOT", "evaluation", "mortgage-process_1.0.0-SNAPSHOT", "Mortgage_Process.MortgageApprovalProcess");

        return Response.ok(result).build();
    }

    @GET
    @Path("/instances")
    public Response getRunningInstances(@QueryParam("containerId") String containerId
    ) throws IOException, URISyntaxException {
        String result = kieService.getRunningInstances(containerId);
        return Response.ok(result).build();
    }

    @GET
    @Path("/plans")
    public Response pimServicesGetAllPlans() throws IOException, URISyntaxException {
            String result = pimService.getAllPlans();
            return Response.ok(result).build();
    }

    @POST
    @Path("/plans")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pimServicesCreatePlan(Plan plan) throws IOException, URISyntaxException {
        String result = pimService.createPlan(plan);
        return Response.ok(result).build();
    }

    @PUT
    @Path("/plans/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pimServicesUpdatePlan(Plan plan, @PathParam("id") String id) throws IOException, URISyntaxException {
        String result = pimService.updatePlan(plan, id);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/plans/{id}")
    public Response pimServicesDeletePlan(@PathParam("id") String id) throws IOException, URISyntaxException {
        String result = pimService.deletePlan(id);
        return Response.ok(result).build();
    }

    @GET
    @Path("/migrations")
    public Response pimServicesGetAllMigrations() throws IOException, URISyntaxException {
        String result = pimService.getAllMigrations();
        return Response.ok(result).build();
    }

    @GET
    @Path("/migrations/{id}")
    public Response pimServicesGetOneMigration(@PathParam("id") String id) throws IOException, URISyntaxException {
        String result = pimService.getOneMigration(id);
        return Response.ok(result).build();
    }

    @GET
    @Path("/migrations/{id}/results")
    public Response pimServicesGetOneMigrationLogs(@PathParam("id") String id) throws IOException, URISyntaxException {
        String result = pimService.getOneMigrationLogs(id);
        return Response.ok(result).build();
    }

    @POST
    @Path("/migrations")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pimServicesExecuteMigration(MigrationDefinition migration) throws IOException, URISyntaxException {
        String result = pimService.executeMigration(migration);
        return Response.ok(result).build();
    }

    @PUT
    @Path("/migrations/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pimServicesUpdateMigration(MigrationDefinition migration, @PathParam("id") String id) throws IOException, URISyntaxException {
        String result = pimService.updateMigration(migration, id);
        return Response.ok(result).build();
    }
    
    @DELETE
    @Path("/migrations/{id}")
    public Response pimServicesDeleteMigration(@PathParam("id") String id) throws IOException, URISyntaxException {
        String result = pimService.deleteMigration(id);
        return Response.ok(result).build();
    }

}
