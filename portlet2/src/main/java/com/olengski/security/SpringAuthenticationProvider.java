package com.olengski.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class SpringAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		if (!"pass".equals(auth.getCredentials()) && !(auth instanceof PreAuthenticatedAuthenticationToken)) {
			throw new BadCredentialsException("Auth Failed");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(auth, auth.getCredentials(), authorities);
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.isAssignableFrom(UsernamePasswordAuthenticationToken.class)
				|| auth.isAssignableFrom(PreAuthenticatedAuthenticationToken.class);
	}

}
