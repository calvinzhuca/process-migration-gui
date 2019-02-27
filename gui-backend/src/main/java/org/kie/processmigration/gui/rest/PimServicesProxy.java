package org.kie.processmigration.gui.rest;

import org.kie.processmigration.gui.model.MigrationDefinition;
import org.kie.processmigration.gui.model.Plan;
import java.net.URISyntaxException;

public class PimServicesProxy {


    public static String getAllPlans() throws URISyntaxException {
        String result = ServicesUtil.getPimService().getAllPlans();
        return result;
    }

    public static String createPlan(Plan plan) throws URISyntaxException {
        String result = ServicesUtil.getPimService().createPlan(plan);
        return result;
    }
    

    public static String updatePlan(Plan plan, String id) throws URISyntaxException {
         String result = ServicesUtil.getPimService().updatePlan(plan, id);
        return result;
    }    
    
    public static String deletePlan(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().deletePlan(id);
        return result;
    }    
    
    public static String executeMigration(MigrationDefinition migration) throws URISyntaxException {
        String result = ServicesUtil.getPimService().executeMigration(migration);
        return result;
    }
    
    
    public static String getAllMigrations() throws URISyntaxException {
        String result = ServicesUtil.getPimService().getAllMigrations();
        return result;
    }

    public static String getOneMigration(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().getOneMigration(id);
        System.out.println("!!!!!!!!!!!!!!getOneMigration result: " + result);
        if (result == null) result = "";
        return result;
    }    
    
    public static String getOneMigrationLogs(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().getOneMigrationLogs(id);
        System.out.println("!!!!!!!!!!!!!!getOneMigration result: " + result);
        if (result == null) result = "";
        return result;
    }   
    
    public static String deleteMigration(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().deleteMigration(id);
        return result;
    }     
    
    public static String updateMigration(MigrationDefinition migration, String id) throws URISyntaxException {
         String result = ServicesUtil.getPimService().updateMigration(migration, id);
        return result;
    }        
}
