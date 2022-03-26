package pl.edu.pw.mini.zpoif.digitsrecognizer.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {

	public static String getCurrentUsername() {
		return SecurityContextHolder.
				getContext().
				getAuthentication().
				getName();
	}
}
