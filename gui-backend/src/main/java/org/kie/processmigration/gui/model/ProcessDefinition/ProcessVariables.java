package org.kie.processmigration.gui.model.ProcessDefinition;

public class ProcessVariables
{
    private String reason;

    private String performance;

    private String initiator;

    private String employee;

    public String getReason ()
    {
        return reason;
    }

    public void setReason (String reason)
    {
        this.reason = reason;
    }

    public String getPerformance ()
    {
        return performance;
    }

    public void setPerformance (String performance)
    {
        this.performance = performance;
    }

    public String getInitiator ()
    {
        return initiator;
    }

    public void setInitiator (String initiator)
    {
        this.initiator = initiator;
    }

    public String getEmployee ()
    {
        return employee;
    }

    public void setEmployee (String employee)
    {
        this.employee = employee;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [reason = "+reason+", performance = "+performance+", initiator = "+initiator+", employee = "+employee+"]";
    }
}