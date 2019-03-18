package org.kie.processmigration.gui.service;

import org.kie.processmigration.gui.model.MigrationDefinition;
import org.kie.processmigration.gui.model.Plan;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public interface PimServiceProxy {

    @GET
    @Path("/plans")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String getAllPlans();

            
    @POST
    @Path("/plans")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String createPlan(Plan plan);
    
            
    @PUT
    @Path("/plans/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String updatePlan(Plan plan, @PathParam("id") String id);    

    @DELETE
    @Path("/plans/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String deletePlan(@PathParam("id") String id);

    @POST
    @Path("/migrations")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String executeMigration(MigrationDefinition migration);
        
    @GET
    @Path("/migrations")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String getAllMigrations();

    @GET
    @Path("/migrations/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String getOneMigration(@PathParam("id") String id);

    @GET
    @Path("/migrations/{id}/results")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String getOneMigrationLogs(@PathParam("id") String id);

    @DELETE
    @Path("/migrations/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String deleteMigration(@PathParam("id") String id);

    @PUT
    @Path("/migrations/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    String updateMigration(MigrationDefinition migration, @PathParam("id") String id);    
    
                                
}
