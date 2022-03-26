package pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.service;

import java.util.List;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.model.RecognitionNetwork;

@Service
public class RecognitionServiceImpl implements RecognitionService{
	
	@Autowired
	RecognitionNetwork network;
	
	public int classifyDigit(List<Integer> pixels) {
		INDArray arr = convert256ListTo01Array(pixels);
		return network.classifySignleDigit(arr);
	}
	
	private INDArray convert256ListTo01Array(List<Integer> pixels) {
		Double[] pixels01 = new Double[28*28];
		for (int i = 0; i < pixels.size(); i++) {
			pixels01[i] = (((double) pixels.get(i)) / 255);
		}
		INDArray arr = Nd4j.createFromArray(pixels01);
		return arr.reshape(1, 28*28);
	}
	
}
