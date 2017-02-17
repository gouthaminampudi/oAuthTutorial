package com.facebook.poc;


import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class PoctutorialApplication  extends WebSecurityConfigurerAdapter {
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	public static void main(String[] args) {
		//set proxy information if you are behind a firewall
/*		String proxyurl = "proxyurl";
		String proxyport = "proxyport";
		String proxyusername = "proxyusername";
		String proxypassword = "proxypassword";
		
		System.getProperties().put("http.proxyHost", proxyurl);
		System.getProperties().put("http.proxyPort", proxyport);
		System.getProperties().put("http.proxyUser", proxyusername);
		System.getProperties().put("http.proxyPassword", proxypassword);
		System.getProperties().put("https.proxyHost", proxyurl);
		System.getProperties().put("https.proxyPort", proxyport);
		System.getProperties().put("https.proxyUser", proxyusername);
		System.getProperties().put("https.proxyPassword", proxypassword);*/
		SpringApplication.run(PoctutorialApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
				.authenticated().and().logout().logoutSuccessUrl("/").permitAll()
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());;
	}
	

}
