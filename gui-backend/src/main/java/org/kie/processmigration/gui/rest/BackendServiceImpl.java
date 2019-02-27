package org.kie.processmigration.gui.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.kie.processmigration.gui.model.ProcessInfos;
import org.kie.processmigration.gui.model.ProcessInfo;
import org.kie.processmigration.gui.model.ProcessInstanceList;
import org.kie.processmigration.gui.model.ProcessInstanceList.ProcessInstance;
import org.kie.processmigration.gui.model.RunningInstance;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import org.eclipse.aether.artifact.Artifact;
import org.kie.api.KieServices;
import org.kie.api.management.GAV;
import org.kie.scanner.KieMavenRepository;
import org.drools.compiler.kie.builder.impl.KieServicesImpl;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;



/*
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.bridge.BridgeContext;
 */
public class BackendServiceImpl {

    private static Logger logger = Logger.getLogger(BackendServiceImpl.class.getName());
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();



    public static String getInfoJsonFromKjar(String processId, String groupId, String artifactId, String version) throws IOException {
        ProcessInfo info = getInfoFromKjar(processId, groupId, artifactId, version);

        return gson.toJson(info);
    }

    public static String getBothInfoJsonFromKjar(String sourceProcessId, String sourceGroupId, String sourceArtifactId, String sourceVersion, String targetProcessId, String targetGroupId, String targetArtifactId, String targetVersion) throws IOException {
        ProcessInfos bothInfo = new ProcessInfos();

        ProcessInfo sourceInfo = getInfoFromKjar(sourceProcessId, sourceGroupId, sourceArtifactId, sourceVersion);
        ProcessInfo targetInfo = getInfoFromKjar(targetProcessId, targetGroupId, targetArtifactId, targetVersion);

        bothInfo.setSourceInfo(sourceInfo);
        bothInfo.setTargetInfo(targetInfo);

        return gson.toJson(bothInfo);
    }

    public static ProcessInfo getInfoFromKjar(String processId, String groupId, String artifactId, String version) throws IOException {
        ProcessInfo info = new ProcessInfo();
        info.setProcessId(processId);
        info.setContainerId(artifactId + "_" + version);
        retriveProcessInfoFromKjar(groupId, artifactId, version, info);
        return info;
    }

    public static void retriveProcessInfoFromKjar(String groupId, String artifactId, String version, ProcessInfo info) throws IOException {
        //System.out.println("######################################retriveProcessInfoFromKjar started, isV1 " + isV1);
        KieServices ks = (KieServicesImpl) KieServices.Factory.get();

        GAV gav = new GAV(groupId, artifactId, version);

        KieMavenRepository repository = KieMavenRepository.getKieMavenRepository();

        Artifact artifact = repository.resolveArtifact(gav);
        File kjarFile = artifact.getFile();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!kjarFile.getPath() " + kjarFile.getPath());

        JarFile jarFile = new JarFile(kjarFile);
        Enumeration enumEntries = jarFile.entries();
        while (enumEntries.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) enumEntries.nextElement();
            //System.out.println("------------jarEntry.getName(): " + jarEntry.getName());

            if (jarEntry.getName().contains("bpmn")) {
                //System.out.println("!!!!!!!!!!!!!! found bpmn file");
                //System.out.println("jarEntry.getName(): " + jarEntry.getName());

                // get the input stream
                InputStream inputStream = jarFile.getInputStream(jarEntry);

                info.setNodes(parseBpmnFile(inputStream));
                fromBpmNodesToStrings(info);
                inputStream.close();

            } else if (jarEntry.getName().contains("svg")) {
                //This is the process diagram file

                //System.out.println("!!!!!!!!!!!!!! found svg file");
                //System.out.println("jarEntry.getName(): " + jarEntry.getName());
                String tmpStr = info.getProcessId();

                /*
                String tmpNameV1 = tmpStr;                
                int i = tmpStr.indexOf(".");
                if (i > -1) {
                    tmpNameV1 = tmpStr.substring(tmpStr.indexOf("."));
                }
                if (jarEntry.getName().contains(tmpNameV1)) {                
                 */
                if (jarEntry.getName().contains(tmpStr)) {
                    InputStream inputStream = jarFile.getInputStream(jarEntry);
                    //testSvgSize(inputStream);
                    Scanner s = new Scanner(inputStream).useDelimiter("\\A");
                    String tmpSvg = s.hasNext() ? s.next() : "";
                    //Add this replacement here because in react-svgmt, ? and = are not allowed. 
                    tmpSvg = tmpSvg.replaceAll("\\?shapeType=BACKGROUND", "_shapeType_BACKGROUND");

                    inputStream.close();

                    info.setSvgFile(tmpSvg);
                }

            }
        }
        jarFile.close();
        //System.out.println("######################################retriveProcessInfoFromKjar ended ");

        //return info;
    }

    private static void fromBpmNodesToStrings(ProcessInfo info) {

        ArrayList<BpmNode> nodes = info.getNodes();
        ArrayList<String> values = new ArrayList();
        ArrayList<String> labels = new ArrayList();
        for (BpmNode node : nodes) {
            if (node.getType().equals("userTask")) {
                values.add(node.getId());
                labels.add(node.getName() + ":" + node.getId());
            }
        }

        info.setValues(values);
        info.setLabels(labels);

    }

    private static ArrayList<BpmNode> parseBpmnFile(InputStream inputStream) {

        SAXBuilder saxBuilder = new SAXBuilder();
        ArrayList<BpmNode> result = new ArrayList<>();
        try {

            Document document = (Document) saxBuilder.build(inputStream);
            //root element is bpmn2:definitions
            Element rootElement = document.getRootElement();

            Element processNode = rootElement.getChild("process", rootElement.getNamespace());

            List<Element> listElement = processNode.getChildren();

            for (Element node : listElement) {

                if (!node.getName().equals("sequenceFlow") && !node.getName().equals("startEvent") && !node.getName().equals("endEvent")) {
                    BpmNode bpmNode = new BpmNode();
                    bpmNode.setType(node.getName());
                    bpmNode.setName(node.getAttributeValue("name"));
                    bpmNode.setId(node.getAttributeValue("id"));
                    result.add(bpmNode);
                }
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        return result;
    }



    public static String getRunningInstancesFromKieServer(String containerId) throws URISyntaxException {
        ProcessInstanceList instanceList = ServicesUtil.getKieService().getRunningInstances(containerId);
        List<ProcessInstance> instances= instanceList.getProcessInstance();
        int i = 0;
        List<RunningInstance> result = new ArrayList<RunningInstance>();
        for (ProcessInstance instance: instances){
            i ++;
            result.add(new RunningInstance(i, instance));
        }
        return gson.toJson(result);
    }

 
}
