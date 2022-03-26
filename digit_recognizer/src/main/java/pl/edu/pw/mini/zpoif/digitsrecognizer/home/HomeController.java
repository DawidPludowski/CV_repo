package pl.edu.pw.mini.zpoif.digitsrecognizer.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pw.mini.zpoif.digitsrecognizer.security.CurrentUserUtils;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.entity.UserStatistics;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions.StatisticsDoesntExistException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.service.UserStatisticsService;

@Controller
public class HomeController {

	@Autowired
	UserStatisticsService userStatisticsService;
	
	@RequestMapping("/")
	public ModelAndView home() throws StatisticsDoesntExistException {
		
		String currentUser = CurrentUserUtils.getCurrentUsername();
		
		ModelAndView mav = new ModelAndView("index");
		
		if(!currentUser.equals("anonymousUser")) {
			UserStatistics us = userStatisticsService.getStatisticsByUsername(currentUser);
			
			mav.addObject("userStatistics", us);
		}
		
		return mav;
	}
	
	
	
}
