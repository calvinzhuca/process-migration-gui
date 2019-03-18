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
        String result = ServicesUtil.getPimService().getAllPlans();
        return result;
    }

    @Override
    public String createPlan(Plan plan) throws URISyntaxException {
        String result = ServicesUtil.getPimService().createPlan(plan);
        return result;
    }

    @Override
    public String updatePlan(Plan plan, String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().updatePlan(plan, id);
        return result;
    }

    @Override
    public String deletePlan(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().deletePlan(id);
        return result;
    }

    @Override
    public String executeMigration(MigrationDefinition migration) throws URISyntaxException {
        String result = ServicesUtil.getPimService().executeMigration(migration);
        return result;
    }

    @Override
    public String getAllMigrations() throws URISyntaxException {
        String result = ServicesUtil.getPimService().getAllMigrations();
        return result;
    }

    @Override
    public String getOneMigration(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().getOneMigration(id);
        System.out.println("!!!!!!!!!!!!!!getOneMigration result: " + result);
        if (result == null) {
            result = "";
        }
        return result;
    }

    @Override
    public String getOneMigrationLogs(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().getOneMigrationLogs(id);
        System.out.println("!!!!!!!!!!!!!!getOneMigration result: " + result);
        if (result == null) {
            result = "";
        }
        return result;
    }

    @Override
    public String deleteMigration(String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().deleteMigration(id);
        return result;
    }

    @Override
    public String updateMigration(MigrationDefinition migration, String id) throws URISyntaxException {
        String result = ServicesUtil.getPimService().updateMigration(migration, id);
        return result;
    }
}
