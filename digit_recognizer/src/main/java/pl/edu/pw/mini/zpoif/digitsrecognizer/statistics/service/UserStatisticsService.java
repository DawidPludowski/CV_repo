package pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.service;

import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.entity.UserStatistics;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions.StatisticsAlreadyInitializedException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions.StatisticsDoesntExistException;

public interface UserStatisticsService {
	
	public UserStatistics initializeStatistics(String username) throws StatisticsAlreadyInitializedException;
	public UserStatistics clearStatistics(String username) throws StatisticsDoesntExistException;
	public UserStatistics increment(String username, boolean correct) throws StatisticsDoesntExistException;
	public UserStatistics getStatisticsByUsername(String username) throws StatisticsDoesntExistException;
}
