package pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.validation;

public class UserAlreadyExistException extends Exception {

	private String username;

	public UserAlreadyExistException(String username) {
		this.username = username;
	}
	
	@Override
	public String getMessage() {
		return "Exists user with username: " + this.username;
	}
}
