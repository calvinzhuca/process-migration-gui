package org.kie.processmigration.gui.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.kie.processmigration.gui.model.ProcessDefinition.Nodes;
import org.kie.processmigration.gui.model.ProcessDefinition.ProcessDefinition;
import org.kie.processmigration.gui.model.ProcessInfo;
import org.kie.processmigration.gui.model.ProcessInfos;
import org.kie.processmigration.gui.model.ProcessInstanceList;
import org.kie.processmigration.gui.model.RunningInstance;
import org.kie.processmigration.gui.service.KieService;
import org.kie.processmigration.gui.util.ServicesUtil;

@ApplicationScoped
public class KieServiceImpl implements KieService {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String getRunningInstances(String containerId) throws URISyntaxException {
        ProcessInstanceList instanceList = ServicesUtil.getKieServiceProxy().getRunningInstances(containerId);
        List<ProcessInstanceList.ProcessInstance> instances = instanceList.getProcessInstance();
        int i = 0;
        List<RunningInstance> result = new ArrayList<RunningInstance>();
        for (ProcessInstanceList.ProcessInstance instance : instances) {
            i++;
            result.add(new RunningInstance(i, instance));
        }
        return gson.toJson(result);
    }

    @Override
    public String getBothInfoJson(String sourceContainerId, String sourceProcessId, String targetContainerId, String targetProcessId) throws URISyntaxException {

        ProcessInfos bothInfo = new ProcessInfos();

        ProcessInfo sourceInfo = getProcessInfo(sourceContainerId, sourceProcessId);
        sourceInfo.setContainerId(sourceContainerId);;
        //System.out.println("sourceInfo: " + gson.toJson(sourceInfo));

        ProcessInfo targetInfo = getProcessInfo(targetContainerId, targetProcessId);
        targetInfo.setContainerId(targetContainerId);;
        //System.out.println("targetInfo: " + gson.toJson(targetInfo));
        
        bothInfo.setSourceInfo(sourceInfo);
        bothInfo.setTargetInfo(targetInfo);
        return gson.toJson(bothInfo);

    }

    private ProcessInfo getProcessInfo(String containerId, String processId) throws URISyntaxException {
        //System.out.println("containerId: " + containerId);
        //System.out.println("processId: " + processId);

        ProcessInfo processInfo = new ProcessInfo();
        String svgFile = ServicesUtil.getKieServiceProxy().getProcessDefinitionImage(containerId, processId);
        processInfo.setSvgFile(svgFile);
        //System.out.println("svgFile: " + svgFile);
        String processDefinitionStr = ServicesUtil.getKieServiceProxy().getProcessDefinition(containerId, processId);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ProcessDefinition pd = gson.fromJson(processDefinitionStr, ProcessDefinition.class);

        Nodes[] nodes = pd.getNodes();
        ArrayList<String> values = new ArrayList();
        ArrayList<String> labels = new ArrayList();
        for (Nodes node : nodes) {
            if (node.getType().equals("HumanTaskNode")) {
                values.add(node.getUniqueId());
                labels.add(node.getName() + ":" + node.getUniqueId());
            }
        }
        processInfo.setValues(values);
        processInfo.setLabels(labels);

        return processInfo;
    }

}
