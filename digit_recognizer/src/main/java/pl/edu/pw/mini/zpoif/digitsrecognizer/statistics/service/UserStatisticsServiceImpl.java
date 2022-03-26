package pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.entity.UserStatistics;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions.StatisticsAlreadyInitializedException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions.StatisticsDoesntExistException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.repository.UserStatisticsRepository;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService{

	@Autowired
	private UserStatisticsRepository userStatisticsRepository;
	
	@Override
	public UserStatistics initializeStatistics(String username) throws StatisticsAlreadyInitializedException {
		
		if(userStatisticsRepository.existsById(username)) {
			throw new StatisticsAlreadyInitializedException(username);
		}
		
		UserStatistics us = new UserStatistics(username);
		
		userStatisticsRepository.save(us);
		
		return us;
	}

	@Override
	public UserStatistics clearStatistics(String username) throws StatisticsDoesntExistException {
		Optional<UserStatistics> optUs = userStatisticsRepository.findById(username);
		
		if(optUs.isEmpty()) {
			throw new StatisticsDoesntExistException(username);
		}
		
		UserStatistics us = optUs.get();
		
		us.setCorrectGuesses(0);
		us.setAllGuesses(0);
		
		return userStatisticsRepository.save(us);
	}

	@Override
	public UserStatistics increment(String username, boolean correct) throws StatisticsDoesntExistException {
		Optional<UserStatistics> optUs = userStatisticsRepository.findById(username);
		
		if(optUs.isEmpty()) {
			throw new StatisticsDoesntExistException(username);
		}
		
		UserStatistics us = optUs.get();
		
		if(correct) {
			us.setCorrectGuesses(us.getCorrectGuesses() + 1);
		}
		
		us.setAllGuesses(us.getAllGuesses() + 1);
		
		return userStatisticsRepository.save(us);
	}

	@Override
	public UserStatistics getStatisticsByUsername(String username) throws StatisticsDoesntExistException {
		Optional<UserStatistics> optUs = userStatisticsRepository.findById(username);
		
		if(optUs.isEmpty()) {
			throw new StatisticsDoesntExistException(username);
		}
		
		UserStatistics us = optUs.get();
		
		return us;
	}

	
}
