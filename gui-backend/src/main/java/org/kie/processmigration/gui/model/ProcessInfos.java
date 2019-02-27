package org.kie.processmigration.gui.model;

public class ProcessInfos {

    private ProcessInfo sourceInfo;

    private ProcessInfo targetInfo;

    public ProcessInfo getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(ProcessInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public ProcessInfo getTargetInfo() {
        return targetInfo;
    }

    public void setTargetInfo(ProcessInfo targetInfo) {
        this.targetInfo = targetInfo;
    }



    @Override
    public String toString() {
        return "ProcessInfos [sourceInfo=" + sourceInfo
                + ", targetInfo=" + targetInfo 
                + "]";
    }

}
