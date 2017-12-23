package com.krpano.build;

import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.krpano.build.model.VRTourModel;

public class KRpanoBuilder extends Thread{
	private static String buildBatchfilePath = "/home/krpano/makevtour.sh";
	private Document doc = null;
	private Process process = null;
	private VRTourModel vrModel = null;

	public KRpanoBuilder(VRTourModel vrModel) {
		this.vrModel = vrModel;
	}

	public VRTourModel getVrModel() {
		return vrModel;
	}

	public void setVrModel(VRTourModel vrModel) {
		this.vrModel = vrModel;
	}

	@Override
    public void run() {
		try {
			if (createVRTourContents() == false) return;
			doc = Utils.getXMLDocument(vrModel.getVTourFolder()+"\\tour.xml");
			if (injectDefaultData() == false) return;
//			if (injectGroupData() == false) return;
			if (injectSceneData() == false) return;
//			if (injectStyleData() == false) return;


			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File(vrModel.getVTourFolder()+"\\tour_new.xml"));
			Source input = new DOMSource(doc);

			transformer.transform(input, output);
			System.out.println(input.toString());
			System.out.println(vrModel.getVTourFolder()+"\\tour_new.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean createVRTourContents() {
		Utils.deleteDirectory(new File(vrModel.getVTourFolder()));

		ProcessBuilder pb = new ProcessBuilder(getCommandArray());
	    pb.redirectInput(Redirect.INHERIT);
	    pb.redirectOutput(Redirect.INHERIT);
	    pb.redirectError(Redirect.INHERIT);
		try {
			process = pb.start();
			process.waitFor();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean injectDefaultData() throws Exception {
		Element krapanoElement =  doc.getDocumentElement();
		krapanoElement.setAttribute("title", vrModel.getTitle());

		return true;
	}

	private boolean injectGroupData() {

		return vrModel.addGroupXML(doc);
	}

	private boolean injectSceneData() {
		return vrModel.applySceneData(doc);
	}

	private boolean injectStyleData() {
		return vrModel.applySeyleData(doc);
	}

	private String[] getCommandArray() {
		List<String> command = new ArrayList<String>();
		command.add(buildBatchfilePath);
		command.addAll(vrModel.getSceneImageFileList());

        return Utils.convertList2Array(command);
	}

	public boolean isProcessStarted() {
		if (process == null) {
			return false;
		}
		return true;
	}

	public boolean isProcessRunning() {
		if (process == null) {
			return false;
		}
	    try {
	        process.exitValue();
	        return false;
	    } catch(IllegalThreadStateException e){
	        return true;
	    }
	}

	public void destoryProcess() {
		if (isProcessRunning() == true) {
//			process.destroy();
			process.destroyForcibly();
		}
	}
}
