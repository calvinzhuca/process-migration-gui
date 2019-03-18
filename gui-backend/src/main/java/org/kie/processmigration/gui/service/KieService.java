package org.kie.processmigration.gui.service;

import java.net.URISyntaxException;

public interface KieService {
    public String getRunningInstances(String containerId) throws URISyntaxException ;    
}
