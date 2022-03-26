package pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import pl.edu.pw.mini.zpoif.digitsrecognizer.security.CurrentUserUtils;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions.StatisticsDoesntExistException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.service.UserStatisticsService;

@Controller
@Secured("USER")
public class StatisticsController {
	
	@Autowired
	UserStatisticsService userStatisticsService;

	@PostMapping("statistics/update")
	public RedirectView getStatistics(@RequestBody boolean isCorrect) throws StatisticsDoesntExistException {
		userStatisticsService.increment(CurrentUserUtils.getCurrentUsername(), isCorrect);
		return new RedirectView("/");
		
	}
}