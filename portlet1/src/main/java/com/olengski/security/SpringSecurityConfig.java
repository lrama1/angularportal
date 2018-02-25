package com.olengski.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().disable().addFilterAfter(siteminderFilter(), RequestHeaderAuthenticationFilter.class)
				.csrf().disable();
	}

	@Bean(name = "siteminderFilter")
	public RequestHeaderAuthenticationFilter siteminderFilter() throws Exception {
		RequestHeaderAuthenticationFilter siteminderFilter = new RequestHeaderAuthenticationFilter();
		siteminderFilter.setPrincipalRequestHeader("SM_USER");
		siteminderFilter.setExceptionIfHeaderMissing(false);
		siteminderFilter.setAuthenticationManager(authenticationManagerBean());
		return siteminderFilter;
	}

	@Bean
	public SpringAuthenticationProvider springAuthenticationProvider() {
		return new SpringAuthenticationProvider();
	}

	@Bean
	public ClearSessionOnSMHeaderChange clearSessionOnSMHeaderChange() {
		return new ClearSessionOnSMHeaderChange();
	}
}
