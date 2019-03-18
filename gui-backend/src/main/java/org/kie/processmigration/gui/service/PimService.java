package org.kie.processmigration.gui.service;

import java.net.URISyntaxException;
import org.kie.processmigration.gui.model.MigrationDefinition;
import org.kie.processmigration.gui.model.Plan;

public interface PimService {

    public String getAllPlans() throws URISyntaxException;

    public String createPlan(Plan plan) throws URISyntaxException;

    public String updatePlan(Plan plan, String id) throws URISyntaxException;

    public String deletePlan(String id) throws URISyntaxException;

    public String executeMigration(MigrationDefinition migration) throws URISyntaxException;

    public String getAllMigrations() throws URISyntaxException;

    public String getOneMigration(String id) throws URISyntaxException;

    public String getOneMigrationLogs(String id) throws URISyntaxException;

    public String deleteMigration(String id) throws URISyntaxException;

    public String updateMigration(MigrationDefinition migration, String id) throws URISyntaxException;
}
