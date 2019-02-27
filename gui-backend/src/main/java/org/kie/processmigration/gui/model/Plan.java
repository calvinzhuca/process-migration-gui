package org.kie.processmigration.gui.model;

import java.util.Map;
 
public class Plan {
    private String id;
    
    private String targetContainerId;

    private String targetProcessId;

    private Map<String, String> mappings;

    private String sourceContainerId;

    private String name;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetContainerId ()
    {
        return targetContainerId;
    }

    public void setTargetContainerId (String targetContainerId)
    {
        this.targetContainerId = targetContainerId;
    }

    public String getTargetProcessId ()
    {
        return targetProcessId;
    }

    public void setTargetProcessId (String targetProcessId)
    {
        this.targetProcessId = targetProcessId;
    }

    public Map<String, String> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, String> mappings) {
        this.mappings = mappings;
    }

    public String getSourceContainerId ()
    {
        return sourceContainerId;
    }

    public void setSourceContainerId (String sourceContainerId)
    {
        this.sourceContainerId = sourceContainerId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "targetContainerId = "+targetContainerId+", targetProcessId = "+targetProcessId+", mappings = "+mappings+", sourceContainerId = "+sourceContainerId+", name = "+name+", description = "+description;
    }    
}
