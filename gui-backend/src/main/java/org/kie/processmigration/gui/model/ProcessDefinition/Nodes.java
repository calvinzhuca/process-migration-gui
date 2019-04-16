package org.kie.processmigration.gui.model.ProcessDefinition;

import com.google.gson.annotations.SerializedName;

public class Nodes {

    private String name;

    private String id;

    private String type;
    
    @SerializedName("unique-id")
    private String uniqueId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", id = " + id + ", type = " + type + ", uniqueId = " + uniqueId + "]";
    }
}
