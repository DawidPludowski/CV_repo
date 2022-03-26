package pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.model;

import org.nd4j.linalg.api.ndarray.INDArray;


public interface RecognitionNetwork {
	
	public int classifySignleDigit(INDArray arr);
}
