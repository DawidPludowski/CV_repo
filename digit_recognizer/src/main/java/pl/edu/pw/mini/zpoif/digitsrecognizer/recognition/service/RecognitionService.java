package pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.service;

import java.util.List;

public interface RecognitionService {
	public int classifyDigit(List<Integer> pixels);
}
