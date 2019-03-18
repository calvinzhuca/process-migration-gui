
package org.kie.processmigration.gui.rest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;


public class TestingRestServer {
    public static TJWSEmbeddedJaxrsServer server;
    
    private static final int port = 12345;
    private static final String baseUri = "http://localhost"+ ":" + port;
    private final ResteasyClient resteasyClient = new ResteasyClientBuilder().build();;    

    TestingRestServer(Object obj){
        server = new TJWSEmbeddedJaxrsServer();
        server.setPort(port);
         server.getDeployment().getResources().add(obj);
         server.start();
    }

  
    public ResteasyWebTarget newRequest(String uriTemplate) {
        return resteasyClient.target(baseUri + uriTemplate);
    }
    
    public void close() {
        if (server != null) {
            server.stop();
            server = null;
        }
}    
        
}
