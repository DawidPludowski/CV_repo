package pl.edu.pw.mini.zpoif.digitsrecognizer.user.registration.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDto {
	
    @NotNull
    @NotEmpty(message = "Cannot be empty")
    private String username;
    
    @NotNull
    @NotEmpty(message = "Cannot be empty")
    private String password;
    
    @NotEmpty(message = "Cannot be empty")
    private String matchingPassword;
    
}