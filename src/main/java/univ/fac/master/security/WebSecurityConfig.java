package univ.fac.master.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import univ.fac.master.service.CustomEncadrantDetailsService;
import univ.fac.master.service.CustomEtudiantDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		//return new CustomEtudiantDetailsService();
		return new CustomEncadrantDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		
	  
		/*http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();*/ 
		http.csrf().disable().authorizeRequests()
		.antMatchers("/", "/process_register")
        .permitAll()
        .antMatchers("/", "/process_encadrant")
        .permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
		

	}
	
}
