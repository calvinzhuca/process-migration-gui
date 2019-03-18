package org.kie.processmigration.gui.service.impl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.kie.processmigration.gui.model.ProcessInstanceList;
import org.kie.processmigration.gui.model.RunningInstance;
import org.kie.processmigration.gui.service.KieService;
import org.kie.processmigration.gui.util.ServicesUtil;

@ApplicationScoped
public class KieServiceImpl implements KieService{
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();



    @Override
    public String getRunningInstances(String containerId) throws URISyntaxException {
        ProcessInstanceList instanceList = ServicesUtil.getKieService().getRunningInstances(containerId);
        List<ProcessInstanceList.ProcessInstance> instances= instanceList.getProcessInstance();
        int i = 0;
        List<RunningInstance> result = new ArrayList<RunningInstance>();
        for (ProcessInstanceList.ProcessInstance instance: instances){
            i ++;
            result.add(new RunningInstance(i, instance));
        }
        return gson.toJson(result);
    }

     
}
