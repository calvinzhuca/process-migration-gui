
package org.kie.processmigration.gui.model;

import java.util.Date;
import org.kie.processmigration.gui.model.ProcessInstanceList.ProcessInstance;
import java.text.SimpleDateFormat;
import javax.xml.datatype.XMLGregorianCalendar;

public class RunningInstance {
    private int id;
    private byte processInstanceId;
    private String name;
    private String description;
    private byte state;
    private String startTime;
    
    public RunningInstance(int i, ProcessInstance p){
        id = i;
        processInstanceId = p.getProcessInstanceId();
        name = p.getProcessName();
        description = p.getProcessInstanceDesc();
        state = p.getProcessInstanceState();
        startTime = convertDate(p.getStartDate());
    }

    private String convertDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        Date date =  calendar.toGregorianCalendar().getTime();
        
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return result;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(byte processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    
    
}
