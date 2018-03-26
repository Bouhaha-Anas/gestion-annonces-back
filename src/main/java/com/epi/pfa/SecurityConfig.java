package com.epi.pfa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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
			.antMatchers("/inscription").permitAll()
			.antMatchers("/inscriptionClient").permitAll()
			.antMatchers("/inscriptionEntrepreneur").permitAll()
			.antMatchers("/profilClient","profilClient/parametresGeneraux").hasAnyAuthority("CLIENT")
			.antMatchers("/profilEntrepreneur").hasAnyAuthority("ENTREPRENEUR")
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
				.logoutSuccessUrl("/accueil")
			.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
		
//		http.sessionManagement()
//        	.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//        	.maximumSessions(2)
//        	.expiredUrl("/sessionExpired.html")
//        	.and()
//        	.invalidSessionUrl("/invalidSession.html");
			
		
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception 
	{
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**");
	}
	
}
