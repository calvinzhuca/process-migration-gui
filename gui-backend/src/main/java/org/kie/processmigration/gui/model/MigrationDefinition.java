package org.kie.processmigration.gui.model;

import java.util.Arrays;

public class MigrationDefinition {
   private Execution execution;

    private String planId;

    private String[] processInstanceIds;

    
    private String kieserverId;

    public String getKieserverId() {
        return kieserverId;
    }

    public void setKieserverId(String kieserverId) {
        this.kieserverId = kieserverId;
    }
    
    public Execution getExecution ()
    {
        return execution;
    }

    public void setExecution (Execution execution)
    {
        this.execution = execution;
    }

    public String getPlanId ()
    {
        return planId;
    }

    public void setPlanId (String planId)
    {
        this.planId = planId;
    }

    public String[] getProcessInstanceIds ()
    {
        return processInstanceIds;
    }

    public void setProcessInstanceIds (String[] processInstanceIds)
    {
        this.processInstanceIds = processInstanceIds;
    }

    @Override
    public String toString()
    {
        return "MigrationDefinition [planId = "+ planId
                + " kieserverId = " + kieserverId 
                + " processInstanceIds = " + Arrays.toString(processInstanceIds) 
                + " execution = "+execution
                +", ]";
    }
}
