package org.kie.processmigration.gui.model;

public class BpmNode {

    private String name;
    private String id;
    private String type;

    //note this targetId is only used in nodeMapping, request from JSP to backend. No value from parse bpmn2 file. 
    //private String targetId;

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



    @Override
    public String toString() {
        return "BpmNode [name=" + name + ", type=" + type 
                + ", id=" + id + "]";
    }

}
