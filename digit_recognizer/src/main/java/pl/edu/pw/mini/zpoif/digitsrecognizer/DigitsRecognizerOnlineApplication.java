package pl.edu.pw.mini.zpoif.digitsrecognizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.edu.pw.mini.zpoif.digitsrecognizer"})
public class DigitsRecognizerOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitsRecognizerOnlineApplication.class, args);
	}

}
