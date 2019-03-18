package org.kie.processmigration.gui.service;

import java.io.IOException;

public interface WorkbenchServices {

   public String getInfoJsonFromKjar(String processId, String groupId, String artifactId, String version) throws IOException ;


   public String getBothInfoJsonFromKjar(String sourceProcessId, String sourceGroupId, String sourceArtifactId, String sourceVersion, String targetProcessId, String targetGroupId, String targetArtifactId, String targetVersion) throws IOException;
    
   
}
