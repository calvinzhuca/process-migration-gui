package org.kie.processmigration.gui.model;

import org.kie.processmigration.gui.rest.BpmNode;
import java.util.ArrayList;

public class ProcessInfo {

    private ArrayList<BpmNode> nodes;
    private String containerId;
    private String processId; 
    private ArrayList<String> values;
    private ArrayList<String> labels;
    private String svgFile;


    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public ArrayList<BpmNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<BpmNode> nodes) {
        this.nodes = nodes;
    }



    public String getSvgFile() {
        return svgFile;
    }

    public void setSvgFile(String svgFile) {
        this.svgFile = svgFile;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }






    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }


    @Override
    public String toString() {
        return "ProcessInfo [nodes=" + nodes
                + ", containerId=" + containerId + ", processId=" + processId 
                + ", values=" + values + ", labels=" + labels 
                + "]";
    }

}
