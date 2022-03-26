package pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.service.RecognitionService;


@RestController
public class RecognitionController {

	@Autowired
	RecognitionService recognitionService;
	
	@PostMapping("recognition/classify")
	public int classify(@RequestBody List<Integer> pixels) {
		return recognitionService.classifyDigit(pixels);
	}
	
}
