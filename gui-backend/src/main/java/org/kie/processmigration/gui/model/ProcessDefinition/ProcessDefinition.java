package org.kie.processmigration.gui.model.ProcessDefinition;

import java.util.ArrayList;
import java.util.Map;

public class ProcessDefinition
{
    private String[] reusableSubProcesses;

    private String processId;

    private String containerId;

    private String[] timers;

    private Nodes[] nodes;

    private String package1;

    private String processName;

    private String dynamic;

    private ProcessVariables processVariables;

    private String processVersion;

    public String[] getReusableSubProcesses ()
    {
        return reusableSubProcesses;
    }

    public void setReusableSubProcesses (String[] reusableSubProcesses)
    {
        this.reusableSubProcesses = reusableSubProcesses;
    }

    public String[] getTimers ()
    {
        return timers;
    }

    public void setTimers (String[] timers)
    {
        this.timers = timers;
    }

    public Nodes[] getNodes ()
    {
        return nodes;
    }

    public void setNodes (Nodes[] nodes)
    {
        this.nodes = nodes;
    }



    public String getDynamic ()
    {
        return dynamic;
    }

    public void setDynamic (String dynamic)
    {
        this.dynamic = dynamic;
    }



    public ProcessVariables getProcessVariables ()
    {
        return processVariables;
    }

    public void setProcessVariables (ProcessVariables processVariables)
    {
        this.processVariables = processVariables;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getPackage1() {
        return package1;
    }

    public void setPackage1(String package1) {
        this.package1 = package1;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessVersion() {
        return processVersion;
    }

    public void setProcessVersion(String processVersion) {
        this.processVersion = processVersion;
    }



    @Override
    public String toString()
    {
        return "ProcessDefinition [reusableSubProcesses = "+reusableSubProcesses+", processId = "+processId+", containerId = "+containerId+", timers = "+timers+", nodes = "+nodes+", package1 = "+package1+", processName = "+processName+", dynamic = "+dynamic+", processVariables = "+processVariables+", processVersion = "+processVersion+"]";

    }
}