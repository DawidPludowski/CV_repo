package pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.model;

import java.io.File;
import java.io.IOException;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.stereotype.Component;

@Component
public class RecognitionNetworkImpl implements RecognitionNetwork{
	
	private MultiLayerNetwork mlpNetwork;
	
	public RecognitionNetworkImpl() {
		try {
			this.mlpNetwork = MultiLayerNetwork.load(new File("networks/network"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int classifySignleDigit(INDArray arr) {
		return this.mlpNetwork.predict(arr)[0];
	}
	
}
