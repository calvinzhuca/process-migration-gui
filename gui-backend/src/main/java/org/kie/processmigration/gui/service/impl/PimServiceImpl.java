package org.kie.processmigration.gui.service.impl;

import org.kie.processmigration.gui.model.MigrationDefinition;
import org.kie.processmigration.gui.model.Plan;
import java.net.URISyntaxException;
import javax.enterprise.context.ApplicationScoped;
import org.kie.processmigration.gui.util.ServicesUtil;
import org.kie.processmigration.gui.service.PimService;

@ApplicationScoped
public class PimServiceImpl implements PimService {

    @Override
    public String getAllPlans() throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().getAllPlans();
        return result;
    }

    @Override
    public String createPlan(Plan plan) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().createPlan(plan);
        return result;
    }

    @Override
    public String updatePlan(Plan plan, String id) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().updatePlan(plan, id);
        return result;
    }

    @Override
    public String deletePlan(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().deletePlan(id);
        return result;
    }

    @Override
    public String executeMigration(MigrationDefinition migration) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().executeMigration(migration);
        return result;
    }

    @Override
    public String getAllMigrations() throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().getAllMigrations();
        return result;
    }

    @Override
    public String getOneMigration(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().getOneMigration(id);
        System.out.println("!!!!!!!!!!!!!!getOneMigration result: " + result);
        if (result == null) {
            result = "";
        }
        return result;
    }

    @Override
    public String getOneMigrationLogs(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().getOneMigrationLogs(id);
        System.out.println("!!!!!!!!!!!!!!getOneMigration result: " + result);
        if (result == null) {
            result = "";
        }
        return result;
    }

    @Override
    public String deleteMigration(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().deleteMigration(id);
        return result;
    }

    @Override
    public String updateMigration(MigrationDefinition migration, String id) throws URISyntaxException {
        String result = ServicesUtil.getPimServiceProxy().updateMigration(migration, id);
        return result;
    }
}
