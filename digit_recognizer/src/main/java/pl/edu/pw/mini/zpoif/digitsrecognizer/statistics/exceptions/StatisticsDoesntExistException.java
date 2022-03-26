package pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions;


public class StatisticsDoesntExistException extends Exception {
	
	private String username;
	
	public StatisticsDoesntExistException(String username) {
		this.username = username;
	}
	
	@Override
	public String getMessage() {
		return "Statistics of user: " + this.username + " doesn't exist";
	}
}
