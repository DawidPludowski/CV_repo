package pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.service;

import pl.edu.pw.mini.zpoif.digitsrecognizer.user.entity.User;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.dto.UserDto;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.validation.PasswordsNotTheSameException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.validation.UserAlreadyExistException;

public interface RegistrationService {
	public User registerNewUserAccount(UserDto userDt) throws UserAlreadyExistException, PasswordsNotTheSameException;
}
