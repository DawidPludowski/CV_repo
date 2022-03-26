package pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.exceptions.StatisticsAlreadyInitializedException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.statistics.service.UserStatisticsService;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.entity.Authority;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.entity.User;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.dto.UserDto;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.validation.PasswordsNotTheSameException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.validation.UserAlreadyExistException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.repository.AuthorityRepository;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	UserStatisticsService userStatisticsService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException, PasswordsNotTheSameException {
		
		if(userRepository.existsById(userDto.getUsername())) {
			throw new UserAlreadyExistException(userDto.getUsername());
		}
		
		if(!userDto.getPassword().equals(userDto.getMatchingPassword())) {
			throw new PasswordsNotTheSameException();
		}
		
		String username = userDto.getUsername();
		String password = encoder.encode(userDto.getPassword());
		
		User user = new User(username, password, true);
		Authority authority = new Authority(userDto.getUsername(), "ROLE_USER");
		
		user = userRepository.save(user);
		
		authorityRepository.save(authority);
		try {
			userStatisticsService.initializeStatistics(userDto.getUsername());
		} catch (StatisticsAlreadyInitializedException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return user; 
	}

}
