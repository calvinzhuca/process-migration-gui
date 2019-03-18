package org.kie.processmigration.gui.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedMap;
import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import org.kie.processmigration.gui.model.MigrationDefinition;
import org.kie.processmigration.gui.service.PimService;
import org.kie.processmigration.gui.service.WorkbenchServices;
import org.kie.processmigration.gui.service.KieService;
import org.kie.processmigration.gui.model.Plan;

@RunWith(MockitoJUnitRunner.class)
public class BackendResourceWithMockTest {

    @Mock
    private WorkbenchServices workbenchServiceMock;

    @Mock
    private KieService kieServiceMock;

    @Mock
    private PimService pimServiceMock;

    @InjectMocks
    public static BackendResource resource = new BackendResource();

    public static TestingRestServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = new TestingRestServer(resource);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void getKjarInfoFromMock() throws Exception {
        String mockResult = "mock kjar info";
        when(workbenchServiceMock.getBothInfoJsonFromKjar("123", "124", "125", "126", "127", "128", "129", "130")).thenReturn(mockResult);

        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("sourceProcessId", "123");
        queryParams.add("sourceGroupId", "124");
        queryParams.add("sourceArtifactId", "125");
        queryParams.add("sourceVersion", "126");
        queryParams.add("targetProcessId", "127");
        queryParams.add("targetGroupId", "128");
        queryParams.add("targetArtifactId", "129");
        queryParams.add("targetVersion", "130");
        Response response = server.newRequest("/both").queryParams(queryParams).request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- getKjarInfoFromMock is tested ");
    }

    @Test
    public void getRunningInstancesFromMock() throws Exception {
        String mockResult = "mock running instances number 123";
        when(kieServiceMock.getRunningInstances("123")).thenReturn(mockResult);

        Response response = server.newRequest("/instances").queryParam("containerId", "123").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- getRunningInstancesFromMock is tested ");
    }

    @Test
    public void pimServicesGetAllPlansFromMock() throws Exception {
        String mockResult = "mock returns all plans";
        when(pimServiceMock.getAllPlans()).thenReturn(mockResult);

        Response response = server.newRequest("/plans").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesGetAllPlansFromMock is tested ");
    }

    @Test
    public void pimServicesCreatePlanFromMock() throws Exception {
        String mockResult = "mock returns for create plan";

        when(pimServiceMock.createPlan(any(Plan.class))).thenReturn(mockResult);

        Plan testPlan = new Plan();
        testPlan.setName("testPlan");
        Response response = server.newRequest("/plans").request().buildPost(Entity.json(testPlan)).invoke();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesCreatePlanFromMock is tested ");
    }

    @Test
    public void pimServicesUpdatePlanFromMock() throws Exception {
        String mockResult = "mock returns for update plan";
        String id = "123";
        when(pimServiceMock.updatePlan(any(Plan.class), eq(id))).thenReturn(mockResult);

        Plan testPlan = new Plan();
        testPlan.setName("testPlan");
        Response response = server.newRequest("/plans").path(id).request().buildPut(Entity.json(testPlan)).invoke();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesUpdatePlanFromMock is tested ");
    }

    @Test
    public void pimServicesDeletePlanFromMock() throws Exception {
        String mockResult = "mock returns for delete plan";
        String id = "123";
        when(pimServiceMock.deletePlan(eq(id))).thenReturn(mockResult);

        Response response = server.newRequest("/plans").path(id).request().buildDelete().invoke();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesDeletePlanFromMock is tested ");
    }

    @Test
    public void pimServicesGetAllMigrationsFromMock() throws Exception {
        String mockResult = "mock returns all migration";
        when(pimServiceMock.getAllMigrations()).thenReturn(mockResult);

        Response response = server.newRequest("/migrations").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesGetAllMigrationsFromMock is tested ");
    }

    @Test
    public void pimServicesGetOneMigrationFromMock() throws Exception {
        String mockResult = "mock returns just one migration";
        when(pimServiceMock.getOneMigration("123")).thenReturn(mockResult);

        Response response = server.newRequest("/migrations").path("123").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesGetOneMigrationFromMock is tested ");
    }

    @Test
    public void pimServicesGetOneMigrationLogsFromMock() throws Exception {
        String mockResult = "mock returns just one migration log";
        when(pimServiceMock.getOneMigrationLogs("123")).thenReturn(mockResult);

        Response response = server.newRequest("/migrations").path("123/results").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesGetOneMigrationLogsFromMock is tested ");
    }

    @Test
    public void pimServicesExecuteMigrationFromMock() throws Exception {
        String mockResult = "mock returns for create migration definition";

        when(pimServiceMock.executeMigration(any(MigrationDefinition.class))).thenReturn(mockResult);

        MigrationDefinition migration = new MigrationDefinition();
        migration.setPlanId("123");
        Response response = server.newRequest("/migrations").request().buildPost(Entity.json(migration)).invoke();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesExecuteMigrationFromMock is tested ");
    }

    @Test
    public void pimServicesUpdateMigrationFromMock() throws Exception {
        String mockResult = "mock returns for update migration definition";
        String id = "123";
        when(pimServiceMock.updateMigration(any(MigrationDefinition.class), eq(id))).thenReturn(mockResult);

        MigrationDefinition migration = new MigrationDefinition();
        migration.setPlanId(id);
        Response response = server.newRequest("/migrations").path(id).request().buildPut(Entity.json(migration)).invoke();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesUpdateMigrationFromMock is tested ");
    }

    @Test
    public void pimServicesDeleteMigrationFromMock() throws Exception {
        String mockResult = "mock returns for delete migration definition";
        String id = "123";
        when(pimServiceMock.deleteMigration(eq(id))).thenReturn(mockResult);

        Response response = server.newRequest("/migrations").path(id).request().buildDelete().invoke();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        String returnJsonStr = response.readEntity(String.class);
        assertEquals(mockResult, returnJsonStr);
        System.out.println("-- pimServicesDeleteMigrationFromMock is tested ");
    }

}
