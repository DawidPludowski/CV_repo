package pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions;

public class StatisticsAlreadyInitializedException extends Exception {
	private String username;
	
	public StatisticsAlreadyInitializedException(String username) {
		this.username = username;
	}
	
	@Override
	public String getMessage() {
		return "User " + this.username + " has statistics already initialized"; 
	}
}
