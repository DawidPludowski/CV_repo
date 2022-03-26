package pl.edu.pw.mini.zpoif.digitsrecognizer.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.dto.UserDto;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.service.RegistrationService;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.validation.PasswordsNotTheSameException;
import pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.validation.UserAlreadyExistException;

@Controller
public class RegistrationController {

	@Autowired
	RegistrationService userService;
	
	@GetMapping("/register")
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "registration_form";
	}
	
	@PostMapping("/register")
	public ModelAndView registerUserAccount(
	  @ModelAttribute("user") @Valid UserDto userDto,
	  BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("/registration_form");
			mav.addObject("user", userDto);
			return mav;
		}
		
	    try {
	        userService.registerNewUserAccount(userDto);
	    } catch (UserAlreadyExistException uaeEx) {
	    	ModelAndView mav = new ModelAndView("/registration_form");
	    	userDto.setMatchingPassword("");
	    	userDto.setPassword("");
	    	mav.addObject("user", userDto);
	        mav.addObject("alreadyexists", "true");
	        return mav;
	    } catch (PasswordsNotTheSameException e) {
	    	ModelAndView mav = new ModelAndView("/registration_form");
	    	userDto.setMatchingPassword("");
	    	userDto.setPassword("");
	    	mav.addObject("user", userDto);
	    	mav.addObject("notSamePasswords", "true");
	    	return mav;
		}
	    
	    ModelAndView indexRedirect = new ModelAndView("index");
	    indexRedirect.addObject("successfulRegistration", "true");
	    return indexRedirect;
	}
	
	
}
