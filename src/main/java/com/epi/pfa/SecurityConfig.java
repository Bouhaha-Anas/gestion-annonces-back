package com.epi.pfa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication()
				.usersByUsernameQuery("select login, mot_de_passe, enabled from comptes where login=?")
				.authoritiesByUsernameQuery("select login, role from comptes where login=?")
				.dataSource(dataSource);		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.authorizeRequests()
			.antMatchers("/accueil").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/inscriptionClient").permitAll()
			.antMatchers("/inscriptionEntrepreneur").permitAll()
			.antMatchers("/profil").hasAuthority("CLIENT")
			.anyRequest()
				.authenticated()
			.and()
			.csrf()
				.disable().formLogin()
			.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/accueil")
				.usernameParameter("login")
				.passwordParameter("motDePasse")
			.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
			.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}	
}
