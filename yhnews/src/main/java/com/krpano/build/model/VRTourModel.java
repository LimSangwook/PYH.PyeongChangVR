package com.krpano.build.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.krpano.build.XMLHelper;

public class VRTourModel {
    String title;
    private List<VRSceneModel> sceneList = new ArrayList<VRSceneModel>();
    private List<VRGroupModel> groupList = new ArrayList<VRGroupModel>();

    public VRTourModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addScene(VRSceneModel scene) {
        sceneList.add(scene);
    }

    public void addGroup(VRGroupModel group) {
        groupList.add(group);
    }

    public String getVTourFolder() {
        // return
        // "C:\\Users\\limsangwook\\workspace_news_h\\yhnews\\target\\ROOT\\vrContents\\adminContents\\site_code1\\vtour";
        String vTourFolder = null;
        if (sceneList.size() > 0) {
            File aFile = new File(sceneList.get(0).getPanoramaFilePath());
            vTourFolder = aFile.getParent() + "\\vtour";
            return vTourFolder;
        }
        return vTourFolder;
    }

    public List<String> getSceneImageFileList() {
        List<String> fileList = new ArrayList<String>();
        for (VRSceneModel scene : sceneList) {
            fileList.add(scene.getPanoramaFilePath());
        }
        return fileList;
    }

    public boolean applySeyleData(Document doc) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean applySceneData(Document doc) {
        for (int idx = 0; idx < sceneList.size(); idx++) {
            System.out.println((idx + 1));
            Element scene = (Element) XMLHelper.getScene(doc, "scene_" + (idx + 1));
            if (scene != null) {
                scene.setAttribute("title", sceneList.get(idx).getTitle());
                scene.setAttribute("name", sceneList.get(idx).getName());
                sceneList.get(idx).addHotspot(doc, scene);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean addGroupXML(Document doc) {
        for (VRGroupModel groupModel : groupList) {
            Element ge = doc.createElement("panoramagroup");
            ge.setAttribute("title", groupModel.getTitle());
            ge.setAttribute("name", groupModel.getName());
            ge.setAttribute("description", groupModel.getDescription());
            doc.getDocumentElement().appendChild(ge);
        }
        return true;
    }
}
